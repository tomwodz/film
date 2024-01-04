package pl.tomwodz.film.domain.film;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface FilmRepository extends Repository <Film, Long> {

    Optional<Film> findById(Long id);

    Film save(Film film);

    Optional<Film> findByImdbID(String imdbID);


}
