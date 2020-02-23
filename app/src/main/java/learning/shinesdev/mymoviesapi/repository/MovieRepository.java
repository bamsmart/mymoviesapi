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
import learning.shinesdev.mymoviesapi.data.local.DatabaseConfig;
import learning.shinesdev.mymoviesapi.data.local.MovieDao;
import learning.shinesdev.mymoviesapi.model.MovieCredits;
import learning.shinesdev.mymoviesapi.model.MovieModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private static final String TAG = "MovieRepository";
    
    private static MovieRepository movieRepository;
    private MovieDao mMovieDao;
    private LiveData<List<MovieModel>> mAllFavoriteMovie;

    public static MovieRepository getInstance(){
        if (movieRepository == null){
            movieRepository = new MovieRepository();
        }
        return movieRepository;
    }

    // Movie
    private APIServiceMovie serviceMovie = null;
    public MovieRepository(){
        serviceMovie = ApiUtils.getAPIServiceMovie();
    }

    public MovieRepository(@NonNull Application application){
        DatabaseConfig config = DatabaseConfig.getDatabase(application);

        // init Movie Dao
        mMovieDao = config.movieDao();

        // get All Movie Data
        mAllFavoriteMovie = mMovieDao.getAllFavoriteMovie();
    }

    public MutableLiveData<MovieModel> getPopular(String language, String api_key){

        final MutableLiveData<MovieModel> movieData = new MutableLiveData<>();

        serviceMovie.getPopular(language,api_key).enqueue(new Callback<MovieModel>() {

            @Override
            public void onResponse(@NonNull Call<MovieModel> call, @NonNull Response<MovieModel> response) {

                if (response.isSuccessful()){
                    movieData.setValue(response.body());
                }
            }
            @Override
            public void onFailure(@NonNull Call<MovieModel> call, @NonNull Throwable t) {
                //movieData.setValue(null);
            }
        });

        return movieData;
    }


    public MutableLiveData<MovieModel> getRecommendations(int id, String lang, String key){
        final MutableLiveData<MovieModel> movieData = new MutableLiveData<>();

        Log.d(TAG, "getRecommendations: "+key);
        serviceMovie.getRecommendations(id,lang,key).enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(@NonNull Call<MovieModel> call, @NonNull Response<MovieModel> response) {
                Log.d(TAG, "onResponse: "+response.isSuccessful());
                if (response.isSuccessful()){
                    movieData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieModel> call, @NonNull Throwable t) {
                /*movieData.setValue(null);
                Log.d("DATA MOVIE RECOM",""+t.getMessage());*/
                t.printStackTrace();
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

    public LiveData<List<MovieModel>> getFavorite() {
        return mAllFavoriteMovie;
    }

    public void addToFavorite(MovieModel movie){
        new AddMovie().execute(movie);
    }

    //Async task to add movie
    public class AddMovie extends AsyncTask<MovieModel, Void, Void> {
        @Override
        protected Void doInBackground(MovieModel... movie) {
            mMovieDao.insertMovie(movie[0]);
            return null;
        }
    }

    public void deleteFromFavorite(MovieModel movie){
       new DeleteMovie().execute(movie);
    }

    //Async task to delete movie
    public class DeleteMovie extends AsyncTask<MovieModel, Void, Void> {
        @Override
        protected Void doInBackground(MovieModel... movie) {
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
