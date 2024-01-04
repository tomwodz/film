package pl.tomwodz.film.domain.loginandregister;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.tomwodz.film.domain.film.Film;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    @ManyToMany
    private Set<Film> favouriteFilm;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
