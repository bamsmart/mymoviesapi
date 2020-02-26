package learning.shinesdev.mymoviesapi.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import learning.shinesdev.mymoviesapi.data.api.ApiUtils;
import learning.shinesdev.mymoviesapi.data.api.response.MovieApiResponse;
import learning.shinesdev.mymoviesapi.model.MovieEntity;
import learning.shinesdev.mymoviesapi.repository.MovieRepository;

public class MovieViewModel extends ViewModel {
    
    private MovieRepository movieRepository;
    private MutableLiveData<MovieApiResponse> movieListLiveData;
    private MutableLiveData<MovieApiResponse> movieRecommListLiveData;
    private MutableLiveData<MovieEntity> movieLiveData;
    private LiveData<List<MovieEntity>> mAllFavoriteMovie;

    public void init(@NonNull Application application) {
        movieRepository = new MovieRepository(application);
        mAllFavoriteMovie = movieRepository.getFavorite();
    }

    public LiveData<MovieApiResponse> getListMovie() {
        return movieListLiveData;
    }

    public LiveData<MovieEntity> getMovie() {
        return movieLiveData;
    }

    public LiveData<MovieApiResponse> getRecommMovie() {
        return movieRecommListLiveData;
    }

    public void init(String language) {
        if (movieListLiveData != null) {
            return;
        }
        movieRepository = MovieRepository.getInstance();
        movieListLiveData = movieRepository.getPopular(language, ApiUtils.API_KEY);
    }

    public void getDetail(int id, String prevLang, String currLang) {
        if (movieLiveData != null && (prevLang.isEmpty() || prevLang.equals(currLang))) {
            return;
        }
        movieRepository = MovieRepository.getInstance();
        movieLiveData = movieRepository.getDetail(id, currLang, ApiUtils.API_KEY);
    }

    public void initRecommendation(int id, String prevLang, String currLang) {

        if (movieRecommListLiveData != null && (prevLang.isEmpty() || prevLang.equals(currLang))) {
            return;
        }
        movieRepository = MovieRepository.getInstance();
        movieRecommListLiveData = movieRepository.getRecommendations(id, currLang, ApiUtils.API_KEY);
    }

    public LiveData<List<MovieEntity>> getFavoriteMovie() {
        return mAllFavoriteMovie;
    }

    public void addToFavorite(MovieEntity movie) {
        movieRepository.addToFavorite(movie);
    }

    public void deleteFromFavorite(MovieEntity movie) {
        movieRepository.deleteFromFavorite(movie);
    }

}
