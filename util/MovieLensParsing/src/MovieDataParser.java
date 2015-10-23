import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MovieDataParser {

	private final static String SEPARATOR = "\\|";
	private final String fileName;

	public MovieDataParser(final String fileName) {
		this.fileName = fileName;
	}

	public List<Movie> getMoviesList() throws Exception {
		List<Movie> movieList = new ArrayList<Movie>();

		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = "";

		while ((line = br.readLine()) != null) {
			String[] movieDetails = line.split(SEPARATOR);

			Movie movie = new MovieBuilder()
				.withId(movieDetails[0])
				.withTitle(movieDetails[1])
				.withReleaseDate(movieDetails[2])
				.withVideoReleaseDate(movieDetails[3])
				.withImdbUrl(movieDetails[4])
				.withGenreString(getGenreString(movieDetails, 5))
				.build();

			movieList.add(movie);
		}

		br.close();

		return movieList;
	}

	private String getGenreString(String[] genreBooleanString, int offsetForParsing) {
		StringBuilder genreBuilder = new StringBuilder();
		String separator = "";
		
		for (int i = 0; i < 19; i++) {
			String flag = genreBooleanString[i + offsetForParsing];
			if (flag.equals("1")) {
				genreBuilder.append(separator);
				genreBuilder.append(Genres.getGenre(i));
				separator = ",";
			}
		}
		return genreBuilder.toString();
	}
}
