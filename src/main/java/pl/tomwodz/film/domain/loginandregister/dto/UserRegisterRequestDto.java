package pl.tomwodz.film.domain.loginandregister.dto;

import lombok.Builder;

@Builder
public record UserRegisterRequestDto(
        String username,
        String password) {

}
