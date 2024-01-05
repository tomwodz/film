package pl.tomwodz.film.infrastructure.loginandregister.error;

import org.springframework.http.HttpStatus;

public record ErrorRegisterResponseDto(
        String message,
        HttpStatus httpStatus) {
}
