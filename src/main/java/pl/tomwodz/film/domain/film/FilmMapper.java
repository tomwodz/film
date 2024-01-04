package pl.tomwodz.film.domain.film;

import pl.tomwodz.film.domain.film.dto.FilmResponseDto;

class FilmMapper {
    public static FilmResponseDto mapFromFilmToFilmResponseDto(Film film){
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
}
