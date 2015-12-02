package ratings_service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;

import edu.cmu.ini.ericsson.practicum.models.ratingsService.Rating;
import ratings_service.domain.RatingRepository;

@SpringBootApplication
@EnableEurekaClient
public class RatingsServiceApplication implements CommandLineRunner {
	
	static final int USER_NUMBER = 10000;
	
	@Autowired
	private RatingRepository repository;
	
    public static void main(String[] args) {
        SpringApplication.run(RatingsServiceApplication.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		repository.deleteAll();
		
		Random rand = new Random();
		for (int i=1; i<1125135; i++) {
			int n = rand.nextInt(USER_NUMBER);
			int score = rand.nextInt(10) + 1;
			repository.save(new Rating(String.valueOf(i), String.valueOf(i), String.valueOf(n), score));
			System.out.println("ratings inserted " + i);
		}
		
	}
	
	 @Bean
	public AlwaysSampler defaultSampler() {
		return new AlwaysSampler();
	}
}
