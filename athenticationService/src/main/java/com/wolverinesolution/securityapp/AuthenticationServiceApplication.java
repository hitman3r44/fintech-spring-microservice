package com.wolverinesolution.securityapp;

import com.wolverinesolution.securityapp.user.model.User;
import com.wolverinesolution.securityapp.user.repository.UserRepository;
import org.jasypt.util.text.StrongTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class AuthenticationServiceApplication implements CommandLineRunner{
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
		textEncryptor.setPassword("wolverine");
		String encryptedPassword = textEncryptor.encrypt("12345");

		userRepository.save(new User("John", encryptedPassword));
		userRepository.save(new User("Mindaugas", encryptedPassword));
	}

}
