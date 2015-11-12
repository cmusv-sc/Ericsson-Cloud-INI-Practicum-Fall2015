package api_gateway.services.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.ObservableResult;

import edu.cmu.ini.ericsson.practicum.models.movieService.Movie;
import rx.Observable;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class MovieIntegrationService {
	private static final Logger LOG = LoggerFactory.getLogger(MovieIntegrationService.class);

	@Autowired
    RestTemplate restTemplate;

	@Autowired
	LoadBalancerClient loadBalancer;

	@HystrixCommand(fallbackMethod = "stubMovie")
    public Observable<Movie> getMovie(final String mID) {
        return new ObservableResult<Movie>() {
            @Override
            public Movie invoke() {
				Movie movie = null;
				ServiceInstance instance = loadBalancer.choose("movies");
				URI uri = instance.getUri();
				LOG.info("Resolved serviceId 'movies' to URL '{}'.", uri);
				String url = uri.toString() + "/movie/" + mID;
				LOG.info("url is " + url);
				try {
					RestTemplate r = new RestTemplate();
					movie = r.getForObject(url, Movie.class);
				} catch (Exception e) {
					LOG.info(e.toString());
					LOG.info("load balancer not working!");
					movie = restTemplate.getForObject("http://movies/movie/{mID}", Movie.class, mID);
				}
				return movie;
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
