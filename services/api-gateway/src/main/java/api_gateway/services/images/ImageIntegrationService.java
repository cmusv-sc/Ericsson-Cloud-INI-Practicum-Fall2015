package api_gateway.services.images;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import rx.Observable;
import api_gateway.services.images.models.Image;
import api_gateway.services.images.models.ImageList;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.ObservableResult;

@Service
public class ImageIntegrationService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "stubImage")
    public Observable<Image> imageFor(String mID) {
        return new ObservableResult<Image>() {
            @Override
            public Image invoke() {
                ParameterizedTypeReference<Image> responseType = new ParameterizedTypeReference<Image>() {};
                return restTemplate.exchange("http://images/image/get/{mID}", HttpMethod.GET, null, responseType, mID).getBody();
            }
        };
    }

    @HystrixCommand(fallbackMethod = "stubImageList")
    public Observable<ImageList> getImageList(final String n) {
        return new ObservableResult<ImageList>() {
            @Override
            public ImageList invoke() {
                return restTemplate.getForObject("http://images/image/latest/{n}", ImageList.class, n);
            }
        };
    }

	private Image stubImage(String mID) {
		Image image = new Image();
		image.setImage("https://scontent-sjc2-1.xx.fbcdn.net/hphotos-xfp1/v/t1.0-9/1780914_683427131804051_7408191191730620890_n.jpg?oh=8a1c0a7b24392fe9bf03be7483985b72&oe=56C52E28");
		return image;
	}
    
	private ImageList stubImageList(String mID) {
		List<Image> list = new ArrayList<Image>();
		for (int i = 1; i <= Integer.parseInt(mID); i++) {
			Image image = new Image();
			image.setImage("https://scontent-sjc2-1.xx.fbcdn.net/hphotos-xfp1/v/t1.0-9/1780914_683427131804051_7408191191730620890_n.jpg?oh=8a1c0a7b24392fe9bf03be7483985b72&oe=56C52E28");
			list.add(image);
		}
		
		ImageList imageList = new ImageList();
		imageList.setList(list);
		return imageList;
	}
}
