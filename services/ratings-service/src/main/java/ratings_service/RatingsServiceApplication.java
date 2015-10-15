package ratings_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import ratings_service.domain.Rating;
import ratings_service.domain.RatingRepository;

@SpringBootApplication
@EnableEurekaClient
public class RatingsServiceApplication implements CommandLineRunner {

	@Autowired
	private RatingRepository repository;
	
    public static void main(String[] args) {
        SpringApplication.run(RatingsServiceApplication.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		repository.deleteAll();
		repository.save(new Rating("1", "1", "1", 5));
		repository.save(new Rating("2", "1", "2", 4));
		repository.save(new Rating("3", "2", "1", 4));
		
		for (Rating rating : repository.findAll()) {
			System.out.println(rating);
		}
	}
}
