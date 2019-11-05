package learning.shinesdev.mymovies.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
    private String number;
    private String title;
    private String nextTitle1;
    private String nextTitle2;
    private String nextTitle3;
    private String date;
    private String year;
    private String group;
    private String duration;
    private String genre;
    private String rating;
    private String metascore;
    private String synopsis;
    private String director;
    private String stars;
    private String votes;
    private String gross;
    private String image;
    private String nextImage1;
    private String nextImage2;
    private String nextImage3;

    public Movie() {
    }

    private Movie(Parcel in) {
        number = in.readString();
        title = in.readString();
        nextTitle1 = in.readString();
        nextTitle2 = in.readString();
        nextTitle3 = in.readString();
        date = in.readString();
        year = in.readString();
        group = in.readString();
        duration = in.readString();
        genre = in.readString();
        rating = in.readString();
        metascore = in.readString();
        synopsis = in.readString();
        director = in.readString();
        stars = in.readString();
        votes = in.readString();
        gross = in.readString();
        image = in.readString();

        nextImage1 = in.readString();
        nextImage2 = in.readString();
        nextImage3 = in.readString();
    }

    public static Creator<Movie> getCREATOR() {
        return CREATOR;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }

    public String getGross() {
        return gross;
    }

    public void setGross(String gross) {
        this.gross = gross;
    }

    public String getNextTitle1() {
        return nextTitle1;
    }

    public void setNextTitle1(String nextTitle1) {
        this.nextTitle1 = nextTitle1;
    }

    public String getNextTitle2() {
        return nextTitle2;
    }

    public void setNextTitle2(String nextTitle2) {
        this.nextTitle2 = nextTitle2;
    }

    public String getNextTitle3() {
        return nextTitle3;
    }

    public void setNextTitle3(String nextTitle3) {
        this.nextTitle3 = nextTitle3;
    }

    public String getNextImage1() {
        return nextImage1;
    }

    public void setNextImage1(String nextImage1) {
        this.nextImage1 = nextImage1;
    }

    public String getNextImage2() {
        return nextImage2;
    }

    public void setNextImage2(String nextImage2) {
        this.nextImage2 = nextImage2;
    }

    public String getNextImage3() {
        return nextImage3;
    }

    public void setNextImage3(String nextImage3) {
        this.nextImage3 = nextImage3;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(number);
        dest.writeString(title);
        dest.writeString(nextTitle1);
        dest.writeString(nextTitle2);
        dest.writeString(nextTitle3);
        dest.writeString(date);
        dest.writeString(year);
        dest.writeString(group);
        dest.writeString(duration);
        dest.writeString(genre);
        dest.writeString(rating);
        dest.writeString(metascore);
        dest.writeString(synopsis);
        dest.writeString(director);
        dest.writeString(stars);
        dest.writeString(votes);
        dest.writeString(gross);
        dest.writeString(image);
        dest.writeString(nextImage1);
        dest.writeString(nextImage2);
        dest.writeString(nextImage3);
    }

}
