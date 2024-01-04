package pl.tomwodz.film.domain.client.dto;

public record OMDbResponseDto(
        String Title,
        String Director,
        String Genre,

        String Plot,
        String Poster,

        String imdbID


) {
}
