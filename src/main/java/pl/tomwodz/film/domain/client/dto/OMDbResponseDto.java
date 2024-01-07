package pl.tomwodz.film.domain.client.dto;

import lombok.Builder;

@Builder
public record OMDbResponseDto(
        String Title,
        String Director,
        String Genre,

        String Plot,
        String Poster,

        String imdbID


) {
}
