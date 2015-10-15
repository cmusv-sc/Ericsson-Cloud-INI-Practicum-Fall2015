package image_service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {

	private String image;

    public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
