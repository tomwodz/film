package pl.tomwodz.film.domain.loginandregister.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UserRegisterRequestDto(

        @NotNull(message = "username {not.null.validation.message}")
        @NotEmpty(message = "username {not.empty.validation.message}")
        String username,
        @NotNull(message = "password {not.null.validation.message}")
        @NotEmpty(message = "password {not.empty.validation.message}")
        String password) {

}
