package pl.tomwodz.film.infrastructure.loginandregister.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.tomwodz.film.infrastructure.loginandregister.controller.dto.JwtResponseDto;
import pl.tomwodz.film.infrastructure.loginandregister.controller.dto.TokenRequestDto;
import pl.tomwodz.film.infrastructure.security.jwt.JwtAuthenticatorFacade;


@RestController
@AllArgsConstructor
public class TokenRestController {

    private final JwtAuthenticatorFacade jwtAuthenticatorFacade;
    @PostMapping("/token")
    public ResponseEntity<JwtResponseDto> authenticateAndGenerateToken(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        JwtResponseDto jwtResponseDto = jwtAuthenticatorFacade.authenticateAndGenerateToken(tokenRequestDto);
        return ResponseEntity.ok(jwtResponseDto);
    }

}
