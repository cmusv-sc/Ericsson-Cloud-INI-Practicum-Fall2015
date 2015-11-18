package edu.cmu.ini.ericsson.practicum.models.apiGatewayService;

import java.util.List;

import edu.cmu.ini.ericsson.practicum.models.movieService.Movie;
import edu.cmu.ini.ericsson.practicum.models.ratingsService.Rating;
import edu.cmu.ini.ericsson.practicum.models.similarMovieService.SimilarMovie;


public class MovieDetails {
	private Movie movie;
    private List<Rating> ratings;
    private SimilarMovie similars;

	public SimilarMovie getSimilars() {
		return similars;
	}

	public void setSimilars(SimilarMovie similars) {
		this.similars = similars;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
}
