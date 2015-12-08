package ratings_service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import edu.cmu.ini.ericsson.practicum.models.ratingsService.Rating;
import ratings_service.domain.RatingRepository;

@SpringBootApplication
@EnableEurekaClient
public class RatingsServiceApplication {

	static final int USER_NUMBER = 10000;

	@Autowired
	private RatingRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(RatingsServiceApplication.class, args);
    }
}
