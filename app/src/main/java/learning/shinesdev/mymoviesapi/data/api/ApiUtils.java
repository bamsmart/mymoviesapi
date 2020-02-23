package learning.shinesdev.mymoviesapi.data.api;

public class ApiUtils {
    public static final String BASE_URL = "https://api.themoviedb.org";
    public static final String IMG_URL = "https://image.tmdb.org/t/p/w600_and_h900_bestv2";
    public static final String API_KEY ="0aba4f721d8337287b06a15824fe55c4";

    public static APIServiceMovie getAPIServiceMovie() {
       return RetrofitClient.getClient(BASE_URL).create(APIServiceMovie.class);
    }


}
