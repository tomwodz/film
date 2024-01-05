package pl.tomwodz.film.domain.film;

import org.junit.jupiter.api.Test;
import pl.tomwodz.film.domain.client.OMDbClient;
import pl.tomwodz.film.domain.film.dto.FilmRequestDto;
import pl.tomwodz.film.domain.film.dto.FilmResponseDto;
import pl.tomwodz.film.infrastructure.film.error.FilmNotFoundException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FilmFacadeTest {

    FilmRepository filmRepository = new FilmFacadeTestImpl();

    OMDbClient omdbClinet = new OMDbClientTestImpl();

    FilmFacade filmFacade = new FilmConfiguration().filmFacade(filmRepository, omdbClinet);
    FilmRequestDto filmRequestDto = FilmRequestDto
            .builder()
            .title("fdsfdsdfsfsd")
            .plot("sdffsdfdsfsd")
            .director("fdsfsddfs")
            .genre("sdfsdfds")
            .poster("fdsfsddsdfs")
            .imdbID("tt123")
            .build();

    FilmRequestDto filmRequestDto2 = FilmRequestDto
            .builder()
            .title("dfdsfdsfdsffsdfsd")
            .plot("dsfdsfsdfdf")
            .director("sfdsfsdfds")
            .genre("gtgttggt")
            .poster("fsdfdsfsd")
            .imdbID("tt123")
            .build();

    FilmRequestDto filmRequestDtoWithEmptyImdbID = FilmRequestDto
            .builder()
            .title("fdsfdsdfsfsd")
            .plot("sdffsdfdsfsd")
            .director("fdsfsddfs")
            .genre("sdfsdfds")
            .poster("fdsfsddsdfs")
            .imdbID("")
            .build();


    @Test
    void ShouldBeAbleSaveFilm() {

        //given

        //when
        FilmResponseDto filmResponseDto = this.filmFacade.saveFilm(filmRequestDto);

        //then
        assertThat(filmResponseDto.id()).isNotNull();
        assertThat(filmResponseDto.title()).isEqualTo(filmRequestDto.title());

    }

    @Test
    void ShouldBeAbleSaveFilmWithEmptyImdbID() {

        //given

        //when
        FilmResponseDto filmResponseDto = this.filmFacade.saveFilm(filmRequestDtoWithEmptyImdbID);

        //then
        assertThat(filmResponseDto.id()).isNotNull();
        assertThat(filmResponseDto.title()).isEqualTo(filmRequestDto.title());

    }

    @Test
    void ShouldBeUnableSaveFilmWithImdbIDExists() {

        //given

        //when
        FilmResponseDto filmResponseDto = this.filmFacade.saveFilm(filmRequestDto);
        FilmResponseDto filmResponseDto2 = this.filmFacade.saveFilm(filmRequestDto2);

        //then
        assertThat(filmResponseDto.title()).isEqualTo(filmResponseDto2.title());

    }

    @Test
    void ShouldBeAbleFindFilmByIdWhenWasSaved() {

        //given
        FilmResponseDto filmResponseDtoSaved = this.filmFacade.saveFilm(filmRequestDto);

        //when
        FilmResponseDto filmResponseDtoFounded = this.filmFacade.findFilmById(filmResponseDtoSaved.id());

        //then
        assertThat(filmResponseDtoFounded.id()).isNotNull();
        assertThat(filmResponseDtoFounded.title()).isEqualTo(filmResponseDtoSaved.title());
        assertThat(filmResponseDtoFounded.plot()).isEqualTo(filmResponseDtoSaved.plot());

    }

    @Test
    void ShouldBeAbleFindFilmsWasSaved() {

        //given
        this.filmFacade.saveFilm(filmRequestDto);
        this.filmFacade.saveFilm(filmRequestDtoWithEmptyImdbID);

        //when
        List<FilmResponseDto> films = this.filmFacade.findAllFilms();

        //then
        assertThat(films).isNotNull();
        assertThat(films.size()).isEqualTo(2);
    }

    @Test
    void ShouldThrowNotFoundExceptionWhenFilmNotFound() {

        //given
        //when
        //then
        assertThrows(FilmNotFoundException.class, () -> this.filmFacade.findFilmById(0L));

    }


}