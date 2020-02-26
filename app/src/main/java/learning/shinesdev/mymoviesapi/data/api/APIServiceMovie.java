package learning.shinesdev.mymoviesapi.data.api;


import learning.shinesdev.mymoviesapi.data.api.response.MovieApiResponse;
import learning.shinesdev.mymoviesapi.model.MovieEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface APIServiceMovie {

    @GET("3/movie/popular")
    Call<MovieApiResponse> getPopular(
            @Query("language") String lang,
            @Query("api_key") String key
    );

    @GET("3/movie/{movie_id}/recommendations")
    Call<MovieApiResponse> getRecommendations(
            @Path("movie_id") int id,
            @Query("language") String lang,
            @Query("api_key") String key
    );

    @GET("3/movie/{movie_id}")
    Call<MovieEntity> getDetails(
            @Path("movie_id") int id,
            @Query("language") String lang,
            @Query("api_key") String key
    );

}
