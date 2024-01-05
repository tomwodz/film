package pl.tomwodz.film.domain.film;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pl.tomwodz.film.domain.client.OMDbClient;
import pl.tomwodz.film.domain.client.dto.OMDbResponseDto;
import pl.tomwodz.film.domain.film.dto.FilmRequestDto;
import pl.tomwodz.film.domain.film.dto.FilmResponseDto;
import pl.tomwodz.film.domain.film.dto.FilmSearchRequestDto;
import pl.tomwodz.film.infrastructure.film.error.FilmNotFoundException;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Transactional
@Log4j2
public class FilmFacade {
    private final FilmRepository filmRepository;
    private final FilmFactory filmFactory;

    private final OMDbClient omdbClinet;

    public List<FilmResponseDto> findAllFilms() {
        List<Film> films = this.filmRepository.findAll();
        return films.stream()
                .map(f -> FilmMapper.mapFromFilmToFilmResponseDto(f))
                .toList();
    }

    public FilmResponseDto findFilmById(Long id) {
        return this.filmRepository.findById(id)
                .map(FilmMapper::mapFromFilmToFilmResponseDto)
                .orElseThrow(() -> new FilmNotFoundException("not found film id:" + id));
    }

    public FilmResponseDto saveFilm(FilmRequestDto requestDto) {
        Film filmToSave = filmFactory.mapFromFilmRequestDtoToFilm(requestDto);
        if (!existsByImdbID(filmToSave.getImdbID())) {
            Film filmSaved = filmRepository.save(filmToSave);
            log.info("saved film by id: " + filmSaved.getId() + " " + filmSaved.getTitle());
            return FilmMapper.mapFromFilmToFilmResponseDto(filmSaved);
        }
        return findFilmResponseByImdbIDInDatabase(requestDto.imdbID());
    }

    public List<FilmResponseDto> searchFilm(FilmSearchRequestDto filmSearchRequestDto) {
        List<Film> filmBox = Collections.emptyList();
        Set<Film> filmSet = new HashSet<>();
        String title = filmSearchRequestDto.title();
        String director = filmSearchRequestDto.director();
        if (filmSearchRequestDto.titleSearch()) {
            filmBox = this.filmRepository.findByTitleLike("%" + title + "%");
            if (filmBox.size() == 0) {
                filmBox.add(searchOMDbAndDatabase(title));
            }
            filmBox.stream()
                    .forEach(f -> filmSet.add(f));
            filmBox.clear();
        }
        if (filmSearchRequestDto.directorSearch()) {
            this.filmRepository.findByDirectorLike("%" + director + "%").
                    stream().
                    forEach(f -> filmSet.add(f));
        }
        if(filmSet.size() != 0) {
        filmBox = filmSet.stream().toList();
        }
        return FilmMapper.mapFromListFilmToListFilmResponseDto(filmBox);
    }

    private Film searchOMDbAndDatabase(String title) {
        OMDbResponseDto omdbResponseDto = this.omdbClinet.findFilmByTitle(title);
        log.warn(omdbResponseDto);
        if (!existsByImdbID(omdbResponseDto.imdbID())) {
            Film filmToSave = this.filmFactory.mapFromOMDbResponseDtoToFilm(omdbResponseDto);
            Film filmSaved = this.filmRepository.save(filmToSave);
            return filmSaved;
        } else {
            Film filmWithDatabase = findFilmByImdbIDInDatabase(omdbResponseDto.imdbID());
            return filmWithDatabase;
        }
    }
    public List<FilmResponseDto> findTop10ByOrderByDateLast(){
        List<Film> films = this.filmRepository.findTop10ByOrderByDateLastLikesDesc();
        return films.stream()
                .map(f -> FilmMapper.mapFromFilmToFilmResponseDto(f))
                .toList();
    }

    public FilmResponseDto findFilmResponseByImdbIDInDatabase(String imdbID) {
        return FilmMapper.mapFromFilmToFilmResponseDto(
                findFilmByImdbIDInDatabase(imdbID));
    }

    private Film findFilmByImdbIDInDatabase(String imdbID) {
        return this.filmRepository.findByImdbID(imdbID)
                .orElseThrow(() -> new FilmNotFoundException("not found film imdbID:" + imdbID));
    }

    private boolean existsByImdbID(String imdbID) {
        return this.filmRepository.existsByImdbID(imdbID);
    }

}
