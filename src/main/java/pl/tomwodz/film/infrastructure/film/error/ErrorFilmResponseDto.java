package pl.tomwodz.film.infrastructure.film.error;

import org.springframework.http.HttpStatus;

public record ErrorFilmResponseDto(String message,
                                   HttpStatus httpStatus) {
}
