package api_gateway.services.movie.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {
	
	private String name;
    private String dataReleased;
    private String imdbURI;

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