import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class RatingParser {

	private final static String SEPARATOR = "\t";
	private final String fileName;

	public RatingParser(final String fileName) {
		this.fileName = fileName;
	}

	public List<Rating> getRatingList() throws Exception {
		List<Rating> ratingList = new ArrayList<Rating>();

		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = "";

		while ((line = br.readLine()) != null) {
			String[] ratingDetails = line.split(SEPARATOR);

			Rating rating = new RatingBuilder()
				.withUserId(ratingDetails[0])
				.withMovieId(ratingDetails[1])
				.withRating(ratingDetails[2])
				.withTimestamp(ratingDetails[3])
				.build();
			ratingList.add(rating);
		}

		br.close();

		return ratingList;
	}
}
