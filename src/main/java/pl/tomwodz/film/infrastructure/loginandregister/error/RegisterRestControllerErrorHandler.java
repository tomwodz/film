package pl.tomwodz.film.infrastructure.loginandregister.error;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.tomwodz.film.domain.favourite.UserNotFoundException;
import pl.tomwodz.film.domain.loginandregister.LoginAlreadyExistException;
import pl.tomwodz.film.infrastructure.loginandregister.controller.RegisterRestController;


@ControllerAdvice(assignableTypes = RegisterRestController.class)
@Log4j2
public class RegisterRestControllerErrorHandler {

    @ExceptionHandler(LoginAlreadyExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorRegisterResponseDto handleBadLogin(LoginAlreadyExistException exception){
        log.warn("LoginAlreadyExistException error");
        return new ErrorRegisterResponseDto(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
