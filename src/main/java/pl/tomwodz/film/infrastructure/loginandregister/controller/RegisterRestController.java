package pl.tomwodz.film.infrastructure.loginandregister.controller;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.tomwodz.film.domain.loginandregister.LoginAndRegisterFacade;
import pl.tomwodz.film.domain.loginandregister.dto.RegistrationResultDto;
import pl.tomwodz.film.domain.loginandregister.dto.UserRegisterRequestDto;

@RestController
@AllArgsConstructor
public class RegisterRestController {

    private final LoginAndRegisterFacade loginAndRegisterFacade;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Operation(description = "Register User", summary = "Register User")
    @PostMapping("/register")
    public ResponseEntity<RegistrationResultDto> register(@RequestBody @Valid UserRegisterRequestDto userRegisterRequestDto) {
        String encodedPassword = bCryptPasswordEncoder.encode(userRegisterRequestDto.password());
        RegistrationResultDto registerResult = loginAndRegisterFacade.register(
                new UserRegisterRequestDto(userRegisterRequestDto.username(), encodedPassword));
        return ResponseEntity.status(HttpStatus.CREATED).body(registerResult);
    }

}
