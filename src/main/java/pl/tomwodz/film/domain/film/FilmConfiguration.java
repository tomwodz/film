package pl.tomwodz.film.domain.film;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class FilmConfiguration {

    @Bean
    FilmFacade filmFacade(FilmRepository filmRepository){
        FilmFactory filmFactory = new FilmFactory();
        return new FilmFacade(filmRepository, filmFactory);
    }
}
