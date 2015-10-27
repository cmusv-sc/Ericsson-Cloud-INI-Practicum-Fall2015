package image_service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.DiscoveryClient;

import edu.cmu.ini.ericsson.practicum.models.imageService.Image;

@RestController
public class ImageController {

	private static final String PATH = "/home/ubuntu/images";

	@Autowired
	DiscoveryClient discoveryClient;

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public Image getImage(@PathVariable String id) throws IOException {

		//Read the image from file
		File imageFile = new File(generateAbsolutePath(id));
		FileInputStream imageInFile = new FileInputStream(imageFile);
		byte imageData[] = new byte[(int) imageFile.length()];
		imageInFile.read(imageData);
		imageInFile.close();

		//Encode the image and send it back
		Image image = new Image();
		image.setImage(encodeImage(imageData));
		return image;
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
