package pl.tomwodz.film.domain.film.dto;

import lombok.Builder;

@Builder
public record FilmRequestDto(
        String title,
        String plot,
        String director,
        String genre,
        String poster,
        String imdbID

) {
}
