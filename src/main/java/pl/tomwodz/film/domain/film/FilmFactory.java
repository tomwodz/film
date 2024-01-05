package pl.tomwodz.film.domain.film;

import pl.tomwodz.film.domain.client.dto.OMDbResponseDto;
import pl.tomwodz.film.domain.film.dto.FilmRequestDto;

class FilmFactory {



    Film mapFromFilmRequestDtoToFilm(FilmRequestDto requestDto){
        return new Film(
                requestDto.title(),
                requestDto.plot(),
                requestDto.director(),
                requestDto.genre(),
                requestDto.poster(),
                requestDto.imdbID()
        );
    }

    public Film mapFromOMDbResponseDtoToFilm(OMDbResponseDto omdbResponseDto) {
        return new Film(
                omdbResponseDto.Title(),
                omdbResponseDto.Plot(),
                omdbResponseDto.Director(),
                omdbResponseDto.Genre(),
                omdbResponseDto.Poster(),
                omdbResponseDto.imdbID()
        );
    }
}