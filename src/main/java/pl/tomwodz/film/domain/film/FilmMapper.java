package pl.tomwodz.film.domain.film;

import pl.tomwodz.film.domain.film.dto.FilmResponseDto;

import java.util.List;

class FilmMapper {
    public static FilmResponseDto mapFromFilmToFilmResponseDto(Film film) {
        return FilmResponseDto.builder()
                .id(film.getId())
                .title(film.getTitle())
                .plot(film.getPlot())
                .director(film.getDirector())
                .genre(film.getGenre())
                .poster(film.getPoster())
                .imdbID(film.getImdbID())
                .build();
    }

    public static List<FilmResponseDto> mapFromListFilmToListFilmResponseDto(List<Film> films) {
        return films.stream()
                .map(f -> mapFromFilmToFilmResponseDto(f))
                .toList();
    }
}
