package pl.tomwodz.film.domain.film;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pl.tomwodz.film.domain.client.OMDbClient;
import pl.tomwodz.film.domain.client.dto.OMDbResponseDto;
import pl.tomwodz.film.domain.film.dto.FilmRequestDto;
import pl.tomwodz.film.domain.film.dto.FilmResponseDto;
import pl.tomwodz.film.infrastructure.film.error.FilmNotFoundException;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Transactional
@Log4j2
public class FilmFacade {
    private final FilmRepository filmRepository;
    private final FilmFactory filmFactory;

    private final OMDbClient omdbClinet;

    public FilmResponseDto findFilmById(Long id){
        return this.filmRepository.findById(id)
                .map(FilmMapper::mapFromFilmToFilmResponseDto)
                .orElseThrow(()-> new FilmNotFoundException("not found film id:" + id));
    }

    public FilmResponseDto saveFilm(FilmRequestDto requestDto){
        Film filmToSave = filmFactory.mapFromFilmRequestDtoToFilm(requestDto);
        Film filmSaved = filmRepository.save(filmToSave);
        log.info("saved task by id: " + filmSaved.getId() + " " + filmSaved.getTitle());
        return FilmMapper.mapFromFilmToFilmResponseDto(filmSaved);
    }

    public FilmResponseDto findFilmByTitle(String title){
        OMDbResponseDto omdbResponseDto = this.omdbClinet.findFilmByTitle(title);
        log.warn(omdbResponseDto);
        Film filmToSave = this.filmFactory.mapFromOMDbResponseDtoToFilm(omdbResponseDto);
        Film filmSaved = this.filmRepository.save(filmToSave);
        return FilmMapper.mapFromFilmToFilmResponseDto(filmSaved);
    }

    public FilmResponseDto findFilmByImdbIDInDatabase(String imdbID){
        return this.filmRepository.findByImdbID(imdbID)
                .map(FilmMapper::mapFromFilmToFilmResponseDto)
                .orElseThrow(()-> new FilmNotFoundException("not found film imdbID:" + imdbID));
    }


}
