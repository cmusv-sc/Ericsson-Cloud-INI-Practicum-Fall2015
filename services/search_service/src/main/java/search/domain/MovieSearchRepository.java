package search.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "moviesearch", path = "moviesearch")
public interface MovieSearchRepository extends MongoRepository<MovieSearch, String>{
	public MovieSearch findByName(String name);
    public MovieSearch findById(String id);
    public MovieSearch findByDirector(String director);
}
