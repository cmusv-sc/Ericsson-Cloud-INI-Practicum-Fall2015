package movie_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import movie_service.domain.Movie;
import movie_service.domain.MovieRepository;

@EnableEurekaClient
@SpringBootApplication
public class MovieServiceApplication implements CommandLineRunner {

	@Autowired
	private MovieRepository repository;
	
    public static void main(String[] args) {
        SpringApplication.run(MovieServiceApplication.class, args);
    }

	@Override
	public void run(String... arg0) throws Exception {
		repository.deleteAll();
		repository.save(new Movie("1","Seven (Se7en) (1995)", "01-Jan-1995", "http://google.com"));
		repository.save(new Movie("2","Eight (Se7en) (1995)", "01-Jan-1995", "http://google.com"));
		repository.save(new Movie("3","Nine (Se7en) (1995)", "01-Jan-1995", "http://google.com"));
		repository.save(new Movie("4","Ten (Se7en) (1995)", "01-Jan-1995", "http://google.com"));
		repository.save(new Movie("5","Eleven (Se7en) (1995)", "01-Jan-1995", "http://google.com"));
		repository.save(new Movie("6","Twelve (Se7en) (1995)", "01-Jan-1995", "http://google.com"));
		repository.save(new Movie("7","Thirteen (Se7en) (1995)", "01-Jan-1995", "http://google.com"));
		repository.save(new Movie("8","Fourteen (Se7en) (1995)", "01-Jan-1995", "http://google.com"));
		repository.save(new Movie("9","Fifteen (Se7en) (1995)", "01-Jan-1995", "http://google.com"));
		repository.save(new Movie("10","Sixteen (Se7en) (1995)", "01-Jan-1995", "http://google.com"));
		
		
		System.out.println("findAll():");
		System.out.println("-------------------------------");
		for (Movie movie : repository.findAll()) {
			System.out.println(movie);
		}
		System.out.println("--------------------------------");
		System.out.println("findByName");
		System.out.println("-------------------------------");
		System.out.println(repository.findByName("Seven (Se7en) (1995)"));
		System.out.println("--------------------------------");
	}
}
