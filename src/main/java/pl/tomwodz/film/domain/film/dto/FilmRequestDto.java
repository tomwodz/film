package pl.tomwodz.film.domain.film.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record FilmRequestDto(

        @NotNull(message = "title {not.null.validation.message}")
        @NotEmpty(message = "title {not.empty.validation.message}")
        String title,

        @NotNull(message = "plot {not.null.validation.message}")
        @NotEmpty(message = "plot {not.empty.validation.message}")
        String plot,

        @NotNull(message = "director {not.null.validation.message}")
        @NotEmpty(message = "director {not.empty.validation.message}")
        String director,

        @NotNull(message = "genre {not.null.validation.message}")
        @NotEmpty(message = "genre {not.empty.validation.message}")
        String genre,

        @NotNull(message = "poster {not.null.validation.message}")
        @NotEmpty(message = "poster {not.empty.validation.message}")
        String poster,

        String imdbID

) {
}
