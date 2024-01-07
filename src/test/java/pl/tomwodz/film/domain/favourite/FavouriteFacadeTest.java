package pl.tomwodz.film.domain.favourite;

import org.junit.jupiter.api.Test;
import pl.tomwodz.film.domain.client.OMDbClient;
import pl.tomwodz.film.domain.film.*;
import pl.tomwodz.film.domain.film.dto.FilmRequestDto;
import pl.tomwodz.film.domain.loginandregister.UserRepository;
import pl.tomwodz.film.domain.loginandregister.UserRepositoryTestImpl;
import pl.tomwodz.film.domain.loginandregister.dto.RegistrationResultDto;
import pl.tomwodz.film.domain.loginandregister.dto.UserRegisterRequestDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FavouriteFacadeTest {

    FilmRepository filmRepository = new FilmFacadeTestImpl();
    UserRepository userRepository = new UserRepositoryTestImpl();

    FavouriteFacade favouriteFacade = new FavouriteFacadeConfiguration().favouriteFacade(userRepository, filmRepository);

    UserRegisterRequestDto userRegisterRequestDto = UserRegisterRequestDto
            .builder()
            .username("tomwodz")
            .password("tomwodz")
            .build();

    FilmRequestDto filmRequestDto = FilmRequestDto
            .builder()
            .title("fdsfdsdfsfsd")
            .plot("sdffsdfdsfsd")
            .director("fdsfsddfs")
            .genre("sdfsdfds")
            .poster("fdsfsddsdfs")
            .imdbID("tt123")
            .build();

}