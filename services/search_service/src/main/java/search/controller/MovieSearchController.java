package search.controller;

import org.apache.lucene.search.suggest.Lookup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import search.api.MovieSuggestion;
import search.dao.Movie;
import search.domain.MovieSearch;
import search.domain.MovieSearchRepository;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/movies/search")
public class MovieSearchController {
	@Autowired
	private MovieSearchRepository movieSearchRepository;
	
	
	@RequestMapping(method=RequestMethod.GET, value="/id/{id}")
	public MovieSearch getMovieById(@PathVariable String id){
		return movieSearchRepository.findById(id);
	}


	@RequestMapping(method=RequestMethod.GET, value="/name/{name}")
	public MovieSearch getMovieByName(@PathVariable String name) {
		//return movieSearchRepository.findByName(name);
		return new MovieSearch("2", "haha", "hehe", "heihei", "hoho");
	}

	@RequestMapping(method=RequestMethod.GET, value = "/test/{haha}")
	public List<Movie> getMovies(@PathVariable String haha) {
		List<Movie> movies = new LinkedList<>();
		List<Lookup.LookupResult> results = MovieSuggestion.lookup(haha, "movie");
		for (Lookup.LookupResult result : results) {
			Movie movie = MovieSuggestion.getMovie(result);
			movies.add(movie);
		}
		return movies;
	}

	
	@RequestMapping(method=RequestMethod.GET, value="/director/{director}")
	public MovieSearch getMovieByDirector(@PathVariable String director) {
		return movieSearchRepository.findByDirector(director);
	}
	
}
