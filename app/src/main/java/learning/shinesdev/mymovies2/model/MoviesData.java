package learning.shinesdev.mymovies2.model;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import learning.shinesdev.mymovies2.R;
import learning.shinesdev.mymovies2.utils.GlobVar;


@SuppressWarnings("ResultOfMethodCallIgnored")
public class MoviesData {
    private final Context context;


    public MoviesData(Context context) {
        this.context = context;
    }

    public ArrayList<Movie> getArrListData(){
        ArrayList<Movie> arrList = new ArrayList<>();
        String [] data_title = context.getResources().getStringArray(R.array.movie_data_title);
        String [] data_year = context.getResources().getStringArray(R.array.movie_data_releasedate);
        String [] data_group = context.getResources().getStringArray(R.array.movie_data_group);
        String [] data_duration = context.getResources().getStringArray(R.array.movie_data_duration);
        String [] data_genre = context.getResources().getStringArray(R.array.movie_data_genre);
        String [] data_rating = context.getResources().getStringArray(R.array.movie_data_rating);
        String [] data_metascore = context.getResources().getStringArray(R.array.movie_data_metascore);
        String [] data_overview = context.getResources().getStringArray(R.array.movie_data_overview);
        String [] data_director = context.getResources().getStringArray(R.array.movie_data_director);
        String [] data_stars = context.getResources().getStringArray(R.array.movie_data_stars);
        String [] data_votes = context.getResources().getStringArray(R.array.movie_data_votes);
        String [] data_gross = context.getResources().getStringArray(R.array.movie_data_gross);
        String [] data_image = context.getResources().getStringArray(R.array.movie_data_poster);

        for(int i = 0 ; i < data_title.length; i++){
            Movie movie = new Movie();
            movie.setTitle(data_title[i]);
            movie.setYear(data_year[i]);
            movie.setGroup(data_group[i]);
            movie.setDuration(data_duration[i]);
            movie.setGenre(data_genre[i]);
            movie.setRating(data_rating[i]);
            movie.setMetascore(data_metascore[i]);
            movie.setSynopsis(data_overview[i]);
            movie.setDirector(data_director[i]);
            movie.setStars(data_stars[i]);
            movie.setVotes(data_votes[i]);
            movie.setGross(data_gross[i]);
            movie.setImage(data_image[i]);

            arrList.add(movie);
        }
        return arrList;
    }

    public int getCount(){
        String [] data_title = context.getResources().getStringArray(R.array.movie_data_title);
        return data_title.length;
    }
}
