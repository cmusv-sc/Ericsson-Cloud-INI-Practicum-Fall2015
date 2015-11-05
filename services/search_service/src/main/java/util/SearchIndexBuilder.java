package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import search.dao.Movie;
import search.dao.MovieIterator;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ricky on 10/25/15.
 */
public class SearchIndexBuilder {
    private static Logger LOG = LoggerFactory.getLogger(SearchIndexBuilder.class);

    public static MovieIterator buildMovieIterator(String fileName) {
        MovieIterator movieIterator = null;
        List<Movie> movies = new LinkedList<>();
        ApplicationContext appContext =
                new ClassPathXmlApplicationContext();

        Resource resource =
                appContext.getResource("classpath:" + fileName);

        try {
            InputStream is = resource.getInputStream();
            BufferedReader bf = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = bf.readLine()) != null) {
                String[] elements = line.split("\t");
                long id = Long.parseLong(elements[0]);
                String information = elements[1] + "," + elements[3] + "," + elements[4];
                float rating = 0;
                try {
                    rating = Float.parseFloat(elements[2]);
                } catch (Exception e) {
                    rating = 0;
                }
                String type = elements[5];
                Movie movie = new Movie(id, information, rating, type);
                movies.add(movie);
            }
        } catch (FileNotFoundException fe) {
            LOG.error("no file found.{}", fe);
        } catch (IOException ie) {
            LOG.error("readline error: {}", ie);
        } catch (Exception e) {
            LOG.error("General exception: {}", e);
        }
        movieIterator = new MovieIterator(movies.iterator());
        return movieIterator;
    }
}
