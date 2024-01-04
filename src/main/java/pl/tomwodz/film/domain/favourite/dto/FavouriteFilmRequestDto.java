package pl.tomwodz.film.domain.favourite.dto;

import lombok.Builder;

import java.util.Set;

@Builder
public record FavouriteFilmRequestDto(
        Long idUser,

        Set<Integer> idFavouriteFilm
) {
}
