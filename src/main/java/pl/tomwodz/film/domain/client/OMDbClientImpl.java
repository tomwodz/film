package pl.tomwodz.film.domain.client;

import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import pl.tomwodz.film.domain.client.dto.OMDbResponseDto;
import pl.tomwodz.film.infrastructure.cilent.OMDbProxy;
import pl.tomwodz.film.infrastructure.cilent.error.ClientException;
import pl.tomwodz.film.infrastructure.film.error.FilmNotFoundException;

@Component
@AllArgsConstructor
@Log4j2
public class OMDbClientImpl implements OMDbClient {

    private final OMDbProxy omdbProxy;
    @Override
    public OMDbResponseDto findFilmByTitle(String title) {
        try {
            OMDbResponseDto omDbResponseDto = this.omdbProxy.makeGetRequestByTitleFilm(title);
            if(omDbResponseDto.imdbID() == null || omDbResponseDto.Title() == null){
                throw new FilmNotFoundException("Film not found: " + title);
            }
            return omDbResponseDto ;
        } catch (FeignException.FeignClientException exception){
            log.warn("OMDbProxy error: " + exception.getMessage());
            throw new ClientException("Error: Please contact with admin.");
        }
    }
}
