package frontend_service.controller;

import java.util.ArrayList;
import java.util.List;

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
import edu.cmu.ini.ericsson.practicum.models.apiGatewayService.MovieDetailsList;


@Controller
public class FrontendController {

	@Autowired
	DiscoveryClient discoveryClient;

	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = "/movie/list/{n}", method=RequestMethod.GET)
	public String index(@PathVariable String n, Model model) {
		InstanceInfo gatewayInstance = discoveryClient.getNextServerFromEureka("API-GATEWAY", false);
		RestTemplate template = new RestTemplate();
		MovieDetailsList response = template.getForObject(gatewayInstance.getHomePageUrl()+"/movie/latest/"+ n, MovieDetailsList.class);	
        model.addAttribute("movies", response.getMovie());
        return "index";
	}
}
