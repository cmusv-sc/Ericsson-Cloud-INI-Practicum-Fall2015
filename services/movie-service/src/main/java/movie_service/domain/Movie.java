package movie_service.domain;

import org.springframework.data.annotation.Id;

public class Movie {

	@Id
	private String id;
	private String name;
	private String videoReleaseDate;
	private String releaseDate;
	private String imdbUrl;
	private String genres;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVideoReleaseDate() {
		return videoReleaseDate;
	}

	public void setVideoReleaseDate(String videoReleaseDate) {
		this.videoReleaseDate = videoReleaseDate;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getImdbUrl() {
		return imdbUrl;
	}

	public void setImdbUrl(String imdbUrl) {
		this.imdbUrl = imdbUrl;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", videoReleaseDate=" + videoReleaseDate + ", releaseDate="
				+ releaseDate + ", imdbUrl=" + imdbUrl + ", genres=" + genres + "]";
	}

	public Movie() {

	}

	public Movie(String id, String name, String videoReleaseDate, String releaseDate, String imdbUrl, String genres) {
		super();
		this.id = id;
		this.name = name;
		this.videoReleaseDate = videoReleaseDate;
		this.releaseDate = releaseDate;
		this.imdbUrl = imdbUrl;
		this.genres = genres;
	}

}
