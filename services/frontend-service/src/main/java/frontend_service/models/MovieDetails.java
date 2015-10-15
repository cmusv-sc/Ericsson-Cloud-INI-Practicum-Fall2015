package frontend_service.models;

import java.util.List;

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
