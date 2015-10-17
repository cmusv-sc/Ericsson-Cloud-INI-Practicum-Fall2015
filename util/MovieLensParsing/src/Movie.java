public class Movie {
	private final String id;
	private final String title;
	private final String releaseDate;
	private final String videoReleaseDate;
	private final String imdbUrl;
	private final String genres;

	public Movie(final String id, final String movieName, final String releaseDate, final String videoReleaseDate,
			final String imdbUrl, final String genres) {
		this.id = id;
		this.title = movieName;
		this.releaseDate = releaseDate;
		this.videoReleaseDate = videoReleaseDate;
		this.imdbUrl = imdbUrl;
		this.genres = genres;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getVideoReleaseDate() {
		return videoReleaseDate;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public String getImdbUrl() {
		return imdbUrl;
	}

	public String getGenres() {
		return genres;
	}

	@Override
	public String toString() {
		StringBuilder movieStringBuilder = new StringBuilder();
		movieStringBuilder.append("Id: " + id);
		movieStringBuilder.append("\nTitle: " + title);
		movieStringBuilder.append("\nReleaseDate: " + releaseDate);
		movieStringBuilder.append("\nVideoReleaseDate: " + videoReleaseDate);
		movieStringBuilder.append("\nImdbUrl: " + imdbUrl);
		movieStringBuilder.append("\nGenres: " + genres);
		return movieStringBuilder.toString();
	}
}
