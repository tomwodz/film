package pl.tomwodz.film.infrastructure.film.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.film.domain.film.FilmFacade;
import pl.tomwodz.film.domain.film.dto.FilmRequestDto;
import pl.tomwodz.film.domain.film.dto.FilmResponseDto;
import pl.tomwodz.film.domain.film.dto.FilmSearchRequestDto;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/film")
public class FilmRestController {

    private final FilmFacade filmFacade;

    @Operation(description = "All films", summary = "All films")
    @GetMapping
    public ResponseEntity<List<FilmResponseDto>> findAllFilms() {
        return ResponseEntity.ok(this.filmFacade.findAllFilms());
    }

    @Operation(description = "Film by id", summary = "Film by id")
    @GetMapping(path = "/{id}")
    public ResponseEntity<FilmResponseDto> findFilmById(@PathVariable Long id) {
        return ResponseEntity.ok(this.filmFacade.findFilmById(id));
    }

    @Operation(description = "Save film", summary = "Save film")
    @PostMapping
    public ResponseEntity<FilmResponseDto> saveFilm(@RequestBody @Valid FilmRequestDto filmRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.filmFacade.saveFilm(filmRequestDto));
    }

    @Operation(description = "Search film by: title, director", summary = "Search film by: title, director")
    @PostMapping(path = "/search")
    public ResponseEntity<List<FilmResponseDto>> searchFilm(@RequestBody FilmSearchRequestDto filmSearchRequestDto) {
        return ResponseEntity.ok(this.filmFacade.searchFilm(filmSearchRequestDto));
    }

    @Operation(description = "Search film by imdbID in Database", summary = "Search film by imdbID in Database")
    @GetMapping(path = "/search/imdbID/{imdbID}")
    public ResponseEntity<FilmResponseDto> findFilmByImdbIDInDatabase(@PathVariable String imdbID) {
        return ResponseEntity.ok(this.filmFacade.findFilmResponseByImdbIDInDatabase(imdbID));
    }

    @Operation(description = "Search film by title in OMBd", summary = "Search film by title in OMBd")
    @GetMapping(path = "/search/title/{title}")
    public ResponseEntity<FilmResponseDto> findFilmByTitleOnOMDd(@PathVariable String title) {
        return ResponseEntity.ok(this.filmFacade.findFilmByTitleOnOMDd(title));
    }

    @Operation(description = "Last 10 favourite Films", summary = "Last 10 favourite Films")
    @GetMapping(path = "/lastfavouritetop10")
    public ResponseEntity<List<FilmResponseDto>> findTop10ByOrderByDateLast() {
        return ResponseEntity.ok(this.filmFacade.findTop10ByOrderByDateLast());
    }

}
