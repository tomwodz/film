package pl.tomwodz.film.domain.film;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface FilmRepository extends Repository <Film, Long> {

    List<Film> findAll();

    List<Film> findTop10ByOrderByDateLastLikesDesc();

    Optional<Film> findById(Long id);

    Film save(Film film);

    Optional<Film> findByImdbID(String imdbID);

    List<Film> findByTitleLike(String title);

    List<Film> findByDirectorLike(String director);

    boolean existsByImdbID(String imdbID);

}
