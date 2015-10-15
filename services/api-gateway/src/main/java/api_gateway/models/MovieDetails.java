package api_gateway.models;

import java.util.List;

import api_gateway.services.movie.models.Movie;
import api_gateway.services.ratings.models.Rating;

public class MovieDetails {
    private Movie movie;
    private List<Rating> ratings;
    
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
