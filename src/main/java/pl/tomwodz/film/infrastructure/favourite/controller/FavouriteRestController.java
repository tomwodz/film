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
    @PostMapping(path = "/add")
    public ResponseEntity<List<FavouriteFilmResponseDto>> addFavouriteFilmForUser(@RequestBody @Valid FavouriteFilmRequestDto favouriteFilmRequestDto) {
        return ResponseEntity.ok(this.favouriteFacade.addFavouriteFilmForUser(favouriteFilmRequestDto));
    }

    @Operation(description = "Delete favourite Films for User", summary = "Delete favourite Films for User")
    @DeleteMapping(path = "/delete")
    public ResponseEntity<List<FavouriteFilmResponseDto>> deleteFavouriteFilmForUser(@RequestBody @Valid FavouriteFilmRequestDto favouriteFilmRequestDto) {
        return ResponseEntity.ok(this.favouriteFacade.deleteFavouriteFilmForUser(favouriteFilmRequestDto));
    }

    @Operation(description = "Get favourite Films for User", summary = "Get favourite Films for User")
    @GetMapping(path = "/{username}")
    public ResponseEntity<List<FavouriteFilmResponseDto>> findFavouriteFilmForUser(@PathVariable String username) {
        return ResponseEntity.ok(this.favouriteFacade.findFavouriteFilmForUser(username));
    }

}
