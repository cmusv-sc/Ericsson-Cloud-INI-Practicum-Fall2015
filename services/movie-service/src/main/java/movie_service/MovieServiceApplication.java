package movie_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;

import movie_service.domain.MovieRepository;

@EnableEurekaClient
@SpringBootApplication
public class MovieServiceApplication {

	@Autowired
	private MovieRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(MovieServiceApplication.class, args);
	}
	
	@Bean
	public AlwaysSampler defaultSampler() {
		return new AlwaysSampler();
	}

}
