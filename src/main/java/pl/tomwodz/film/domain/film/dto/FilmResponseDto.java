package pl.tomwodz.film.domain.film.dto;

import lombok.Builder;

@Builder
public record FilmResponseDto(
        Long id,
        String title,
        String description,
        String director,
        String genre,
        String image
) {
}
