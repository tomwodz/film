package pl.tomwodz.film.domain.loginandregister;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import pl.tomwodz.film.domain.loginandregister.dto.RegistrationResultDto;
import pl.tomwodz.film.domain.loginandregister.dto.UserDto;
import pl.tomwodz.film.domain.loginandregister.dto.UserRegisterRequestDto;

@AllArgsConstructor
public class LoginAndRegisterFacade {
    private final UserRepository userRepository;
    private final UserFactory userFactory;

    public RegistrationResultDto register(UserRegisterRequestDto userRegisterRequestDto) {
        if (this.userRepository.existsByUsername(userRegisterRequestDto.username())) {
            throw new LoginAlreadyExistException("Username is already busy: " + userRegisterRequestDto.username());
        }
        User userToSave = userFactory.mapFromUserRegisterRequestDto(userRegisterRequestDto);
        User userSaved = userRepository.save(userToSave);
        return RegistrationResultDto.builder()
                .id(userSaved.getId())
                .username(userSaved.getUsername())
                .registered(true)
                .build();
    }

    public UserDto findByUsername(String username) {
        return this.userRepository.findByUsername(username)
                .map(UserMapper::mapFromUserToUserDto)
                .orElseThrow(() -> new BadCredentialsException("User not found :" + username));
    }


}
