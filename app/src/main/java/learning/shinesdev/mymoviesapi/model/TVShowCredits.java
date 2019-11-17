package learning.shinesdev.mymoviesapi.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import learning.shinesdev.mymoviesapi.api.ApiUtils;
import learning.shinesdev.mymoviesapi.repository.TVShowRepository;

public class TVShowCredits extends ViewModel {
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
    private List<TVShowCredits> creditsList;

    public String getName() {
        return name;
    }

    public List<TVShowCredits> getCreditsList() {
        return creditsList;
    }

    private MutableLiveData<TVShowCredits> mutableLiveData;

    public void init(int id){
        if (mutableLiveData != null){
            return;
        }
       TVShowRepository tvShowRepository = TVShowRepository.getInstance();
        mutableLiveData = tvShowRepository.getCredits(id, ApiUtils.API_KEY);
    }

    public LiveData<TVShowCredits> getTVShowRepository() {
        return mutableLiveData;
    }
}
