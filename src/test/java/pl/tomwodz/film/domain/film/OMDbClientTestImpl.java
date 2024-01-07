package pl.tomwodz.film.domain.film;

import pl.tomwodz.film.domain.client.OMDbClient;
import pl.tomwodz.film.domain.client.dto.OMDbResponseDto;

public class OMDbClientTestImpl implements OMDbClient {

    @Override
    public OMDbResponseDto findFilmByTitle(String title) {
        return OMDbResponseDto
                .builder()
                .Title("Shrek")
                .Director("Adamson")
                .build();
    }
}
