package learning.shinesdev.mymoviesapi.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import learning.shinesdev.mymoviesapi.R;
import learning.shinesdev.mymoviesapi.api.ApiUtils;

public class Movie extends ViewModel {
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

    public void init(){
        if (mutableLiveData != null){
            return;
        }
        movieRepository = MovieRepository.getInstance();
        mutableLiveData = movieRepository.getPopular(String.valueOf(R.string.language),ApiUtils.API_KEY);
    }

    public void initRecommendation(int id){
        if (mutableLiveData != null){
            return;
        }
        movieRepository = MovieRepository.getInstance();
        mutableLiveData = movieRepository.getRecommendations(id,String.valueOf(R.string.language),ApiUtils.API_KEY);
    }

    public LiveData<Movie> getMovieRepository() {
        return mutableLiveData;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_result() {
        return total_result;
    }

    public void setTotal_result(int total_result) {
        this.total_result = total_result;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<MovieModel> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<MovieModel> movieList) {
        this.movieList = movieList;
    }
}
