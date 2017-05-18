package sg.edu.rp.c347.p05_songsofsingapore;

import java.io.Serializable;

/**
 * Created by 15017274 on 18/5/2017.
 */

public class Song implements Serializable{
    private int id;
    private String title;
    private  String singers;
    private int year;
    private int stars;

    public Song(int id, String title, String singers, int year, int stars) {
        this.id = id;
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSingers() {
        return singers;
    }

    public int getYear() {
        return year;
    }

    public int getStars() {
        return stars;
    }


}
