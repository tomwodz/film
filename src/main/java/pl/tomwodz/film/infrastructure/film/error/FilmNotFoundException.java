package pl.tomwodz.film.infrastructure.film.error;

public class FilmNotFoundException extends RuntimeException {
    public FilmNotFoundException(String message) {
        super(message);
    }
}
