package learning.shinesdev.mymoviesapi.api;

public class ApiUtils {

    private static final String BASE_URL = "https://api.themoviedb.org";
    public static final String API_KEY ="0aba4f721d8337287b06a15824fe55c4";

    private ApiUtils() {
    }
    public static APIServiceMovie getAPIServiceMovie() {
        return RetrofitClient.getClient(BASE_URL).create(APIServiceMovie.class);
    }
    public static APIServiceTVShow getAPIServiceTVShow() {
        return RetrofitClient.getClient(BASE_URL).create(APIServiceTVShow.class);
    }
}
