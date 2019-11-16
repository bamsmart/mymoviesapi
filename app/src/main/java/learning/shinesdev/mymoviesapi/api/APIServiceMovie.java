package learning.shinesdev.mymoviesapi.api;


import learning.shinesdev.mymoviesapi.model.Movie;
import learning.shinesdev.mymoviesapi.model.MovieCredits;
import learning.shinesdev.mymoviesapi.model.MovieModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface APIServiceMovie {
    @GET("3/movie/popular")
    Call<Movie> getPopular(
            @Query("language") String lang,
            @Query("api_key") String key
    );

    @GET("3/movie/{movie_id}/recommendations")
    Call<Movie> getRecommendations(
            @Path("movie_id") int id,
            @Query("language") String lang,
            @Query("api_key") String key
    );

    @GET("3/movie/{movie_id}/credits")
    Call<MovieCredits> getCredits(
            @Path("movie_id") int id,
            @Query("api_key") String key
    );

    @GET("3/movie/top_rated")
    Call<Movie> getTopRated(
            @Query("api_key") String key
    );

    @GET("3/movie/latest")
    Call<Movie> getLatest(
            @Query("api_key") String key
    );

    @GET("3/movie/{movie_id}")
    Call<MovieModel> getDetails(
            @Path("movie_id") int id,
            @Query("language") String lang,
            @Query("api_key") String key
    );
}
