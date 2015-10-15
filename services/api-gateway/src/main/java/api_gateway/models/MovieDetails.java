package api_gateway.models;

import java.util.List;

import api_gateway.services.images.models.Image;
import api_gateway.services.movie.models.Movie;
import api_gateway.services.ratings.models.Rating;

public class MovieDetails {
	private Movie movie;
	private List<Rating> ratings;
	private Image image;

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

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
