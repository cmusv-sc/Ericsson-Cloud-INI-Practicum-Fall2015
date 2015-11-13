package user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import edu.cmu.ini.ericsson.practicum.models.userService.User;
import user_service.domain.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository repository;
	
	@RequestMapping(method=RequestMethod.GET, value="{id}")
	public User getMovie(@PathVariable String id){
		return repository.findById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public User postMovie(@RequestBody User user){
		return repository.save(user);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="{id}")
	public User updateMovie(@PathVariable String id, @RequestBody User user) {
	    User update = repository.findOne(id);	    
	    update.setName(user.getName());
	    return repository.save(update);
	}	
	
	@RequestMapping(method=RequestMethod.DELETE, value="{id}")
	public void deleteMovie(@PathVariable String id) {
		repository.delete(id);
	}
}
