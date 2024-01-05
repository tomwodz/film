package pl.tomwodz.film.domain.film;

import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class FilmFacadeTestImpl implements FilmRepository {

    Random random = new Random();
    Map<Long, Film> inMemoryDatabase = new ConcurrentHashMap<>();
    @Override
    public Optional<Film> findById(Long id) {
        if(inMemoryDatabase.containsKey(id)){
            Film film = inMemoryDatabase.get(id);
            film.setId(id);
            return
                    Optional.of(film);
        } else
            return Optional.empty();
    }

    @Override
    public Film save(Film film) {
        Long id = random.nextLong(1,200000);
        if(film.getId() != null) id = film.getId();
        this.inMemoryDatabase.put(id, film);
        film.setId(id);
        return film;
    }

    @Override
    public Optional<Film> findByImdbID(String imdbID) {
        return Optional.empty();
    }
}
