package movie_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.cmu.ini.ericsson.practicum.models.movieService.Movie;
import movie_service.domain.MovieRepository;

@RestController
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	private MovieRepository repository;

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public Movie getMovie(@PathVariable String id) {
		return repository.findById(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Movie postMovie(@RequestBody Movie movie) {
		return repository.save(movie);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public Movie updateMovie(@PathVariable String id, @RequestBody Movie movie) {
		Movie update = repository.findOne(id);
		update.setImdbUrl(movie.getImdbUrl());
		update.setReleaseDate(movie.getReleaseDate());
		update.setName(movie.getName());
		update.setGenres(movie.getGenres());
		return repository.save(update);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public void deleteMovie(@PathVariable String id) {
		repository.delete(id);
	}
}
