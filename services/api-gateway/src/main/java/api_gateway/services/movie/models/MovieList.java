package api_gateway.services.movie.models;

import java.util.List;

public class MovieList {

	private final List<Movie> movieList;

	public MovieList(List<Movie> movieList) {
		this.movieList = movieList;
	}

	public List<Movie> getMovieList() {
		return movieList;
	}
}
