package api_gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import rx.Observable;
import rx.Observer;
import api_gateway.service.user.UserIntegrationService;
import api_gateway.services.images.ImageIntegrationService;
import api_gateway.services.movie.MovieIntegrationService;
import api_gateway.services.ratings.RatingIntegrationService;
import api_gateway.services.similar_movie.SimilarMovieIntegrationService;
import edu.cmu.ini.ericsson.practicum.models.apiGatewayService.MovieDetails;
import edu.cmu.ini.ericsson.practicum.models.userService.User;

@RestController
@RequestMapping("/movie")
public class GatewayController {

	@Autowired
    MovieIntegrationService movieIntegrationService;

    @Autowired
    RatingIntegrationService ratingIntegrationService;

    @Autowired
    ImageIntegrationService imageIntegrationService;

    @Autowired
    SimilarMovieIntegrationService similarMovieIntegrationService;
    
    @Autowired
    UserIntegrationService userIntegrationService;

    @RequestMapping(value="{mID}", method=RequestMethod.GET)
    public DeferredResult<MovieDetails> getMovieDetails(@PathVariable String mID) {
        Observable<MovieDetails> details = Observable.zip(
			movieIntegrationService.getMovie(mID),
			ratingIntegrationService.ratingFor(mID),
			imageIntegrationService.imageFor(mID),
			similarMovieIntegrationService.getSimilarMovie(mID),
            (movie, ratings, image, similars) -> {
                MovieDetails movieDetails = new MovieDetails();
                movieDetails.setMovie(movie);
                movieDetails.setRatings(ratings);
                movieDetails.setSimilars(similars);
                movieDetails.setImage(image);
                return movieDetails;
            }
        );
        return toDeferredResult(details);
    }

    public DeferredResult<MovieDetails> toDeferredResult(Observable<MovieDetails> details) {
        DeferredResult<MovieDetails> result = new DeferredResult<>();
        details.subscribe(new Observer<MovieDetails>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onNext(MovieDetails movieDetails) {
                result.setResult(movieDetails);
            }
        });
        return result;
    }
    
    @RequestMapping(value="/user/{mID}", method=RequestMethod.GET)
    public DeferredResult<User> getUser(@PathVariable String mID) {
        Observable<User> user = userIntegrationService.getUser(mID);
        return toDeferredResultUser(user);
    }

    public DeferredResult<User> toDeferredResultUser(Observable<User> user) {
        DeferredResult<User> result = new DeferredResult<>();
        user.subscribe(new Observer<User>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onNext(User user) {
                result.setResult(user);
            }
        });
        return result;
    }
}
