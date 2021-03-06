package com.wolverinesolution.lendingengine;

import com.github.javafaker.Faker;
import com.wolverinesolution.lendingengine.domain.model.Balance;
import com.wolverinesolution.lendingengine.domain.model.User;
import com.wolverinesolution.lendingengine.domain.repository.UserRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.stream.IntStream;

@SpringBootApplication
//@OpenAPIDefinition(info = @Info(title = "LandingEngine API", version = "1.0", description = "LandingEngine Information"))
@EnableSwagger2
public class LendingengineApplication implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(LendingengineApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        userRepository.save(new User("Mindaugas", "Mindaugas", "Khandelwal", 25, "Software Developer", new Balance()));
        userRepository.save(new User("John", "John", "Agrawal", 26, "Software Developer", new Balance()));
//        userRepository.save(new User("Gaurav", "Gaurav", "Patidar", 25, "Software Developer", new Balance()));
//        userRepository.save(new User("Munendra", "Munendra", "Dhayal", 23, "Business", new Balance()));
//        userRepository.save(new User("Lekhu", "Lekhu", "Kumawat", 25, "Unemployed", new Balance()));

    }

    @Bean
    CommandLineRunner runner(UserRepository userRepository) {
        Faker faker = new Faker();
        return args -> IntStream.range(0, 10).forEach(i -> userRepository.save(new User(
                faker.name().username(),
                faker.name().firstName(),
                faker.name().lastName(),
                faker.number().numberBetween(20, 50),
                faker.job().position(),
                new Balance()
        )));
    }
}
