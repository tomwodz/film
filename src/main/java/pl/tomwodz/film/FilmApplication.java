package pl.tomwodz.film;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import pl.tomwodz.film.infrastructure.security.jwt.JwtConfigurationProperties;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties({JwtConfigurationProperties.class})
public class FilmApplication {
    public static void main(String[] args) {
        SpringApplication.run(FilmApplication.class);
    }
}
