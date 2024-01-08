package pl.tomwodz.film.domain.loginandregister;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {

    User save(User user);

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

}
