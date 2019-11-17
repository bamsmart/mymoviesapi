package learning.shinesdev.mymoviesapi.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import learning.shinesdev.mymoviesapi.api.APIServiceMovie;
import learning.shinesdev.mymoviesapi.api.ApiUtils;
import learning.shinesdev.mymoviesapi.model.Movie;
import learning.shinesdev.mymoviesapi.model.MovieCredits;
import learning.shinesdev.mymoviesapi.model.MovieModel;
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

    private MovieRepository(){
        serviceMovie = ApiUtils.getAPIServiceMovie();
    }

    public MutableLiveData<Movie> getPopular(String lang, String key){
        final MutableLiveData<Movie> movieData = new MutableLiveData<>();
        serviceMovie.getPopular(lang,key).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {
                if (response.isSuccessful()){
                    movieData.setValue(response.body());
                }
            }
            @Override
            public void onFailure(@NonNull Call<Movie> call, @NonNull Throwable t) {
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
            public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {
                if (response.isSuccessful()){
                    movieData.setValue(response.body());
                    Log.d("",""+response.body());
                }
            }
            @Override
            public void onFailure(@NonNull Call<Movie> call, @NonNull Throwable t) {
                movieData.setValue(null);
                Log.d("DATA MOVIE RECOM",""+t.getMessage());
            }
        });
        return movieData;
    }

    public MutableLiveData<MovieModel> getDetail(int id, String lang, String key){
        final MutableLiveData<MovieModel> movieData = new MutableLiveData<>();
        serviceMovie.getDetails(id,lang,key).enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(@NonNull Call<MovieModel> call, @NonNull Response<MovieModel> response) {
                if (response.isSuccessful()){
                    movieData.setValue(response.body());
                }
            }
            @Override
            public void onFailure(@NonNull Call<MovieModel> call, @NonNull Throwable t) {
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
            public void onResponse(@NonNull Call<MovieCredits> call, @NonNull Response<MovieCredits> response) {
                if(response.isSuccessful()){
                    movieCredit.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieCredits> call, @NonNull Throwable t) {
                Log.d("ON Failure",t.getMessage());
            }
        });
        return movieCredit;
    }
}
