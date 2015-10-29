package movie_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import movie_service.domain.MovieRepository;

@EnableEurekaClient
@SpringBootApplication
public class MovieServiceApplication {

	@Autowired
	private MovieRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(MovieServiceApplication.class, args);
	}
}
