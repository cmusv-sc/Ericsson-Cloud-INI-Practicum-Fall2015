package search.api;

/**
 * Created by Ricky on 10/25/15.
 */
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.search.suggest.analyzing.AnalyzingInfixSuggester;
import org.apache.lucene.search.suggest.Lookup;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.Version;
import org.apache.lucene.search.suggest.Lookup.LookupResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import search.dao.Movie;
import search.dao.MovieIterator;
import util.SearchIndexBuilder;

public class MovieSuggestion {
    private static AnalyzingInfixSuggester suggester;
    private static Logger LOG = LoggerFactory.getLogger(MovieSuggestion.class);

    public MovieSuggestion(RAMDirectory directory, Version version, String fileName) {
        StandardAnalyzer analyzer = new StandardAnalyzer(version);
        MovieIterator iterator = SearchIndexBuilder.buildMovieIterator(fileName);
        try {
            this.suggester = new AnalyzingInfixSuggester(version, directory, analyzer);
            suggester.build(iterator);

        } catch (IOException e) {
            LOG.error("Couldn't build suggester.{}", e);
        }

    }

    // Get suggestions given a prefix and a region.
    public static List<LookupResult> lookup(String information, String type) {
        List<LookupResult> results = new ArrayList<>();
        try {
            HashSet<BytesRef> contexts = new HashSet<>();
            contexts.add(new BytesRef(type.getBytes("UTF8")));
            results = suggester.lookup(information, contexts, 5, true, false);
        } catch (IOException e) {
            LOG.warn("couldn't convert the type to UTF8: {}", e);
        }
        return results;
    }

    // Deserialize a Movie from a LookupResult payload.
    public static Movie getMovie(LookupResult result) {
        Movie movie = new Movie();
        try {
            BytesRef payload = result.payload;
            if (payload != null) {
                ByteArrayInputStream bis = new ByteArrayInputStream(payload.bytes);
                ObjectInputStream in = new ObjectInputStream(bis);
                movie = (Movie) in.readObject();
            }
        } catch (Exception e) {
            LOG.error("Couldn't decode payload. {}", e);
        }
        return movie;
    }
}
