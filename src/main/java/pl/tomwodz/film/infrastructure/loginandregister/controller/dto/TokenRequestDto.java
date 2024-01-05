package pl.tomwodz.film.infrastructure.loginandregister.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record TokenRequestDto(
        @NotNull(message = "username {not.null.validation.message}")
        @NotEmpty(message = "username {not.empty.validation.message}")
        String username,
        @NotNull(message = "password {not.null.validation.message}")
        @NotEmpty(message = "password {not.empty.validation.message}")
        String password
) {
}
