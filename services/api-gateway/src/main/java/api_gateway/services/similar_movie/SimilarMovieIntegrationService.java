package api_gateway.services.similar_movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.ObservableResult;

import api_gateway.services.similar_movie.models.SimilarMovie;
import rx.Observable;

@Service
public class SimilarMovieIntegrationService {
	@Autowired
    RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "stubSimilarMovie")
    public Observable<SimilarMovie> getSimilarMovie(final String mID) {
        return new ObservableResult<SimilarMovie>() {
            @Override
            public SimilarMovie invoke() {
                return restTemplate.getForObject("http://similar-movies//similar-movie/{mID}", SimilarMovie.class, mID);
            }
        };
    }

	private SimilarMovie stubSimilarMovie(final String mID) {
		SimilarMovie stub = new SimilarMovie();
		stub.setId(mID);
        stub.setName("fallback movie");
        stub.setFirstChoice("first choice");
        stub.setSecondChoice("second choice");
        stub.setThirdChoice("third choice");
        return stub;
    }
}
