package api_gateway.models;

import java.util.List;

import api_gateway.services.images.models.Image;
import api_gateway.services.movie.models.Movie;
import api_gateway.services.ratings.models.Rating;
import api_gateway.services.similar_movie.models.SimilarMovie;

public class MovieDetails {
	private Image image;
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

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
