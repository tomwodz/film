package pl.tomwodz.film.domain.favourite;

import pl.tomwodz.film.domain.favourite.dto.FavouriteFilmResponseDto;
import pl.tomwodz.film.domain.film.Film;

import java.util.List;
import java.util.Set;

class FavouriteMapper {

    public static List<FavouriteFilmResponseDto> mapFromSetFavouriteFilmToListFavouriteFilmResponseDto(Set<Film> favouriteFilm){
        return favouriteFilm.stream()
                .map(f -> mapFromFilmToFavouriteFilmResponseDto(f))
                .toList();
    }
    
    public static FavouriteFilmResponseDto mapFromFilmToFavouriteFilmResponseDto(Film film){
        return FavouriteFilmResponseDto.builder()
                .id(film.getId())
                .title(film.getTitle())
                .description(film.getPlot())
                .director(film.getDirector())
                .genre(film.getGenre())
                .image(film.getPoster())
                .build();
    }
    
}
