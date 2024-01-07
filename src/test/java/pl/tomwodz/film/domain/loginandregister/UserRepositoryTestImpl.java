package pl.tomwodz.film.domain.loginandregister;

import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepositoryTestImpl implements UserRepository{

    Map<Long, User> inMemoryDatabase = new ConcurrentHashMap<>();
    Random random = new Random();

    @Override
    public Optional<User> findByUsername(String username) {
        return inMemoryDatabase.values()
                .stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    @Override
    public User save(User user) {
        Long id = random.nextLong(1, 200000);
        if(user.getId()!=null){
            id = user.getId();
        }
        this.inMemoryDatabase.put(id, user);
        return new User(id,
                inMemoryDatabase.get(id).getUsername(),
                inMemoryDatabase.get(id).getPassword(),
                inMemoryDatabase.get(id).getFavouriteFilm());
    }

    @Override
    public boolean existsByUsername(String username) {
        return inMemoryDatabase.values()
                .stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst().isPresent();
    }


}
