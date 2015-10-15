package movie_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import movie_service.domain.Movie;
import movie_service.domain.MovieRepository;

@RestController
@RequestMapping("/movie")
public class MovieController {
	
	@Autowired
	private MovieRepository repository;
	
	@RequestMapping(method=RequestMethod.GET, value="{id}")
	public Movie getMovie(@PathVariable String id){
		return repository.findById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Movie postMovie(@RequestBody Movie movie){
		return repository.save(movie);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="{id}")
	public Movie updateMovie(@PathVariable String id, @RequestBody Movie movie) {
	    Movie update = repository.findOne(id);
	    update.setImdbURI(movie.getImdbURI());
	    update.setName(movie.getName());
	    update.setDataReleased(movie.getDataReleased());
	    return repository.save(update);
	}	
	
	@RequestMapping(method=RequestMethod.DELETE, value="{id}")
	public void deleteMovie(@PathVariable String id) {
		repository.delete(id);
	}
}
