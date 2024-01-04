package pl.tomwodz.film.infrastructure.favourite.error;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.tomwodz.film.domain.favourite.UserNotFoundException;
import pl.tomwodz.film.infrastructure.favourite.controller.FavouriteRestController;
import pl.tomwodz.film.infrastructure.film.error.ErrorFilmResponseDto;
import pl.tomwodz.film.infrastructure.film.error.FilmNotFoundException;


@ControllerAdvice(assignableTypes = FavouriteRestController.class)
@Log4j2
public class FavouriteRestControllerErrorHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorUserNotFoundResponseDto handleUserNotFound(UserNotFoundException exception){
        log.warn("UserNotFoundException error");
        return new ErrorUserNotFoundResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FilmNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorFilmResponseDto handleNotFoundException(FilmNotFoundException exception){
        log.warn("FilmNotFoundException error");
        return new ErrorFilmResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND);
    }


}
