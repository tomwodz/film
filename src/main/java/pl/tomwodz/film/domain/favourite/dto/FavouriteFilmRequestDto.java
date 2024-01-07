package pl.tomwodz.film.domain.favourite.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.Set;

@Builder
public record FavouriteFilmRequestDto(
        @NotNull(message = "username {not.null.validation.message}")
        @NotEmpty(message = "username {not.empty.validation.message}")
        String username,

        @NotNull(message = "Id favourite films {not.null.validation.message}")
        @NotEmpty(message = "Id favourite films {not.empty.validation.message}")
        Set<Integer> idFavouriteFilm
) {
}
