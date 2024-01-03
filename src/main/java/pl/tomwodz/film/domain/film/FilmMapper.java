package pl.tomwodz.film.domain.film;

import pl.tomwodz.film.domain.film.dto.FilmResponseDto;

class FilmMapper {
    public static FilmResponseDto mapFromFilmToFilmResponseDto(Film film){
        return FilmResponseDto.builder()
                .id(film.getId())
                .title(film.getTitle())
                .description(film.getDescription())
                .director(film.getDirector())
                .genre(film.getGenre())
                .image(film.getImage())
                .build();
    }
}
