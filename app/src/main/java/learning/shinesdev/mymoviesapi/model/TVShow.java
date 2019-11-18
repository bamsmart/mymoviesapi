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
import learning.shinesdev.mymoviesapi.repository.TVShowRepository;

public class TVShow extends ViewModel  implements Parcelable {
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

    public TVShow() {
    }
    public void init(String prevLang,String currLang){
        if (mutableLiveData != null && (prevLang.isEmpty() || prevLang.equals(currLang))){
            return;
        }
        tvShowRepository = TVShowRepository.getInstance();
        mutableLiveData = tvShowRepository.getPopular(currLang,ApiUtils.API_KEY);
    }

    public void initRecommendation(int id,String currLang){
        if (mutableLiveData != null){
            return;
        }
        tvShowRepository = TVShowRepository.getInstance();
        mutableLiveData = tvShowRepository.getRecommendations(id,currLang,ApiUtils.API_KEY);
    }

    public LiveData<TVShow> getTVShowRepository() {
        return mutableLiveData;
    }

    public List<TVShowModel> getTVShowList() {
        return tvshowList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(tvshowList);
    }

    @SuppressWarnings("unchecked")
    private TVShow(Parcel in) {
        tvshowList = in.readArrayList(TVShowModel.class.getClassLoader());
    }

    public static final Creator<TVShow> CREATOR = new Creator<TVShow>() {
        @Override
        public TVShow createFromParcel(Parcel in) {
            return new TVShow(in);
        }

        @Override
        public TVShow[] newArray(int size) {
            return new TVShow[size];
        }
    };
}
