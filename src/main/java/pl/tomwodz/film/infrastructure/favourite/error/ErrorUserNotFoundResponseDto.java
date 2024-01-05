package pl.tomwodz.film.infrastructure.favourite.error;

import org.springframework.http.HttpStatus;

public record ErrorUserNotFoundResponseDto(
        String message,
        HttpStatus httpStatus) {
}
