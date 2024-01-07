package pl.tomwodz.film.domain.loginandregister;

import pl.tomwodz.film.domain.loginandregister.dto.UserDto;

class UserMapper {

    public static UserDto mapFromUserToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}
