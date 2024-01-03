package pl.tomwodz.film.domain.favourite.dto;

import lombok.Builder;

import java.util.Set;

@Builder
public record FavouriteFilmResponseDto(
        Long id,
        String title,
        String description,
        String director,
        String genre,
        String image
) {
}
