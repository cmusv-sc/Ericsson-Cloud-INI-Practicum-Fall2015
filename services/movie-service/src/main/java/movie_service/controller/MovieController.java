package movie_service.controller;

import java.util.ArrayList;
import java.util.List;

import movie_service.Utils;
import movie_service.domain.MovieRepository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import edu.cmu.ini.ericsson.practicum.models.imageService.Image;
import edu.cmu.ini.ericsson.practicum.models.imageService.ImageList;
import edu.cmu.ini.ericsson.practicum.models.movieService.Movie;
import edu.cmu.ini.ericsson.practicum.models.movieService.MovieList;

@RestController
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	private MovieRepository repository;
	
	@Autowired
    RestTemplate restTemplate;
	
	private static final Logger logger = Logger.getLogger(MovieController.class);

	@RequestMapping(method = RequestMethod.GET, value = "{omdbid}/{trace_uuid}")
	public Movie getMovie(@PathVariable String omdbid, @PathVariable String trace_uuid) {
		Movie movie = repository.findByOmdbid(omdbid);
		Utils.trace_log("movie_service/movie/"+omdbid, "movie_service", "image_service", trace_uuid, MovieController.class);
		Image image = restTemplate.getForObject("http://images/image/get/{id}/{trace_uuid}",
				Image.class, omdbid, trace_uuid);
		movie.setPoster(image.getImage());
		return movie;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/latest/{n}")
	public MovieList getAllMovie(@PathVariable String n) {
		List<Movie> movieList = new ArrayList<Movie>();
		//call image firstly
		
		ImageList imageList = restTemplate.getForObject("http://images/image/latest/{n}", ImageList.class, n);
		for (int i = 1; i <= Integer.parseInt(n); i++) {
			Movie movie = repository.findByOmdbid(String.valueOf(i));
			movie.setPoster(imageList.getList().get(i-1).getImage());
			movieList.add(movie);
		}
		
		MovieList movieListObject = new MovieList();
		movieListObject.setMovie(movieList);
		return movieListObject;
	}

	@RequestMapping(method = RequestMethod.POST)
	public Movie postMovie(@RequestBody Movie movie) {
		return repository.save(movie);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{omdbid}")
	public Movie updateMovie(@PathVariable String omdbid, @RequestBody Movie movie) {
		Movie update = repository.findOne(omdbid);
		update.setOmdbid(movie.getOmdbid());
		update.setCast(movie.getCast());
		update.setCountry(movie.getCountry());
		update.setDirector(movie.getDirector());
		update.setFullplot(movie.getFullplot());
		update.setGenre(movie.getGenre());
		update.setImdbid(movie.getImdbid());
		update.setImdbrating(movie.getImdbrating());
		update.setLanguage(movie.getLanguage());
		update.setPlot(movie.getPlot());
		update.setPoster(movie.getPoster());
		update.setRuntime(movie.getRuntime());
		update.setTitle(movie.getTitle());
		update.setWriter(movie.getWriter());
		update.setYear(movie.getYear());
		return repository.save(update);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{omdbid}")
	public void deleteMovie(@PathVariable String omdbid) {
		repository.delete(omdbid);
	}
}
