package movie_service.controller;

import java.util.ArrayList;
import java.util.List;

import movie_service.domain.Movie;
import movie_service.domain.MovieList;
import movie_service.domain.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	private MovieRepository repository;

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public Movie getMovie(@PathVariable String id) {
		return repository.findById(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/latest/{n}")
	public MovieList getAllMovie(@PathVariable Integer n) {
		List<Movie> movieList = new ArrayList<Movie>();
		// TODO: Change logic here
		for (int i = 1; i <= n; i++) {
			movieList.add(repository.findById(String.valueOf(i)));
		}
		return new MovieList(movieList);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Movie postMovie(@RequestBody Movie movie) {
		return repository.save(movie);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public Movie updateMovie(@PathVariable String id, @RequestBody Movie movie) {
		Movie update = repository.findOne(id);
		update.setImdbURI(movie.getImdbURI());
		update.setName(movie.getName());
		update.setDataReleased(movie.getDataReleased());
		return repository.save(update);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public void deleteMovie(@PathVariable String id) {
		repository.delete(id);
	}
}
