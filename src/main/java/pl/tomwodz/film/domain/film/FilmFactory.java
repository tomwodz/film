package pl.tomwodz.film.domain.film;

import pl.tomwodz.film.domain.film.dto.FilmRequestDto;

class FilmFactory {

    Film mapFromFilmRequestDtoToFilm(FilmRequestDto requestDto){
        return new Film(
                requestDto.title(),
                requestDto.description(),
                requestDto.director(),
                requestDto.genre(),
                requestDto.image()
        );
    }
}