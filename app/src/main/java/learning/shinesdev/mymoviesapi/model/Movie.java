package learning.shinesdev.mymoviesapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import learning.shinesdev.mymoviesapi.api.ApiUtils;
import learning.shinesdev.mymoviesapi.repository.MovieRepository;

public class Movie extends ViewModel implements Parcelable {
    @SerializedName("page")
    @Expose
    private int page;

    @SerializedName("total_results")
    @Expose
    private int total_result;

    @SerializedName("total_pages")
    @Expose
    private int total_pages;

    @SerializedName("results")
    @Expose
    private List<MovieModel> movieList;

    private MutableLiveData<Movie> mutableLiveData;
    private MovieRepository movieRepository;


    public Movie() {
    }


    public void init(String prevLang, String currLang) {
        if (mutableLiveData != null && (prevLang.isEmpty() || prevLang.equals(currLang))) {
            return;
        }
        movieRepository = MovieRepository.getInstance();
        mutableLiveData = movieRepository.getPopular(currLang, ApiUtils.API_KEY);
    }

    public void initRecommendation(int id, String prevLang, String currLang) {
        if (mutableLiveData != null && (prevLang.isEmpty() || prevLang.equals(currLang))) {
            return;
        }
        movieRepository = MovieRepository.getInstance();
        mutableLiveData = movieRepository.getRecommendations(id, currLang, ApiUtils.API_KEY);
    }

    public LiveData<Movie> getMovieRepository() {
        return mutableLiveData;
    }

    public List<MovieModel> getMovieList() {
        return movieList;
    }


    @SuppressWarnings("unchecked")
    private Movie(Parcel in) {
        movieList = in.readArrayList(MovieModel.class.getClassLoader());
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(movieList);
    }
}
