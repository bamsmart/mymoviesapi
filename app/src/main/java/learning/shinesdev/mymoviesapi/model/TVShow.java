package learning.shinesdev.mymoviesapi.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import learning.shinesdev.mymoviesapi.api.ApiUtils;
import learning.shinesdev.mymoviesapi.repository.TVShowRepository;

public class TVShow extends ViewModel {
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
    private List<TVShowModel> tvshowList;
    private MutableLiveData<TVShow> mutableLiveData;
    private TVShowRepository tvShowRepository;

    public void init(String language){
        /*if (mutableLiveData != null){
            return;
        }*/
        tvShowRepository = TVShowRepository.getInstance();
        mutableLiveData = tvShowRepository.getPopular(language,ApiUtils.API_KEY);
    }

    public void initRecommendation(int id,String language){
        /*if (mutableLiveData != null){
            return;
        }*/
        tvShowRepository = TVShowRepository.getInstance();
        mutableLiveData = tvShowRepository.getRecommendations(id,language,ApiUtils.API_KEY);
    }

    public LiveData<TVShow> getTVShowRepository() {
        return mutableLiveData;
    }

    public List<TVShowModel> getTVShowList() {
        return tvshowList;
    }

}
