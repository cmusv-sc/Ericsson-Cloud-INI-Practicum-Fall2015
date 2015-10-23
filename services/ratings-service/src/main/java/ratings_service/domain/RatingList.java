package ratings_service.domain;

import java.util.List;

public class RatingList {

	private final List<Rating> ratingList;

	public RatingList(List<Rating> ratingList) {
		this.ratingList = ratingList;
	}

	public List<Rating> getRatingList() {
		return ratingList;
	}
}
