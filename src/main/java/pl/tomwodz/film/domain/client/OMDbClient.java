package pl.tomwodz.film.domain.client;

import pl.tomwodz.film.domain.client.dto.OMDbResponseDto;

public interface OMDbClient {

    OMDbResponseDto findFilmByTitle(String title);

}
