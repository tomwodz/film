package pl.tomwodz.film.domain.favourite;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pl.tomwodz.film.domain.favourite.dto.FavouriteFilmRequestDto;
import pl.tomwodz.film.domain.favourite.dto.FavouriteFilmResponseDto;
import pl.tomwodz.film.domain.film.Film;
import pl.tomwodz.film.domain.film.FilmRepository;
import pl.tomwodz.film.domain.loginandregister.User;
import pl.tomwodz.film.domain.loginandregister.UserRepository;
import pl.tomwodz.film.infrastructure.film.error.FilmNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Transactional
@Log4j2
public class FavouriteFacade {

    private final UserRepository userRepository;
    private final FilmRepository filmRepository;
    private final FavouriteMapper favouriteMapper;

    public List<FavouriteFilmResponseDto> findFavouriteFilmForUser(Long idUser) {
        User user = this.userRepository.findById(idUser)
                .orElseThrow(() -> new UserNotFoundException("not found user id:" + idUser));
        List<FavouriteFilmResponseDto> response = favouriteMapper.mapFromSetFavouriteFilmToListFavouriteFilmResponseDto(user.getFavouriteFilm());
        return response;
    }

    public List<FavouriteFilmResponseDto> addFavouriteFilmForUser(FavouriteFilmRequestDto favouriteFilmRequestDto) {
        User user = this.userRepository.findById(favouriteFilmRequestDto.idUser())
                .orElseThrow(() -> new UserNotFoundException("not found user id:" + favouriteFilmRequestDto.idUser()));
        Set<Integer> setBox = favouriteFilmRequestDto.idFavouriteFilm();
        Set<Film> setFilmUser = user.getFavouriteFilm();
        Set<Film> setFilmToAdd = new HashSet<>();
        setBox.stream()
                .forEach(n -> setFilmToAdd.add(get(n.longValue())));
        setFilmToAdd.stream()
                .forEach(f -> setFilmUser.add(f));
        user.setFavouriteFilm(setFilmUser);
        List<FavouriteFilmResponseDto> response = favouriteMapper.mapFromSetFavouriteFilmToListFavouriteFilmResponseDto(setFilmToAdd);
        return response;
    }

    private Film get(Long id) {
        return this.filmRepository.findById(id)
                .orElseThrow(() -> new FilmNotFoundException("not found film id:" + id));
    }
}
