import java.util.List;

public class MovieLensDataIngester {

	private static final String MOVIE_DATA_FILE_PATH = "/Users/mayank/Downloads/ml-100k/u.item";
	private static final String RATING_DATA_FILE_PATH = "/Users/mayank/Downloads/ml-100k/u.data";
	
	public static void main(String[] args) throws Exception {

		MovieDataParser movieParser = new MovieDataParser(MOVIE_DATA_FILE_PATH);
		List<Movie> movieList = movieParser.getMoviesList();

		//TODO: Put the movies into MongoDB
		System.out.println(movieList.get(0).toString());
		
		RatingParser ratingParser = new RatingParser(RATING_DATA_FILE_PATH);
		List<Rating> ratingList = ratingParser.getRatingList();
		
		//TODO: Put the ratings into MongoDB
		System.out.println("\n" + ratingList.get(0).toString());
	}
}
