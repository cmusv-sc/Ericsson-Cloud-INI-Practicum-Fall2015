package edu.cmu.ini.ericsson.practicum.models.movieService;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Movie {

	@Id
	private String id;
	private String omdbid;
	private String imdbid;
	private String title;
	private String year;
	private String runtime;
	private List<String> genre;
	private String director;
	private String writer;
	private List<String> cast;
	private String poster;
	private String plot;
	private String fullplot;
	private String language;
	private String country;
	private float imdbrating;

	public Movie(String id, String omdbid, String imdbid, String title, String year, String runtime, List<String> genre,
			String director, String writer, List<String> cast, String poster, String plot, String fullplot,
			String language, String country, float imdbrating) {
		super();
		this.id = id;
		this.omdbid = omdbid;
		this.imdbid = imdbid;
		this.title = title;
		this.year = year;
		this.runtime = runtime;
		this.genre = genre;
		this.director = director;
		this.writer = writer;
		this.cast = cast;
		this.poster = poster;
		this.plot = plot;
		this.fullplot = fullplot;
		this.language = language;
		this.country = country;
		this.imdbrating = imdbrating;
	}

	public Movie(Movie movie) {
		this.id = movie.getId();
		this.omdbid = movie.getOmdbid();
		this.imdbid = movie.getImdbid();
		this.title = movie.getTitle();
		this.year = movie.getYear();
		this.runtime = movie.getRuntime();
		this.genre = movie.getGenre();
		this.director = movie.getDirector();
		this.writer = movie.getWriter();
		this.cast = movie.getCast();
		this.poster = movie.getPoster();
		this.plot = movie.getPlot();
		this.fullplot = movie.getFullplot();
		this.language = movie.getLanguage();
		this.country = movie.getCountry();
		this.imdbrating = movie.getImdbrating();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOmdbid() {
		return omdbid;
	}

	public void setOmdbid(String omdbid) {
		this.omdbid = omdbid;
	}

	public String getImdbid() {
		return imdbid;
	}

	public void setImdbid(String imdbID) {
		this.imdbid = imdbID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public List<String> getGenre() {
		return genre;
	}

	public void setGenre(List<String> genre) {
		this.genre = genre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public List<String> getCast() {
		return cast;
	}

	public void setCast(List<String> cast) {
		this.cast = cast;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getFullplot() {
		return fullplot;
	}

	public void setFullplot(String fullPlot) {
		this.fullplot = fullPlot;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public float getImdbrating() {
		return imdbrating;
	}

	public void setImdbrating(float imdbrating) {
		this.imdbrating = imdbrating;
	}

	public Movie() {

	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", omdbid=" + omdbid + ", imdbid=" + imdbid + ", title=" + title + ", year=" + year
				+ ", runtime=" + runtime + ", genre=" + genre + ", director=" + director + ", writer=" + writer
				+ ", cast=" + cast + ", poster=" + poster + ", plot=" + plot + ", fullplot=" + fullplot + ", language="
				+ language + ", country=" + country + ", imdbrating=" + imdbrating + "]";
	}

}
