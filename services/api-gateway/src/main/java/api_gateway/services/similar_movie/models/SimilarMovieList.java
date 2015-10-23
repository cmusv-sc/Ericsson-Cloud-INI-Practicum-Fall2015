package api_gateway.services.similar_movie.models;

import java.util.List;

public class SimilarMovieList {

	private List<SimilarMovie> similarMovieList;

	public List<SimilarMovie> getSimilarMovieList() {
		return similarMovieList;
	}

	public void setSimilarMovieList(List<SimilarMovie> similarMovieList) {
		this.similarMovieList = similarMovieList;
	}

}