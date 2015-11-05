package search.dao;

import java.io.Serializable;

/**
 * Created by Ricky on 10/25/15.
 */
public class Movie implements Serializable{
    private long id;
    private String information;
    private float rating;
    private String type;

    public Movie() {}
    public Movie(long id, String information, float rating, String type) {
        this.id = id;
        this.information = information;
        this.rating = rating;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", information='" + information + '\'' +
                ", rating=" + rating +
                ", type='" + type + '\'' +
                '}';
    }
}
