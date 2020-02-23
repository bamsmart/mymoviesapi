package learning.shinesdev.mymoviesapi.viewmodel;


import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import learning.shinesdev.mymoviesapi.data.api.ApiUtils;
import learning.shinesdev.mymoviesapi.model.MovieModel;
import learning.shinesdev.mymoviesapi.repository.MovieRepository;

public class MovieViewModel extends ViewModel {
    
    private MovieRepository movieRepository;
    private MutableLiveData<MovieModel> mutableLiveData;
    private MutableLiveData<MovieModel> recommLiveData;
    private LiveData<List<MovieModel>> mAllFavoriteMovie;

    public void init(@NonNull Application application) {
        movieRepository = new MovieRepository(application);
        mAllFavoriteMovie = movieRepository.getFavorite();
    }

    public LiveData<MovieModel> getMovie() {
        return mutableLiveData;
    }

    public LiveData<MovieModel> getRecommMovie() {
        return recommLiveData;
    }

    public void init(String language) {
        if (mutableLiveData != null) {
            return;
        }
        movieRepository = MovieRepository.getInstance();
        mutableLiveData = movieRepository.getPopular(language, ApiUtils.API_KEY);
    }

    public void getDetail(int id, String prevLang, String currLang) {
        if (mutableLiveData != null && (prevLang.isEmpty() || prevLang.equals(currLang))) {
            return;
        }
        movieRepository = MovieRepository.getInstance();
        mutableLiveData = movieRepository.getDetail(id, currLang, ApiUtils.API_KEY);
    }

    public void initRecommendation(int id, String prevLang, String currLang) {

        if (recommLiveData != null && (prevLang.isEmpty() || prevLang.equals(currLang))) {
            return;
        }
        movieRepository = MovieRepository.getInstance();
        recommLiveData = movieRepository.getRecommendations(id, currLang, ApiUtils.API_KEY);
    }

    public LiveData<List<MovieModel>> getFavoriteMovie() {
        return mAllFavoriteMovie;
    }

    public void addToFavorite(MovieModel movie) {
        movieRepository.addToFavorite(movie);
    }

    public void deleteFromFavorite(MovieModel movie) {
        movieRepository.deleteFromFavorite(movie);
    }

}
