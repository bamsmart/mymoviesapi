package learning.shinesdev.mymoviesapi.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import learning.shinesdev.mymoviesapi.api.ApiUtils;

public class MovieCredits extends ViewModel {
    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("character")
    @Expose
    String character;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("profile_path")
    @Expose
    private String image;

    @SerializedName("cast")
    @Expose
    private List<MovieCredits> creditsList;

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<MovieCredits> getCreditsList() {
        return creditsList;
    }

    public void setCreditsList(List<MovieCredits> creditsList) {
        this.creditsList = creditsList;
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
