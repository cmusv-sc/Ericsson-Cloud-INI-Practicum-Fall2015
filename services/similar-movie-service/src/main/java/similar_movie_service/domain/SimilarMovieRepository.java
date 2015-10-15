package similar_movie_service.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "similar-movie", path = "similar-movie")
public interface SimilarMovieRepository extends MongoRepository<SimilarMovie, String> {
	public SimilarMovie findById(String id);
	public SimilarMovie findByName(String name);
}
