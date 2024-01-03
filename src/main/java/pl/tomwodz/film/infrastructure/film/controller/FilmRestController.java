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

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/film")
public class FilmRestController {

    private final FilmFacade filmFacade;

    @Operation(description = "Film by id", summary = "Film by id")
    @GetMapping(path = "/{id}")
    public ResponseEntity<FilmResponseDto> findFilmById(@PathVariable Long id){
        return ResponseEntity.ok(this.filmFacade.findFilmById(id));

    }

    @Operation(description = "Save film", summary = "Save film")
    @PostMapping
    public ResponseEntity<FilmResponseDto> saveFilm(@RequestBody @Valid FilmRequestDto filmRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.filmFacade.saveFilm(filmRequestDto));
    }
}
