package similar_movie_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import similar_movie_service.domain.SimilarMovie;
import similar_movie_service.domain.SimilarMovieRepository;

@EnableEurekaClient
@SpringBootApplication
public class SimilarMovieService implements CommandLineRunner {
	@Autowired
	private SimilarMovieRepository repository;
	
    public static void main(String[] args) {
        SpringApplication.run(SimilarMovieService.class, args);
    }

	public void run(String... arg0) throws Exception {
		repository.deleteAll();
		repository.save(new SimilarMovie("1","Seven (Se7en) (1995)", 
				"Fight Club", "House of Cards", "Gone Girl"));

		System.out.println("findAll():");
		System.out.println("-------------------------------");
		for (SimilarMovie movie : repository.findAll()) {
			System.out.println(movie);
		}
		System.out.println("--------------------------------");
		System.out.println("findById");
		System.out.println("-------------------------------");
		System.out.println(repository.findById("1"));
		System.out.println("--------------------------------");
	}
}
