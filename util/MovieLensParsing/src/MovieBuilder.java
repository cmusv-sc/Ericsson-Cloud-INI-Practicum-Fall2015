public class MovieBuilder {
	private String id;
	private String title;
	private String releaseDate;
	private String videoReleaseDate;
	private String imdbUrl;
	private String genreString;
	private boolean isUsed;
	
	public Movie build() throws Exception {
		if(isUsed) 
			throw new Exception("Attempting to use an already-used builder");
		
		isUsed = true;
		return new Movie(id, title, releaseDate, videoReleaseDate, imdbUrl, genreString);
	}

	public MovieBuilder withId(final String id) {
		this.id = id;
		return this;
	}

	public MovieBuilder withTitle(final String title) {
		this.title = title;
		return this;
	}

	public MovieBuilder withReleaseDate(final String releaseDate) {
		this.releaseDate = releaseDate;
		return this;
	}

	public MovieBuilder withVideoReleaseDate(final String videoReleaseDate) {
		this.videoReleaseDate = videoReleaseDate;
		return this;
	}

	public MovieBuilder withImdbUrl(final String imdbUrl) {
		this.imdbUrl = imdbUrl;
		return this;
	}

	public MovieBuilder withGenreString(final String genreString) {
		this.genreString = genreString;
		return this;
	}
}
