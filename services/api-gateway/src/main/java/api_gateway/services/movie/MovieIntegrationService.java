package api_gateway.services.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import rx.Observable;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.ObservableResult;

import edu.cmu.ini.ericsson.practicum.models.movieService.Movie;
import rx.Observable;

@Service
public class MovieIntegrationService {
	
	@Autowired
    RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "stubMovie")
    public Observable<Movie> getMovie(final String mID) {
        return new ObservableResult<Movie>() {
            @Override
            public Movie invoke() {
                return restTemplate.getForObject("http://movies/movie/{mID}", Movie.class, mID);
            }
        };
    }

	@HystrixCommand(fallbackMethod = "stubMovie")
    public Observable<MovieList> getMovieList(final Integer n) {
        return new ObservableResult<MovieList>() {
            @Override
            public MovieList invoke() {
                return restTemplate.getForObject("http://movies/movie/latest/{n}", MovieList.class, n);
            }
        };
    }
	
    private Movie stubMovie(final String mID) {
        Movie stub = new Movie();
		stub.setCast(null);
		stub.setCountry("");
		stub.setDirector("");
		stub.setFullplot("");
		stub.setGenre(null);
		stub.setImdbid("");
		stub.setLanguage("");
		stub.setPlot("");
		stub.setPoster(null);
		stub.setRuntime("");
		stub.setTitle("Default Movie");
		stub.setWriter("");
		stub.setYear("");
        return stub;
    }

}
