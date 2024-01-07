package pl.tomwodz.film.domain.favourite;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.tomwodz.film.domain.film.FilmRepository;
import pl.tomwodz.film.domain.loginandregister.UserRepository;

@Configuration
public class FavouriteFacadeConfiguration {

    @Bean
    FavouriteFacade favouriteFacade(UserRepository userRepository, FilmRepository filmRepository) {
        FavouriteMapper favouriteMapper = new FavouriteMapper();
        return new FavouriteFacade(userRepository, filmRepository, favouriteMapper);
    }
}
