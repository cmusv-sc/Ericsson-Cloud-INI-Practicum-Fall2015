package ratings_service.domain;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.cmu.ini.ericsson.practicum.models.ratingsService.Rating;


public interface RatingRepository extends MongoRepository<Rating, String> {

    public List<Rating> findByMovieId(String movieId);
    public Rating findById(String id);
}