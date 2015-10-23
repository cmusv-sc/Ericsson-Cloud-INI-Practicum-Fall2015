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
		repository.save(new Movie("1","Se7en", "01-Jan-1995", "http://google.com"));
		repository.save(new Movie("2","Gone Girl", "03-Oct-2014", "http://google.com"));
		repository.save(new Movie("3","Fight Club", "15-Oct-1999", "http://google.com"));
		repository.save(new Movie("4","Pulp Fiction", "14-Oct-1994", "http://google.com"));
		repository.save(new Movie("5","Django Unchained", "25-Dec-2012", "http://google.com"));
		repository.save(new Movie("6","Natural Born Killers", "26 Aug 1994", "http://google.com"));
		repository.save(new Movie("7","Sin City", "01 Apr 2005", "http://google.com"));
		repository.save(new Movie("8","Reservoir Dogs", "02 Sep 1992", "http://google.com"));
		repository.save(new Movie("9","The Matrix", "31 Mar 1999", "http://google.com"));
		repository.save(new Movie("10","The Martian", "02 Oct 2015", "http://google.com"));
		
		
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
