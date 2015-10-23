package ratings_service.domain;

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
