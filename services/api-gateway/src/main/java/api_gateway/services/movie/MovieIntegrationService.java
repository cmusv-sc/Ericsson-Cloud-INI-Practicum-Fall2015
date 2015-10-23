package api_gateway.services.movie;

import java.util.ArrayList;
import java.util.List;

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

	@HystrixCommand(fallbackMethod = "stubMovieList")
    public Observable<MovieList> getMovieList(final String n) {
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
    
	private MovieList stubMovieList(final String mID) {
		List<Movie> list = new ArrayList<Movie>();

		for (int i = 1; i <= Integer.parseInt(mID); i++) {
			Movie stub = new Movie();
			stub.setDataReleased("01-Jan-2001");
			stub.setImdbURI("www.imdb.com");
			stub.setName("8IGHT");
			list.add(stub);
		}
		MovieList movieList = new MovieList();
		movieList.setMovie(list);
		return movieList;
	}

}
