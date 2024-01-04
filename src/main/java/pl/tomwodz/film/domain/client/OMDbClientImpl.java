package pl.tomwodz.film.domain.client;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.tomwodz.film.domain.client.dto.OMDbResponseDto;
import pl.tomwodz.film.infrastructure.cilent.OMDbProxy;

@Component
@AllArgsConstructor
public class OMDbClientImpl implements OMDbClient {

    private final OMDbProxy omdbProxy;
    @Override
    public OMDbResponseDto findFilmByTitle(String title) {
        return this.omdbProxy.makeGetRequestByTitleFilm(title);
    }
}
