package learning.shinesdev.mymoviesapi.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import learning.shinesdev.mymoviesapi.data.api.ApiUtils;
import learning.shinesdev.mymoviesapi.repository.MovieRepository;

public class MovieCredits extends ViewModel {
    @SerializedName("id")
    @Expose
    private
    int id;

    @SerializedName("character")
    @Expose
    private
    String character;

    @SerializedName("name")
    @Expose
    private
    String name;

    @SerializedName("profile_path")
    @Expose
    private String image;

    @SerializedName("cast")
    @Expose
    private List<MovieCredits> creditsList;

    public String getName() {
        return name;
    }

    public List<MovieCredits> getCreditsList() {
        return creditsList;
    }

    private MutableLiveData<MovieCredits> mutableLiveData;

    public void init(int id){
        if (mutableLiveData != null){
            return;
        }
        MovieRepository movieRepository = MovieRepository.getInstance();
        mutableLiveData = movieRepository.getCredits(id, ApiUtils.API_KEY);
    }

    public LiveData<MovieCredits> getMovieRepository() {
        return mutableLiveData;
    }
}
