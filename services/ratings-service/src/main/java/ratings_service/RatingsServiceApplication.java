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
		repository.save(new Rating("1", "1", "Abhishek", 1));
		repository.save(new Rating("11", "1", "Yifan", 5));
		repository.save(new Rating("2", "3", "Mayank", 5));
		repository.save(new Rating("12", "3", "Alex", 5));
		repository.save(new Rating("3", "2", "Anshima", 4));
		repository.save(new Rating("13", "2", "Ruiqi", 4));
		repository.save(new Rating("4", "4", "Ruiqi", 5));
		repository.save(new Rating("14", "4", "Yifan", 3));
		repository.save(new Rating("5", "5", "Abhishek", 1));
		repository.save(new Rating("15", "5", "Anshima", 5));
		repository.save(new Rating("6", "6", "Mayank", 5));
		repository.save(new Rating("16", "6", "Yifan", 5));
		repository.save(new Rating("7", "7", "Yifan", 4));
		repository.save(new Rating("17", "7", "Ruiqi", 4));
		repository.save(new Rating("8", "8", "Abhishek", 2));
		repository.save(new Rating("18", "8", "Anshima", 4));
		repository.save(new Rating("9", "9", "Ruiqi", 2));
		repository.save(new Rating("19", "9", "Alex", 4));
		repository.save(new Rating("10", "10", "Anshima", 4));
		repository.save(new Rating("20", "10", "Yifan", 4));
		
		for (Rating rating : repository.findAll()) {
			System.out.println(rating);
		}
	}
}
