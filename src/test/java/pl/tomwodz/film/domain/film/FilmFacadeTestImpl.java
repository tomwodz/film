package pl.tomwodz.film.domain.film;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class FilmFacadeTestImpl implements FilmRepository {

    Random random = new Random();
    Map<Long, Film> inMemoryDatabase = new ConcurrentHashMap<>();

    @Override
    public Optional<Film> findById(Long id) {
        if (inMemoryDatabase.containsKey(id)) {
            Film film = inMemoryDatabase.get(id);
            film.setId(id);
            return
                    Optional.of(film);
        } else
            return Optional.empty();
    }

    @Override
    public Film save(Film film) {
        if (existsByImdbID(film.getImdbID())) {
            return findByImdbID(film.getImdbID()).get();
        }
        Long id = random.nextLong(1, 200000);
        if (film.getId() != null) id = film.getId();
        this.inMemoryDatabase.put(id, film);
        film.setId(id);
        return film;
    }

    @Override
    public Optional<Film> findByImdbID(String imdbID) {
        List<Long> keys = new ArrayList<>();
        inMemoryDatabase.entrySet().stream()
                .forEach(n -> keys.add(n.getKey()));
        if (keys.stream().
                filter(n -> inMemoryDatabase.get(n).getImdbID().equals(imdbID))
                .findFirst()
                .isPresent()) {
            Long film = keys.stream().
                    filter(n -> inMemoryDatabase.get(n).getImdbID().equals(imdbID))
                    .findFirst().get();
            return Optional.of(inMemoryDatabase.get(film));
        }
        return Optional.empty();
    }

    @Override
    public boolean existsByImdbID(String imdbID) {
        List<Long> keys = new ArrayList<>();
        inMemoryDatabase.entrySet().stream()
                .forEach(n -> keys.add(n.getKey()));
        if (keys.stream().
                filter(n -> inMemoryDatabase.get(n).getImdbID().equals(imdbID))
                .findFirst()
                .isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public List<Film> findAll() {
        List<Film> films = new ArrayList<>();
        List<Long> keys = new ArrayList<>();
        inMemoryDatabase.entrySet().stream()
                .forEach(n -> keys.add(n.getKey()));
        keys.stream()
                .forEach(n -> films.add(inMemoryDatabase.get(n)));
        return films;
    }

    @Override
    public List<Film> findByTitleLike(String title) {
        return Collections.emptyList();
    }

    @Override
    public List<Film> findByDirectorLike(String director) {
        return null;
    }

    @Override
    public List<Film> findTop10ByOrderByDateLastLikesDesc() {
        return null;
    }
}
