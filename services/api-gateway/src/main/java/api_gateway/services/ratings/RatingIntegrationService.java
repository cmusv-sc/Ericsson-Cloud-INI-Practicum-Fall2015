package api_gateway.services.ratings;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.ObservableResult;
import org.springframework.http.HttpMethod;

import api_gateway.services.ratings.models.Rating;
import rx.Observable;

@Service
public class RatingIntegrationService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "stubRating")
    public Observable<List<Rating>> ratingFor(String mID) {
        return new ObservableResult<List<Rating>>() {
            @Override
            public List<Rating> invoke() {
                ParameterizedTypeReference<List<Rating>> responseType = new ParameterizedTypeReference<List<Rating>>() {};
                return restTemplate.exchange("http://rating/movie/{mID}", HttpMethod.GET, null, responseType, mID).getBody();
            }
        };
    }

    private List<Rating> stubRating(String mID) {
        Rating rating = new Rating();
        rating.setMovieId(mID);
        rating.setRating(3);
        rating.setUserId("1");
        return Arrays.asList(rating);
    }
}
