package search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import search.domain.MovieSearch;
import search.domain.MovieSearchRepository;

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
		return movieSearchRepository.findByName(name);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/director/{director}")
	public MovieSearch getMovieByDirector(@PathVariable String director) {
		return movieSearchRepository.findByDirector(director);
	}
	
}
