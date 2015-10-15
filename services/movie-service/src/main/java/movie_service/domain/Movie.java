package movie_service.domain;

import org.springframework.data.annotation.Id;


public class Movie {

	@Id
	private String id;
	private String name;
    private String dataReleased;
    private String imdbURI;

    public Movie() {}

    public Movie(String id, String name, String dateReleased, String imdbURI) {
    	this.id = id;
        this.name = name;
        this.dataReleased = dateReleased;
        this.imdbURI = imdbURI;
    }

    @Override
    public String toString() {
        return String.format("%s -- %s", this.id, this.name);
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

	public String getDataReleased() {
		return dataReleased;
	}

	public void setDataReleased(String dataReleased) {
		this.dataReleased = dataReleased;
	}

	public String getImdbURI() {
		return imdbURI;
	}

	public void setImdbURI(String imdbURI) {
		this.imdbURI = imdbURI;
	}
	
}
