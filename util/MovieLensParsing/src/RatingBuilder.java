public class RatingBuilder {
	private String userId;
	private String movieId;
	private String rating;
	private String timestamp;
	private boolean isUsed;

	public Rating build() throws Exception {
		if (isUsed)
			throw new Exception("This rating builder has already been used");

		isUsed = true;
		return new Rating(userId, movieId, rating, timestamp);
	}

	public RatingBuilder withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public RatingBuilder withMovieId(String movieId) {
		this.movieId = movieId;
		return this;
	}

	public RatingBuilder withRating(String rating) {
		this.rating = rating;
		return this;
	}

	public RatingBuilder withTimestamp(String timestamp) {
		this.timestamp = timestamp;
		return this;
	}
}
