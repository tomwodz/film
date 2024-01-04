package pl.tomwodz.film.domain.loginandregister;

import pl.tomwodz.film.domain.loginandregister.dto.UserRegisterRequestDto;

class UserFactory {

    User mapFromUserRegisterRequestDto(UserRegisterRequestDto userRegisterRequestDto){
        return new User(
                userRegisterRequestDto.username(),
                userRegisterRequestDto.password());
    }

}
