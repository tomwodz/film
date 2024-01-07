package pl.tomwodz.film.domain.favourite.dto;

import lombok.Builder;

@Builder
public record FavouriteFilmResponseDto(
        Long id,
        String title,
        String description,
        String director,
        String genre,
        String image,
        String imdbID
) {
}
