package edu.cmu.ini.ericsson.practicum.models.apiGatewayService;

import java.util.List;

public class MovieDetailsList {

	private List<MovieDetails> movie;
	
	public MovieDetailsList(List<MovieDetails> movie) {
		this.movie = movie;
	}
	
	public List<MovieDetails> getMovie() {
		return movie;
	}

	public void setMovie(List<MovieDetails> movie) {
		this.movie = movie;
	}
}
