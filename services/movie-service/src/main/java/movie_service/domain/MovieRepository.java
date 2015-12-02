package movie_service.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import edu.cmu.ini.ericsson.practicum.models.movieService.Movie;

@RepositoryRestResource(collectionResourceRel = "movie", path = "movie")
public interface MovieRepository extends MongoRepository<Movie, String> {
	public Movie findByTitle(@Param("title") String title);
	public Movie findByOmdbid(@Param("omdbid") String omdbid);
}
