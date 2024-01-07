package pl.tomwodz.film.domain.loginandregister;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoginAndRegisterConfiguration {

    @Bean
    LoginAndRegisterFacade loginAndRegisterFacade(UserRepository userRepository) {
        UserFactory userFactory = new UserFactory();
        return new LoginAndRegisterFacade(userRepository, userFactory);
    }
}
