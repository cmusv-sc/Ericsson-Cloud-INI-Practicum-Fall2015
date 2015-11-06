package frontend_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;

import edu.cmu.ini.ericsson.practicum.models.apiGatewayService.MovieDetails;
import edu.cmu.ini.ericsson.practicum.models.userService.User;

@Controller
public class FrontendController {
	
	@Autowired
	DiscoveryClient discoveryClient;

	@RequestMapping(value = "/movie/{id}", method=RequestMethod.GET)
	public String index(@PathVariable String id, 
			@RequestParam("uid") String userId,
			@RequestParam("pwd") String pwd,
			Model model) {
		InstanceInfo gatewayInstance = discoveryClient.getNextServerFromEureka("API-GATEWAY", false);
		RestTemplate template = new RestTemplate();
		User user = template.getForObject(gatewayInstance.getHomePageUrl()+"/movie/user/"+userId, User.class);
		System.out.println(user);
		if (user.getPassword().equals(pwd)) {
			MovieDetails response = template.getForObject(gatewayInstance.getHomePageUrl()+"/movie/"+id, MovieDetails.class);		
	        model.addAttribute("response", response);
	        model.addAttribute("user", user);
	        return "index";
		} else {
			return "login";
		}
	}
	
	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}
}
