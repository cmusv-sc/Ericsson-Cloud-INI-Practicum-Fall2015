import java.util.HashMap;

public class Genres {

	private static final HashMap<Integer, String> genres = new HashMap<Integer, String>();

	static {
		genres.put(1, "unknown");
		genres.put(2, "Action");
		genres.put(3, "Adventure");
		genres.put(4, "Animation");
		genres.put(5, "Children's");
		genres.put(6, "Comedy");
		genres.put(7, "Crime");
		genres.put(8, "Documentary");
		genres.put(9, "Drama");
		genres.put(10, "Fantasy");
		genres.put(11, "Film-Noir");
		genres.put(12, "Horror");
		genres.put(13, "Musical");
		genres.put(14, "Mystery");
		genres.put(15, "Romance");
		genres.put(16, "Sci-Fi");
		genres.put(17, "Thriller");
		genres.put(18, "War");
		genres.put(19, "Western");
	}

	public static String getGenre(int id) {
		return genres.get(id);
	}
}
