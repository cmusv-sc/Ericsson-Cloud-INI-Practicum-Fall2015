package api_gateway.services.ratings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import rx.Observable;
import api_gateway.services.ratings.models.Rating;
import api_gateway.services.ratings.models.RatingList;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.ObservableResult;

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

    @HystrixCommand(fallbackMethod = "stubRatingList")
    public Observable<RatingList> getRatingList(final String n) {
        return new ObservableResult<RatingList>() {
            @Override
            public RatingList invoke() {
                return restTemplate.getForObject("http://rating/movie/latest/{n}", RatingList.class, n);
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
    
	private RatingList stubRatingList(final String n) {
		List<List<Rating>> list = new ArrayList<List<Rating>>();
		for (int i = 1; i <= Integer.parseInt(n); i++) {
			Rating rating = new Rating();
			rating.setMovieId(String.valueOf(i));
			rating.setRating(3);
			rating.setUserId("1");
			list.add(Arrays.asList(rating));
		}
		
		RatingList ratingList = new RatingList();
		ratingList.setList(list);
		return ratingList;
	}
}
