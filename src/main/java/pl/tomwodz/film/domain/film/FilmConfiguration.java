package pl.tomwodz.film.domain.film;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.tomwodz.film.domain.client.OMDbClient;

@Configuration
class FilmConfiguration {

    @Bean
    FilmFacade filmFacade(FilmRepository filmRepository, OMDbClient omdbClinet){
        FilmFactory filmFactory = new FilmFactory();
        return new FilmFacade(filmRepository, filmFactory, omdbClinet);
    }
}
