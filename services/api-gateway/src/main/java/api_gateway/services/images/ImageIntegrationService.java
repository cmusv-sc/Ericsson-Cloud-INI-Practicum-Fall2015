package api_gateway.services.images;

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
                return restTemplate.exchange("http://images/get/{mID}", HttpMethod.GET, null, responseType, mID).getBody();
            }
        };
    }

    @HystrixCommand(fallbackMethod = "stubImage")
    public Observable<ImageList> getImageList(final Integer n) {
        return new ObservableResult<ImageList>() {
            @Override
            public ImageList invoke() {
                return restTemplate.getForObject("http://images/image/latest/{n}", ImageList.class, n);
            }
        };
    }
    private Image stubImage(String mID) {
       Image image = new Image();
        image.setImage("iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg==");
        return image;
    }
}
