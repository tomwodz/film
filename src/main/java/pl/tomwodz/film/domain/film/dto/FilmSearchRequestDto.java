package pl.tomwodz.film.domain.film.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record FilmSearchRequestDto(
        @NotNull(message = "title condition {not.null.validation.message}")
        @NotEmpty(message = "title condition {not.empty.validation.message}")
        boolean titleSearch,

        @NotNull(message = "title {not.null.validation.message}")
        @NotEmpty(message = "title {not.empty.validation.message}")
        String title,
        @NotNull(message = "director condition {not.null.validation.message}")
        @NotEmpty(message = "director condition {not.empty.validation.message}")
        boolean directorSearch,
        @NotNull(message = "director {not.null.validation.message}")
        @NotEmpty(message = "director {not.empty.validation.message}")
        String director
) {
}
