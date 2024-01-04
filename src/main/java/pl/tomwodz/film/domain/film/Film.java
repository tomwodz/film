package pl.tomwodz.film.domain.film;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="films")
@NoArgsConstructor
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String plot;
    private String director;
    private String genre;
    private String poster;
    @Column(unique = true)
    private String imdbID;

    public Film(String title, String plot, String director, String genre, String poster, String imdbID) {
        this.title = title;
        this.plot = plot;
        this.director = director;
        this.genre = genre;
        this.poster = poster;
        this.imdbID = imdbID;
    }
}
