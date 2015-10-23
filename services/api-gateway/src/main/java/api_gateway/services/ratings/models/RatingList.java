package api_gateway.services.ratings.models;

import java.util.List;

public class RatingList {

	private List<List<Rating>> list;

	public List<List<Rating>> getList() {
		return list;
	}

	public void setList(List<List<Rating>> list) {
		this.list = list;
	}
}