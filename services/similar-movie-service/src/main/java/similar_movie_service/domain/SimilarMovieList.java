package similar_movie_service.domain;

import java.util.List;

public class SimilarMovieList {

	private final List<SimilarMovie> similarMovieList;

	public SimilarMovieList(List<SimilarMovie> similarMovieList) {
		this.similarMovieList = similarMovieList;
	}

	public List<SimilarMovie> getSimilarMovieList() {
		return similarMovieList;
	}
}
