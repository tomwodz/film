package pl.tomwodz.film.domain.loginandregister.dto;

import lombok.Builder;

@Builder
public record RegistrationResultDto (
        Long id,
        String username,
        boolean registered
) {
}
