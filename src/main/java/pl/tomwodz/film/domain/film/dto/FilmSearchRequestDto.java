package pl.tomwodz.film.domain.film.dto;

import lombok.Builder;

@Builder
public record FilmSearchRequestDto(
        boolean titleSearch,
        String title,
        boolean directorSearch,
        String director
) {
}
