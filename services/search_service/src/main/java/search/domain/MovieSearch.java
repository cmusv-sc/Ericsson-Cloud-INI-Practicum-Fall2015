package search.domain;

import org.springframework.data.annotation.Id;

public class MovieSearch {
	@Id
	private String id;
	private String name;
    private String year;
    private String director;
    private String lead;
	public MovieSearch(String id, String name, String year, String director, String lead) {
		super();
		this.id = id;
		this.name = name;
		this.year = year;
		this.director = director;
		this.lead = lead;
	}
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
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	@Override
	public String toString() {
		return "MovieSearch [id=" + id + ", name=" + name + ", year=" + year + ", director=" + director
				+ ", lead_actor=" + lead + "]";
	}
}
