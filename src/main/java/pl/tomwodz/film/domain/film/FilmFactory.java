package pl.tomwodz.film.domain.film;

import pl.tomwodz.film.domain.client.dto.OMDbResponseDto;
import pl.tomwodz.film.domain.film.dto.FilmRequestDto;

import java.time.LocalDateTime;
import java.util.UUID;

class FilmFactory {



    Film mapFromFilmRequestDtoToFilm(FilmRequestDto requestDto){
        String imdbID = String.valueOf(UUID.randomUUID());
        if(!requestDto.imdbID().equals("") || requestDto.imdbID() != null) {
            imdbID = requestDto.imdbID();
        }
        LocalDateTime dateCreation = LocalDateTime.now();
        LocalDateTime dateLastLikes = LocalDateTime.of(2024,01,07,15,00);
        return new Film(
                requestDto.title(),
                requestDto.plot(),
                requestDto.director(),
                requestDto.genre(),
                requestDto.poster(),
                imdbID,
                dateCreation,
                dateLastLikes
        );
    }

    public Film mapFromOMDbResponseDtoToFilm(OMDbResponseDto omdbResponseDto) {
        LocalDateTime dateCreation = LocalDateTime.now();
        LocalDateTime dateLastLikes = dateCreation;
        return new Film(
                omdbResponseDto.Title(),
                omdbResponseDto.Plot(),
                omdbResponseDto.Director(),
                omdbResponseDto.Genre(),
                omdbResponseDto.Poster(),
                omdbResponseDto.imdbID(),
                dateCreation,
                dateLastLikes
        );
    }
}