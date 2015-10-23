package frontend_service.models;

import java.util.List;

public class MovieDetailsList {

	private final List<MovieDetails> movieList;

	public MovieDetailsList(List<MovieDetails> movieList) {
		this.movieList = movieList;
	}

	public List<MovieDetails> getMovieList() {
		return movieList;
	}
}