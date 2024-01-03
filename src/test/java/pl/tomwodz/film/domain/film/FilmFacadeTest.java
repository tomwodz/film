package pl.tomwodz.film.domain.film;

import org.junit.jupiter.api.Test;
import pl.tomwodz.film.domain.film.dto.FilmRequestDto;
import pl.tomwodz.film.domain.film.dto.FilmResponseDto;
import pl.tomwodz.film.infrastructure.film.error.FilmNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FilmFacadeTest {

    FilmRepository filmRepository = new FilmFacadeTestImpl();

    FilmFacade filmFacade = new FilmConfiguration().filmFacade(filmRepository);
    FilmRequestDto filmRequestDto = FilmRequestDto
    .builder()
            .title("fdsfdsdfsfsd")
            .description("sdffsdfdsfsd")
            .director("fdsfsddfs")
            .genre("sdfsdfds")
            .image("fdsfsddsdfs")
            .build();


    @Test
    void ShouldBeAbleSaveFilm(){

        //given

        //when
        FilmResponseDto filmResponseDto = this.filmFacade.saveFilm(filmRequestDto);

        //then
        assertThat(filmResponseDto.id()).isNotNull();
        assertThat(filmResponseDto.title()).isEqualTo(filmRequestDto.title());

    }

    @Test
    void ShouldBeAbleFindFilmByIdWhenWasSaved(){

        //given
        FilmResponseDto filmResponseDtoSaved = this.filmFacade.saveFilm(filmRequestDto);

        //when
        FilmResponseDto filmResponseDtoFounded = this.filmFacade.findFilmById(filmResponseDtoSaved.id());

        //then
        assertThat(filmResponseDtoFounded.id()).isNotNull();
        assertThat(filmResponseDtoFounded.title()).isEqualTo(filmResponseDtoSaved.title());
        assertThat(filmResponseDtoFounded.description()).isEqualTo(filmResponseDtoSaved.description());

    }

    @Test
    void ShouldThrowNotFoundExceptionWhenFilmNotFound(){

        //given
        //when
        //then
        assertThrows(FilmNotFoundException.class, () -> this.filmFacade.findFilmById(0L));

    }


}