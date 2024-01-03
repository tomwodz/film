package pl.tomwodz.film.domain.film;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
    private String description;
    private String director;
    private String genre;
    private String image;

    public Film(String title, String description, String director, String genre, String image) {
        this.title = title;
        this.description = description;
        this.director = director;
        this.genre = genre;
        this.image = image;
    }
}
