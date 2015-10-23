public class Rating {
	private final String userId;
	private final String movieId;
	private final String rating;
	private final String timestamp;

	public Rating(String userId, String movieId, String rating, String timestamp) {
		this.userId = userId;
		this.movieId = movieId;
		this.rating = rating;
		this.timestamp = timestamp;
	}

	public String getUserId() {
		return userId;
	}

	public String getMovieId() {
		return movieId;
	}

	public String getRating() {
		return rating;
	}

	public String getTimestamp() {
		return timestamp;
	}

	@Override
	public String toString() {
		StringBuilder ratingStringBuilder = new StringBuilder();
		ratingStringBuilder.append("UserId: " + userId);
		ratingStringBuilder.append("\nMovieId: " + movieId);
		ratingStringBuilder.append("\nRating: " + rating);
		ratingStringBuilder.append("\nTimestamp: " + timestamp);
		return ratingStringBuilder.toString();
	}
}
