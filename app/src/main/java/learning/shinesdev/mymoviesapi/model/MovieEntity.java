package learning.shinesdev.mymoviesapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("ALL")
@Entity(primaryKeys = ("id"))
public class MovieEntity implements Parcelable {

    public static final Creator<MovieEntity> CREATOR = new Creator<MovieEntity>() {
        @Override
        public MovieEntity createFromParcel(Parcel in) {
            return new MovieEntity(in);
        }

        @Override
        public MovieEntity[] newArray(int size) {
            return new MovieEntity[size];
        }
    };

    @SerializedName("popularity")
    @Expose
    private double popularity;

    @SerializedName("vote_count")
    @Expose
    @ColumnInfo(name = "vote_count")
    private int vote;

    @SerializedName("poster_path")
    @Expose
    @ColumnInfo(name = "poster_path")
    private String image;


    @SerializedName("id")
    @Expose
    @ColumnInfo(name = "id")
    private int id;


    @SerializedName("title")
    @Expose
    @ColumnInfo(name = "title")
    private String title;


    @SerializedName("vote_average")
    @Expose
    private double rating;

    @SerializedName("overview")
    @Expose
    @ColumnInfo(name = "overview")
    private String overview;


    @SerializedName("release_date")
    @Expose
    @ColumnInfo(name = "release_date")
    private String date;
    @SerializedName("revenue")
    @Expose
    @Ignore
    private int revenue;
    @SerializedName("budget")
    @Expose
    @Ignore
    private String budget;

    public MovieEntity() {
    }

    private MovieEntity(Parcel in) {
        id = in.readInt();
        title = in.readString();
        date = in.readString();
        overview = in.readString();
        image = in.readString();
        vote = in.readInt();
        revenue = in.readInt();
    }

    public String getImage() {
        return image;
    }

    @SuppressWarnings("unused")
    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    @SuppressWarnings("unused")
    public void setTitle(String title) {
        this.title = title;
    }

    public int getVote() {
        return vote;
    }

    @SuppressWarnings("unused")
    public void setVote(int vote) {
        this.vote = vote;
    }

    public int getId() {
        return id;
    }

    @SuppressWarnings("unused")
    public void setId(int id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    @SuppressWarnings("unused")
    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getOverview() {
        return overview;
    }

    @SuppressWarnings("unused")
    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getDate() {
        return date;
    }

    @SuppressWarnings("unused")
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(date);
        dest.writeString(overview);
        dest.writeString(image);
        dest.writeInt(vote);
        dest.writeInt(revenue);
    }

    public int getRevenue() {
        return revenue;
    }

    @SuppressWarnings("unused")
    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    @SuppressWarnings("unused")
    public double getPopularity() {
        return popularity;
    }

    @SuppressWarnings("unused")
    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    @SuppressWarnings("unused")
    public String getBudget() {
        return budget;
    }

    @SuppressWarnings("unused")
    public void setBudget(String budget) {
        this.budget = budget;
    }
}
