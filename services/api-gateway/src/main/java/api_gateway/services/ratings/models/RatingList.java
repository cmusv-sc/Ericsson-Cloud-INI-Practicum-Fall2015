package api_gateway.services.ratings.models;

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
