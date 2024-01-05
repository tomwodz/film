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

import java.time.LocalDateTime;
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

    public List<FavouriteFilmResponseDto> findFavouriteFilmForUser(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("not found user by username:" + username));
        List<FavouriteFilmResponseDto> response = favouriteMapper.mapFromSetFavouriteFilmToListFavouriteFilmResponseDto(user.getFavouriteFilm());
        return response;
    }

    public List<FavouriteFilmResponseDto> addFavouriteFilmForUser(FavouriteFilmRequestDto favouriteFilmRequestDto) {
        User userToExchange = this.userRepository.findByUsername(favouriteFilmRequestDto.username())
                .orElseThrow(() -> new UserNotFoundException("not found user by username:" + favouriteFilmRequestDto.username()));
        Set<Integer> setBox = favouriteFilmRequestDto.idFavouriteFilm();
        Set<Film> setFilmUser = userToExchange.getFavouriteFilm();
        Set<Film> setFilmToAdd = new HashSet<>();
        setBox.stream()
                .forEach(n -> setFilmToAdd.add(get(n.longValue())));
        setFilmToAdd.stream()
                        .forEach(f -> f.setDateLastLikes(LocalDateTime.now()));
        setFilmToAdd.stream()
                .forEach(f -> setFilmUser.add(f));
        userToExchange.setFavouriteFilm(setFilmUser);
        User userSaved = this.userRepository.save(userToExchange);
        List<FavouriteFilmResponseDto> response = favouriteMapper.mapFromSetFavouriteFilmToListFavouriteFilmResponseDto(userSaved.getFavouriteFilm());
        return response;
    }

    public List<FavouriteFilmResponseDto> deleteFavouriteFilmForUser(FavouriteFilmRequestDto favouriteFilmRequestDto) {
        User userToExchange = this.userRepository.findByUsername(favouriteFilmRequestDto.username())
                .orElseThrow(() -> new UserNotFoundException("not found user by username:" + favouriteFilmRequestDto.username()));
        Set<Integer> setBox = favouriteFilmRequestDto.idFavouriteFilm();
        Set<Film> setFilmUser = userToExchange.getFavouriteFilm();
        Set<Film> setFilmToDelete = new HashSet<>();
        setBox.stream()
                .forEach(n -> setFilmToDelete.add(get(n.longValue())));
        setFilmToDelete.stream()
                .forEach(f -> setFilmUser.remove(f));
        userToExchange.setFavouriteFilm(setFilmUser);
        User userSaved = this.userRepository.save(userToExchange);
        List<FavouriteFilmResponseDto> response = favouriteMapper.mapFromSetFavouriteFilmToListFavouriteFilmResponseDto(userSaved.getFavouriteFilm());
        return response;
    }

    private Film get(Long id) {
        return this.filmRepository.findById(id)
                .orElseThrow(() -> new FilmNotFoundException("not found film id:" + id));
    }
}
