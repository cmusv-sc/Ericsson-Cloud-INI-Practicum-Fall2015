package image_service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.DiscoveryClient;

import edu.cmu.ini.ericsson.practicum.models.imageService.Image;
import edu.cmu.ini.ericsson.practicum.models.imageService.ImageList;

@RestController
@RequestMapping("/image")
public class ImageController {

	private static final String PATH = "/home/ubuntu/images";
	private static final HashMap<String, String> imageLinks = new HashMap<String, String>();
	private static final String DEFAULT_IMAGE_LINK = "https://media.licdn.com/mpr/mpr/shrinknp_400_400/AAEAAQAAAAAAAAJ9AAAAJDlkNWY3NWUyLWIwYzMtNDE5Yi1iMjU5LWUzNmY4ODM4MzU0MA.jpg";
	
	static {
		imageLinks.put("1", "http://ia.media-imdb.com/images/M/MV5BMTQwNTU3MTE4NF5BMl5BanBnXkFtZTcwOTgxNDM2Mg@@._V1_SX300.jpg");
		imageLinks.put("2", "http://ia.media-imdb.com/images/M/MV5BMTk0MDQ3MzAzOV5BMl5BanBnXkFtZTgwNzU1NzE3MjE@._V1_SX300.jpg");
		imageLinks.put("3", "http://ia.media-imdb.com/images/M/MV5BMjIwNTYzMzE1M15BMl5BanBnXkFtZTcwOTE5Mzg3OA@@._V1_SX300.jpg");
		imageLinks.put("4", "http://ia.media-imdb.com/images/M/MV5BMTkxMTA5OTAzMl5BMl5BanBnXkFtZTgwNjA5MDc3NjE@._V1_SX300.jpg");
		imageLinks.put("5", "http://ia.media-imdb.com/images/M/MV5BMjIyNTQ5NjQ1OV5BMl5BanBnXkFtZTcwODg1MDU4OA@@._V1_SX300.jpg");
		imageLinks.put("6", "http://ia.media-imdb.com/images/M/MV5BMTI2NTU2Nzc0MV5BMl5BanBnXkFtZTcwMzY1OTM2MQ@@._V1_SX300.jpg");
		imageLinks.put("7", "http://ia.media-imdb.com/images/M/MV5BMTI2NjUyMDUyMV5BMl5BanBnXkFtZTcwMzU0OTkyMQ@@._V1_SX300.jpg");
		imageLinks.put("8", "http://ia.media-imdb.com/images/M/MV5BMTQxMTAwMDQ3Nl5BMl5BanBnXkFtZTcwODMwNTgzMQ@@._V1_SX300.jpg");
		imageLinks.put("9", "http://ia.media-imdb.com/images/M/MV5BMTkxNDYxOTA4M15BMl5BanBnXkFtZTgwNTk0NzQxMTE@._V1_SX300.jpg");
		imageLinks.put("10", "http://ia.media-imdb.com/images/M/MV5BMTc2MTQ3MDA1Nl5BMl5BanBnXkFtZTgwODA3OTI4NjE@._V1_SX300.jpg");
	}
	
	@RequestMapping(value = "/get/{id}/{trace_uuid}", method = RequestMethod.GET)
	public Image getImage(@PathVariable String id, @PathVariable String trace_uuid) throws IOException {
		String imageLink = imageLinks.get(id);
		Utils.trace_log("image_service/image/get/"+id, "image_service", "null", trace_uuid, ImageController.class);
		if(imageLink == null)
			imageLink = DEFAULT_IMAGE_LINK;
		return new Image(imageLink);
	}

	@RequestMapping(value = "/latest/{n}", method = RequestMethod.GET)
	public ImageList getImageList(@PathVariable String n) throws IOException {
		List<Image> imageList = new ArrayList<Image>();
		for(int i = 1; i <= Integer.parseInt(n); i++) {
			String imageLink = imageLinks.get(String.valueOf(i));
			if(imageLink == null)
				imageLink = DEFAULT_IMAGE_LINK;
			imageList.add(new Image(imageLink));
		}
		ImageList list = new ImageList();
		list.setList(imageList);
		return list;
	}

	
	@RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
	public void addImage(@PathVariable String id,
			@RequestBody String incomingImage) throws IOException {
		try {
			// Converting a Base64 String into Image byte array
			byte[] imageByteArray = decodeImage(incomingImage);

			// Write a image byte array into file
			FileOutputStream imageOutFile = new FileOutputStream(
					generateAbsolutePath(id));
			imageOutFile.write(imageByteArray);
			imageOutFile.close();
		} catch (FileNotFoundException e) {
			System.err.println("Image not found" + e);
		} catch (IOException ioe) {
			System.err.println("Exception while reading the Image " + ioe);
		}
	}

	/**
	 * Encodes the byte array into base64 string
	 *
	 * @param imageByteArray
	 *            - byte array
	 * @return String a {@link java.lang.String}
	 */
	private String encodeImage(byte[] imageByteArray) {
		return Base64.getEncoder().encodeToString(imageByteArray);
	}

	/**
	 * Decodes the base64 string into byte array
	 *
	 * @param imageDataString
	 *            - a {@link java.lang.String}
	 * @return byte array
	 */
	private byte[] decodeImage(String imageDataString) {
		return Base64.getDecoder().decode(imageDataString);
	}

	/**
	 * Generates the path of the image file from image ID
	 *
	 * @param imageID
	 *
	 * @return path - a {@link java.lang.String}
	 */
	private String generateAbsolutePath(String imageId) {
		return new String(PATH + imageId + ".jpg");
	}
}
