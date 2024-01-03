package pl.tomwodz.film.domain.film;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

interface FilmRepository extends Repository <Film, Long> {

    Optional<Film> findById(Long id);

    Film save(Film film);

}
