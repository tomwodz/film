package pl.tomwodz.film.domain.loginandregister;

public class LoginAlreadyExistException extends RuntimeException {
    public LoginAlreadyExistException(String message) {
        super(message);
    }
}
