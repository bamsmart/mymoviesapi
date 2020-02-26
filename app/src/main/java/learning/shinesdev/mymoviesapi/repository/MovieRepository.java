package learning.shinesdev.mymoviesapi.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import learning.shinesdev.mymoviesapi.data.api.APIServiceMovie;
import learning.shinesdev.mymoviesapi.data.api.ApiUtils;
import learning.shinesdev.mymoviesapi.data.api.response.MovieApiResponse;
import learning.shinesdev.mymoviesapi.data.local.AppDatabase;
import learning.shinesdev.mymoviesapi.data.local.MovieDao;
import learning.shinesdev.mymoviesapi.model.MovieCredits;
import learning.shinesdev.mymoviesapi.model.MovieEntity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    
    private static MovieRepository movieRepository;
    private static APIServiceMovie serviceMovie;

    private MovieDao mMovieDao;
    private LiveData<List<MovieEntity>> mAllFavoriteMovie;

    public static MovieRepository getInstance(){
        if (movieRepository == null){
            movieRepository = new MovieRepository();
            serviceMovie = ApiUtils.getAPIServiceMovie();
        }
        return movieRepository;
    }

    MovieRepository(){}

    public MovieRepository(@NonNull Application application){
        AppDatabase config = AppDatabase.getDatabase(application);

        // init Movie Dao
        mMovieDao = config.movieDao();

        // get All Movie Data
        mAllFavoriteMovie = mMovieDao.getAllFavoriteMovie();
    }

    public MutableLiveData<MovieApiResponse> getPopular(String language, String api_key){

        final MutableLiveData<MovieApiResponse> movieData = new MutableLiveData<>();

        serviceMovie.getPopular(language,api_key).enqueue(new Callback<MovieApiResponse>() {

            @Override
            public void onResponse(@NonNull Call<MovieApiResponse> call, @NonNull Response<MovieApiResponse> response) {

                if (response.isSuccessful()){
                    movieData.setValue(response.body());
                }
            }
            @Override
            public void onFailure(@NonNull Call<MovieApiResponse> call, @NonNull Throwable t) {
               t.printStackTrace();
            }
        });

        return movieData;
    }


    public MutableLiveData<MovieApiResponse> getRecommendations(int id, String lang, String key){
        final MutableLiveData<MovieApiResponse> movieData = new MutableLiveData<>();

        serviceMovie.getRecommendations(id,lang,key).enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieApiResponse> call, @NonNull Response<MovieApiResponse> response) {

                if (response.isSuccessful()){
                    movieData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieApiResponse> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
        return movieData;
    }

    public MutableLiveData<MovieEntity> getDetail(int id, String lang, String key){
        final MutableLiveData<MovieEntity> movieData = new MutableLiveData<>();
        serviceMovie.getDetails(id,lang,key).enqueue(new Callback<MovieEntity>() {
            @Override
            public void onResponse(@NonNull Call<MovieEntity> call, @NonNull Response<MovieEntity> response) {
                if (response.isSuccessful()){
                    movieData.setValue(response.body());
                }
            }
            @Override
            public void onFailure(@NonNull Call<MovieEntity> call, @NonNull Throwable t) {
                movieData.setValue(null);
                Log.d("DATA NULL",""+t.getMessage());
            }
        });
        return movieData;
    }

    public LiveData<List<MovieEntity>> getFavorite() {
        return mAllFavoriteMovie;
    }

    public void addToFavorite(MovieEntity movie){
        new AddMovie().execute(movie);
    }

    //Async task to add movie
    public class AddMovie extends AsyncTask<MovieEntity, Void, Void> {
        @Override
        protected Void doInBackground(MovieEntity... movie) {
            mMovieDao.insertMovie(movie[0]);
            return null;
        }
    }

    public void deleteFromFavorite(MovieEntity movie){
       new DeleteMovie().execute(movie);
    }

    //Async task to delete movie
    public class DeleteMovie extends AsyncTask<MovieEntity, Void, Void> {
        @Override
        protected Void doInBackground(MovieEntity... movie) {
            mMovieDao.deleteMovie(movie[0]);
            return null;
        }
    }























    public MutableLiveData<MovieCredits> getCredits(int id, String key){
        final MutableLiveData<MovieCredits> movieCredit = new MutableLiveData<>();
        /*serviceMovie.getCredits(id,key).enqueue(new Callback<MovieCredits>() {
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
        });*/
        return movieCredit;
    }




}
