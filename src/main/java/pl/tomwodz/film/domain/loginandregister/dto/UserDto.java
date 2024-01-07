package pl.tomwodz.film.domain.loginandregister.dto;

import lombok.Builder;

@Builder
public record UserDto(
        Long id,
        String password,
        String username

) {
}
