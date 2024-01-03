package pl.tomwodz.film.domain.film;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pl.tomwodz.film.domain.film.dto.FilmRequestDto;
import pl.tomwodz.film.domain.film.dto.FilmResponseDto;
import pl.tomwodz.film.infrastructure.film.error.FilmNotFoundException;

@AllArgsConstructor
@Transactional
@Log4j2
public class FilmFacade {
    private final FilmRepository filmRepository;
    private final FilmFactory filmFactory;

    public FilmResponseDto findFilmById(Long id){
        return this.filmRepository.findById(id)
                .map(FilmMapper::mapFromFilmToFilmResponseDto)
                .orElseThrow(()-> new FilmNotFoundException("not found film id:" + id));
    }

    public FilmResponseDto saveFilm(FilmRequestDto requestDto){
        Film filmToSave = filmFactory.mapFromFilmRequestDtoToFilm(requestDto);
        Film fimSaved = filmRepository.save(filmToSave);
        log.info("saved task by id: " + fimSaved.getId() + " " + fimSaved.getTitle());
        return FilmMapper.mapFromFilmToFilmResponseDto(fimSaved);
    }

}
