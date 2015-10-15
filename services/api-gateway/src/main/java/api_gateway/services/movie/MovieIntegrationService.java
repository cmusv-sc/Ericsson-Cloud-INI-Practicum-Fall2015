package api_gateway.services.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.ObservableResult;

import api_gateway.services.movie.models.Movie;
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

    private Movie stubMovie(final String mID) {
        Movie stub = new Movie();
        stub.setDataReleased("01-Jan-2001");
        stub.setImdbURI("www.imdb.com");
        stub.setName("Default Movie");
        return stub;
    }

}
