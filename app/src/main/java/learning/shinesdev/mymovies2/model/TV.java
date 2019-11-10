package learning.shinesdev.mymovies2.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TV implements Parcelable {
    public static final Creator<TV> CREATOR = new Creator<TV>() {
        @Override
        public TV createFromParcel(Parcel in) {
            return new TV(in);
        }

        @Override
        public TV[] newArray(int size) {
            return new TV[size];
        }
    };

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
    private String synopsis;
    private String stars;
    private String votes;
    private String image;
    private String nextImage1;
    private String nextImage2;
    private String nextImage3;

    public TV() {
    }

    private TV(Parcel in) {
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
        synopsis = in.readString();
        stars = in.readString();
        votes = in.readString();
        image = in.readString();

        nextImage1 = in.readString();
        nextImage2 = in.readString();
        nextImage3 = in.readString();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }



    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
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
        dest.writeString(synopsis);
        dest.writeString(stars);
        dest.writeString(votes);
        dest.writeString(image);
        dest.writeString(nextImage1);
        dest.writeString(nextImage2);
        dest.writeString(nextImage3);
    }

}
