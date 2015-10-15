package ratings_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ratings_service.domain.Rating;
import ratings_service.domain.RatingRepository;

@RestController
@RequestMapping("movie")
public class RatingController {

	@Autowired
	private RatingRepository repository;
	
	@RequestMapping(value = "{id}", method=RequestMethod.GET)
	public List<Rating> getRatingForMovie(@PathVariable String id){
		return repository.findByMovieId(id);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Rating postMovie(@RequestBody Rating rating){
		return repository.save(rating);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="{id}")
	public Rating updateRating(@PathVariable String id, @RequestBody Rating rating) {
	    Rating update = repository.findOne(id);
	    update.setMovieId(rating.getMovieId());
	    update.setRating(rating.getRating());
	    update.setUserId(rating.getUserId());
	    return repository.save(update);
	}	
	
	@RequestMapping(method=RequestMethod.DELETE, value="{id}")
	public void deleteRating(@PathVariable String id) {
		repository.delete(id);
	}

}
