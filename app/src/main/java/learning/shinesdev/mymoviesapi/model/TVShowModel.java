package learning.shinesdev.mymoviesapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import learning.shinesdev.mymoviesapi.api.ApiUtils;
import learning.shinesdev.mymoviesapi.repository.TVShowRepository;

public class TVShowModel  extends ViewModel implements Parcelable {
    public static final Creator<TVShowModel> CREATOR = new Creator<TVShowModel>() {
        @Override
        public TVShowModel createFromParcel(Parcel in) {
            return new TVShowModel(in);
        }

        @Override
        public TVShowModel[] newArray(int size) {
            return new TVShowModel[size];
        }
    };

    @SerializedName("original_name")
    @Expose
    private String original_name;

    @SerializedName("genre_ids")
    @Expose
    private int[] genre_ids;

    @SerializedName("name")
    @Expose
    private
    String name;

    @SerializedName("popularity")
    @Expose
    private
    Double popularity;

    @SerializedName("vote_count")
    @Expose
    private
    int vote_count;

    @SerializedName("first_air_date")
    @Expose
    private
    String first_air_date;

    @SerializedName("backdrop_path")
    @Expose
    private
    String backdrop_path;

    @SerializedName("original_language")
    @Expose
    private
    String original_language;

    @SerializedName("id")
    @Expose
    private
    int id;
    @SerializedName("vote_average")
    @Expose
    private
    Double vote_average;

    @SerializedName("overview")
    @Expose
    private
    String overview;

    @SerializedName("poster_path")
    @Expose
    private
    String poster_path;

    public TVShowModel() {
    }

    private TVShowModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        first_air_date = in.readString();
        overview = in.readString();
        poster_path = in.readString();
        vote_count = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(first_air_date);
        dest.writeString(overview);
        dest.writeString(poster_path);
        dest.writeInt(vote_count);
    }

    public String getName() {
        return name;
    }

    public int getVote_count() {
        return vote_count;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public int getId() {
        return id;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    private MutableLiveData<TVShowModel> mutableLiveData;

    public void init(int id,String language){
        if (mutableLiveData != null){
            return;
        }
        TVShowRepository tvShowRepository = TVShowRepository.getInstance();
        mutableLiveData = tvShowRepository.getDetail(id,language, ApiUtils.API_KEY);
    }

    public LiveData<TVShowModel> getTVShowRepository() {
        return mutableLiveData;
    }
}
