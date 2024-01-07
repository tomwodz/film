package pl.tomwodz.film.infrastructure.config;

import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class Config {
    @Bean
    public Decoder feignDecoder() {
        return new JacksonDecoder();
    }

    @Bean
    Clock clock() {
        return Clock.systemUTC();
    }
}