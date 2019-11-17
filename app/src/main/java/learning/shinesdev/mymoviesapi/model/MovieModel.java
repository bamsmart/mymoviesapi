package learning.shinesdev.mymoviesapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import learning.shinesdev.mymoviesapi.api.ApiUtils;
import learning.shinesdev.mymoviesapi.repository.MovieRepository;

public class MovieModel extends ViewModel implements Parcelable {
    @SerializedName("popularity")
    @Expose
    private double popularity;

    @SerializedName("vote_count")
    @Expose
    private int vote;

    @SerializedName("poster_path")
    @Expose
    private String image;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("vote_average")
    @Expose
    private double rating;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("release_date")
    @Expose
    private String date;

    @SerializedName("revenue")
    @Expose
    private String revenue;

    @SerializedName("budget")
    @Expose
    private String budget;

    public MovieModel() {
    }

    private MutableLiveData<MovieModel> mutableLiveData;

    public void init(int id,String language){
        /*if (mutableLiveData != null){
            return;
        }*/
        MovieRepository movieRepository = MovieRepository.getInstance();
        mutableLiveData = movieRepository.getDetail(id, language,ApiUtils.API_KEY);
    }

    public LiveData<MovieModel> getMovieRepository() {
        return mutableLiveData;
    }

    private MovieModel(Parcel in) {
        id = in.readInt();
        title = in.readString();
        date = in.readString();
        image = in.readString();
    }
    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public int getVote() {
        return vote;
    }

    public int getId() {
        return id;
    }

    public double getRating() {
        return rating;
    }

    public String getOverview() {
        return overview;
    }

    public String getDate() {
        return date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(id);
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public String getRevenue() {
        return revenue;
    }

}
