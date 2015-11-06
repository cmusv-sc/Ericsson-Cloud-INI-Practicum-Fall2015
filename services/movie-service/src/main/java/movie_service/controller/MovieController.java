package movie_service.controller;

import java.util.ArrayList;
import java.util.List;

import movie_service.domain.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.cmu.ini.ericsson.practicum.models.movieService.Movie;
import edu.cmu.ini.ericsson.practicum.models.movieService.MovieList;

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
	public MovieList getAllMovie(@PathVariable String n) {
		List<Movie> movieList = new ArrayList<Movie>();
		// TODO: Change logic here
		for (int i = 1; i <= Integer.parseInt(n); i++) {
			movieList.add(repository.findById(String.valueOf(i)));
		}
		
		MovieList movieListObject = new MovieList();
		movieListObject.setMovie(movieList);
		return movieListObject;
	}

	@RequestMapping(method = RequestMethod.POST)
	public Movie postMovie(@RequestBody Movie movie) {
		return repository.save(movie);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public Movie updateMovie(@PathVariable String id, @RequestBody Movie movie) {
		Movie update = repository.findOne(id);
		update.setCast(movie.getCast());
		update.setCountry(movie.getCountry());
		update.setDirector(movie.getDirector());
		update.setFullplot(movie.getFullplot());
		update.setGenre(movie.getGenre());
		update.setImdbid(movie.getImdbid());
		update.setLanguage(movie.getLanguage());
		update.setPlot(movie.getPlot());
		update.setPoster(movie.getPoster());
		update.setRuntime(movie.getRuntime());
		update.setTitle(movie.getTitle());
		update.setWriter(movie.getWriter());
		update.setYear(movie.getYear());
		return repository.save(update);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public void deleteMovie(@PathVariable String id) {
		repository.delete(id);
	}
}
