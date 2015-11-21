package edu.cmu.ini.ericsson.practicum.models.apiGatewayService;

import java.util.List;

public class MovieDetailsList {

	public List<MovieDetails> getMovieList() {
		return movieList;
	}

	public void setMovieList(List<MovieDetails> movieList) {
		this.movieList = movieList;
	}

	private List<MovieDetails> movieList;
	
	public MovieDetailsList() {
		
	}
	
	public MovieDetailsList(List<MovieDetails> movieList) {
		this.movieList = movieList;
	}
}
