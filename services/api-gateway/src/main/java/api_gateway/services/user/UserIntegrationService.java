package api_gateway.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.ObservableResult;

import edu.cmu.ini.ericsson.practicum.models.userService.User;
import rx.Observable;

@Service
public class UserIntegrationService {
	@Autowired
    RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "stubUser")
    public Observable<User> getUser(final String mID) {
        return new ObservableResult<User>() {
            @Override
            public User invoke() {
                return restTemplate.getForObject("http://user-service//user/{mID}", User.class, mID);
            }
        };
    }

	private User stubUser(final String mID) {
		return new User("1", "John", "M", "Doctor", "15213", "6", "1qaz2wsx");
    }
}
