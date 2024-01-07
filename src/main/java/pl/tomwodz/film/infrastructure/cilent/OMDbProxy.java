package pl.tomwodz.film.infrastructure.cilent;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.tomwodz.film.domain.client.dto.OMDbResponseDto;

@FeignClient(value = "omdb-client")
@Component
public interface OMDbProxy {

    final String API_KEY = "95d3ff54";

    @GetMapping(value = "/?t={title}&apikey=" + API_KEY)
    OMDbResponseDto makeGetRequestByTitleFilm(@PathVariable String title);

}
