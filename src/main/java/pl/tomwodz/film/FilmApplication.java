package pl.tomwodz.film;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FilmApplication {
    public static void main(String[] args) {
        SpringApplication.run(FilmApplication.class);
    }
}
