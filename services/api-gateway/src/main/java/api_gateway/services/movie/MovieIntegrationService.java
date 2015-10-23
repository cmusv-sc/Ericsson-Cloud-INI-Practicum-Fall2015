package api_gateway.services.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import rx.Observable;
import api_gateway.services.movie.models.Movie;
import api_gateway.services.movie.models.MovieList;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.ObservableResult;


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
        stub.setDataReleased("01-Jan-2001");
        stub.setImdbURI("www.imdb.com");
        stub.setName("Default Movie");
        return stub;
    }

}
