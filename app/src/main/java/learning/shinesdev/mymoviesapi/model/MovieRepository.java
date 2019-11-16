package learning.shinesdev.mymoviesapi.model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import learning.shinesdev.mymoviesapi.api.APIServiceMovie;
import learning.shinesdev.mymoviesapi.api.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private static MovieRepository newsRepository;

    public static MovieRepository getInstance(){
        if (newsRepository == null){
            newsRepository = new MovieRepository();
        }
        return newsRepository;
    }

    private final APIServiceMovie serviceMovie;

    public MovieRepository(){
        serviceMovie = ApiUtils.getAPIServiceMovie();
    }

    public MutableLiveData<Movie> getPopular(String lang,String key){
        final MutableLiveData<Movie> movieData = new MutableLiveData<>();
        serviceMovie.getPopular(lang,key).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()){
                    movieData.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                movieData.setValue(null);
                Log.d("DATA NULL",""+t.getMessage());
            }
        });
        return movieData;
    }

    public MutableLiveData<Movie> getRecommendations(int id, String lang,String key){
        final MutableLiveData<Movie> movieData = new MutableLiveData<>();
        serviceMovie.getRecommendations(id,lang,key).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()){
                    movieData.setValue(response.body());
                    Log.d("",""+response.body());
                }
            }
            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                movieData.setValue(null);
                Log.d("DATA MOVIE RECOM",""+t.getMessage());
            }
        });
        return movieData;
    }

    public MutableLiveData<MovieModel> getDetail(int id,String lang, String key){
        final MutableLiveData<MovieModel> movieData = new MutableLiveData<>();
        serviceMovie.getDetails(id,lang,key).enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if (response.isSuccessful()){
                    movieData.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                movieData.setValue(null);
                Log.d("DATA NULL",""+t.getMessage());
            }
        });
        return movieData;
    }

    public MutableLiveData<MovieCredits> getCredits(int id, String key){
        final MutableLiveData<MovieCredits> movieCredit = new MutableLiveData<>();
        serviceMovie.getCredits(id,key).enqueue(new Callback<MovieCredits>() {
            @Override
            public void onResponse(Call<MovieCredits> call, Response<MovieCredits> response) {
                if(response.isSuccessful()){
                    movieCredit.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MovieCredits> call, Throwable t) {
                Log.d("ON Failure",t.getMessage());
            }
        });
        return movieCredit;
    }
}
