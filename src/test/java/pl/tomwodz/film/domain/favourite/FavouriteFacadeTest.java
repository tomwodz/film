package pl.tomwodz.film.domain.favourite;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.BadCredentialsException;
import pl.tomwodz.film.domain.client.OMDbClient;
import pl.tomwodz.film.domain.favourite.dto.FavouriteFilmRequestDto;
import pl.tomwodz.film.domain.favourite.dto.FavouriteFilmResponseDto;
import pl.tomwodz.film.domain.film.*;
import pl.tomwodz.film.domain.film.dto.FilmRequestDto;
import pl.tomwodz.film.domain.film.dto.FilmResponseDto;
import pl.tomwodz.film.domain.loginandregister.User;
import pl.tomwodz.film.domain.loginandregister.UserRepository;
import pl.tomwodz.film.domain.loginandregister.UserRepositoryTestImpl;
import pl.tomwodz.film.domain.loginandregister.dto.RegistrationResultDto;
import pl.tomwodz.film.domain.loginandregister.dto.UserRegisterRequestDto;
import pl.tomwodz.film.infrastructure.film.error.FilmNotFoundException;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.as;
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

    User user = new User(0L, "tomwodz", "tomwodz", new HashSet<>());

    Film film = new Film("test", "dsadsdsa", "sadsdada", "dasaddadasda", "sdaadsdasdas", "tt123", LocalDateTime.now(), LocalDateTime.of(2014, 01, 07, 15, 00));

    Set<Integer> setBox = Set.of(1);


    FavouriteFilmRequestDto favouriteFilmRequestDto = FavouriteFilmRequestDto
            .builder()
            .username("tomwodz")
            .idFavouriteFilm(setBox)
            .build();

    @Test
    void ShouldBeUnableAddToFavouriteWhenUserNotExists() {

        //given
        //when
        //then
        assertThrows(UserNotFoundException.class, () -> this.favouriteFacade.addFavouriteFilmForUser(favouriteFilmRequestDto));
    }

    @Test
    void ShouldBeUnableAddToFavouriteWhenFilmNotExists() {

        //given
        this.userRepository.save(user);

        //when
        //then
        assertThrows(FilmNotFoundException.class, () -> this.favouriteFacade.addFavouriteFilmForUser(favouriteFilmRequestDto));
    }

    @Test
    void ShouldBeAbleAddToFavouriteWhenUserAndFilmExists() {

        //given
        User userSaved = this.userRepository.save(user);
        Film filmSaved = this.filmRepository.save(film);
        Set<Integer> setBox2 = Set.of(filmSaved.getId().intValue());
        FavouriteFilmRequestDto favouriteFilmRequestDtoExists = FavouriteFilmRequestDto
                .builder()
                .username("tomwodz")
                .idFavouriteFilm(setBox2)
                .build();

        //when
        List<FavouriteFilmResponseDto> favouriteFilmResponseDtoList =
                this.favouriteFacade.addFavouriteFilmForUser(favouriteFilmRequestDtoExists);

        //then
        assertThat(favouriteFilmResponseDtoList).isNotNull();
        assertThat(favouriteFilmResponseDtoList.size()).isEqualTo(1);
        assertThat(favouriteFilmResponseDtoList.get(0).title()).isEqualTo(filmSaved.getTitle());
    }

    @Test
    void ShouldBeAbleDeleteToFavouriteWhenUserAndFilmExists() {

        //given
        User userSaved = this.userRepository.save(user);
        Film filmSaved = this.filmRepository.save(film);
        Set<Integer> setBox2 = Set.of(filmSaved.getId().intValue());
        FavouriteFilmRequestDto favouriteFilmRequestDtoExists = FavouriteFilmRequestDto
                .builder()
                .username("tomwodz")
                .idFavouriteFilm(setBox2)
                .build();
        this.favouriteFacade.addFavouriteFilmForUser(favouriteFilmRequestDtoExists);

        //when
        List<FavouriteFilmResponseDto> favouriteFilmResponseDtoList =
                this.favouriteFacade.deleteFavouriteFilmForUser(favouriteFilmRequestDtoExists);

        //then
        assertThat(favouriteFilmResponseDtoList).isNotNull();
        assertThat(favouriteFilmResponseDtoList.size()).isEqualTo(0);
    }

    @Test
    void ShouldBeAbleFindFavouriteForUser() {

        //given
        User userSaved = this.userRepository.save(user);
        Film filmSaved = this.filmRepository.save(film);
        Set<Integer> setBox2 = Set.of(filmSaved.getId().intValue());
        FavouriteFilmRequestDto favouriteFilmRequestDtoExists = FavouriteFilmRequestDto
                .builder()
                .username("tomwodz")
                .idFavouriteFilm(setBox2)
                .build();
        this.favouriteFacade.addFavouriteFilmForUser(favouriteFilmRequestDtoExists);

        //when
        List<FavouriteFilmResponseDto> favouriteFilmResponseDtoList =
                this.favouriteFacade.findFavouriteFilmForUser(favouriteFilmRequestDtoExists.username());

        //then
        assertThat(favouriteFilmResponseDtoList).isNotNull();
        assertThat(favouriteFilmResponseDtoList.size()).isEqualTo(1);
        assertThat(favouriteFilmResponseDtoList.get(0).title()).isEqualTo(filmSaved.getTitle());
    }

}