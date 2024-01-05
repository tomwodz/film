package pl.tomwodz.film.domain.film;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
    private String imdbID;

    private LocalDateTime dateCreation;
    private LocalDateTime dateLastLikes;

    public Film(String title, String plot, String director, String genre, String poster, String imdbID, LocalDateTime dateCreation, LocalDateTime dateLastLikes) {
        this.title = title;
        this.plot = plot;
        this.director = director;
        this.genre = genre;
        this.poster = poster;
        this.imdbID = imdbID;
        this.dateCreation = dateCreation;
        this.dateLastLikes = dateLastLikes;
    }
}
