package pl.tomwodz.film.infrastructure.film.error;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.tomwodz.film.infrastructure.film.controller.FilmRestController;

@ControllerAdvice(assignableTypes = FilmRestController.class)
@Log4j2
public class FilmErrorHandler {

    @ExceptionHandler(FilmNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorFilmResponseDto handleNotFoundException(FilmNotFoundException exception){
        log.warn("FilmNotFoundException error while accessing user");
        return new ErrorFilmResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

}
