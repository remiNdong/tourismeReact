package fr.tourisme.react;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TourismeReactApplication  implements CommandLineRunner {

	@Value("${jwt.secret}")
	private String jwt;
	public static void main(String[] args) {
		SpringApplication.run(TourismeReactApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("property jwt value is: " + jwt);
	}
}
