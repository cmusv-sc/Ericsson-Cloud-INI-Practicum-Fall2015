package edu.cmu.ini.ericsson.practicum.models.similarMovieService;
import java.util.List;

public class SimilarMovieList {

	private List<SimilarMovie> similarMovieList;
	
	public SimilarMovieList() {
		
	}
	
	public SimilarMovieList(List<SimilarMovie> similarMovieList) {
		this.similarMovieList = similarMovieList;
	}
	
	public List<SimilarMovie> getSimilarMovieList() {
		return similarMovieList;
	}

	public void setSimilarMovieList(List<SimilarMovie> similarMovieList) {
		this.similarMovieList = similarMovieList;
	}

}
