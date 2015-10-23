package api_gateway.services.images.models;

import java.util.List;

public class ImageList {

	private final List<Image> imageList;

	public ImageList(List<Image> imageList) {
		this.imageList = imageList;
	}

	public List<Image> getImageList() {
		return imageList;
	}
}
