package pl.tomwodz.film.infrastructure.favourite.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.film.domain.favourite.FavouriteFacade;
import pl.tomwodz.film.domain.favourite.dto.FavouriteFilmRequestDto;
import pl.tomwodz.film.domain.favourite.dto.FavouriteFilmResponseDto;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/favourite")
public class FavouriteRestController {

    private final FavouriteFacade favouriteFacade;

    @Operation(description = "Add favourite Films for User", summary = "Add favourite Films for User")
    @PostMapping()
    public ResponseEntity<List<FavouriteFilmResponseDto>> addFavouriteFilmForUser(@RequestBody @Valid FavouriteFilmRequestDto favouriteFilmRequestDto) {
        return ResponseEntity.ok(this.favouriteFacade.addFavouriteFilmForUser(favouriteFilmRequestDto));
    }

    @Operation(description = "Get favourite Films for User", summary = "Get favourite Films for User")
    @GetMapping(path = "/{idUser}")
    public ResponseEntity<List<FavouriteFilmResponseDto>> findFavouriteFilmForUser(@PathVariable Long idUser) {
        return ResponseEntity.ok(this.favouriteFacade.findFavouriteFilmForUser(idUser));
    }

}
