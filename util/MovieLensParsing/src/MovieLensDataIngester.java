import java.util.List;

public class MovieLensDataIngester {

	public static void main(String[] args) throws Exception {
		if (args == null || args.length == 0)
			throw new IllegalArgumentException("Please provide the input file");

		String inputFileName = args[0];

		MovieLensDataSetParser parser = new MovieLensDataSetParser(inputFileName);
		List<Movie> movies = parser.getMoviesList();

		//TODO: Put the movies into MongoDB
		System.out.println(movies.get(1).toString());
	}
}
