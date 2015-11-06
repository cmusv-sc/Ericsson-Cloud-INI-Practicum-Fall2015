package edu.cmu.ini.ericsson.practicum.models.movieService;

import java.util.List;

public class MovieList {

	private List<Movie> movie;
	
	public MovieList() {
		
	}
	
	public MovieList(List<Movie> movie) {
		this.movie = movie;
	}

	public List<Movie> getMovie() {
		return movie;
	}

	public void setMovie(List<Movie> movie) {
		this.movie = movie;
	}
}
