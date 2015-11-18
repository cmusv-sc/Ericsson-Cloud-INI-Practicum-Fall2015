package similar_movie_service.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.cmu.ini.ericsson.practicum.models.movieService.Movie;
import edu.cmu.ini.ericsson.practicum.models.movieService.MovieList;
import edu.cmu.ini.ericsson.practicum.models.similarMovieService.SimilarMovie;
import edu.cmu.ini.ericsson.practicum.models.similarMovieService.SimilarMovieList;
import similar_movie_service.domain.SimilarMovieRepository;

@RestController
@RequestMapping("/similar-movie")
public class SimilarMovieController {

	@Autowired
	private SimilarMovieRepository repository;

	@RequestMapping(method=RequestMethod.GET, value="{id}")
	public SimilarMovie getMovie(@PathVariable String id){
		return repository.findById(id);
	}

	@RequestMapping(method=RequestMethod.POST)
	public SimilarMovie postMovie(@RequestBody SimilarMovie movie){
		return repository.save(movie);
	}

	@RequestMapping(method=RequestMethod.PUT, value="{id}")
	public SimilarMovie updateMovie(@PathVariable String id, @RequestBody SimilarMovie movie) {
		SimilarMovie update = repository.findOne(id);
	    update.setName(movie.getName());
	    update.setFirstChoice(movie.getFirstChoice());
	    update.setSecondChoice(movie.getSecondChoice());
	    update.setThirdChoice(movie.getThirdChoice());
	    return repository.save(update);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/latest/{n}")
	public SimilarMovieList getAllMovie(@PathVariable Integer n) {
		List<SimilarMovie> movieList = new ArrayList<SimilarMovie>();
		// TODO: Change logic here
		for (int i = 1; i <= n; i++) {
			movieList.add(repository.findById(String.valueOf(i)));
		}
		return new SimilarMovieList(movieList);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="{id}")
	public void deleteMovie(@PathVariable String id) {
		repository.delete(id);
	}

}
