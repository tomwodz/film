package pl.tomwodz.film.infrastructure.cilent.error;

public class ClientException extends RuntimeException {
    public ClientException(String message) {
        super(message);
    }
}
