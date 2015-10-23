package search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import search.domain.*;

@SpringBootApplication
@EnableEurekaClient
public class SearchServiceApplication implements CommandLineRunner{
	@Autowired
	private MovieSearchRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(SearchServiceApplication.class, args);
    }
    
    @Override
	public void run(String... arg0) throws Exception {
    	repository.save(new MovieSearch("1", "God Father", "1972", "Francis Ford Coppola", "Al Pacino"));
    }
}
