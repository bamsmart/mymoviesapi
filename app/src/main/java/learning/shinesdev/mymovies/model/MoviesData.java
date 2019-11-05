package learning.shinesdev.mymovies.model;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import learning.shinesdev.mymovies.utils.GlobalVariable;


public class MoviesData {
    private GlobalVariable var;
    private Context context;

    public MoviesData(Context context) {
        this.context = context;
    }

    public ArrayList<Movie> getListData() {
        ArrayList<Movie> list = new ArrayList<>();
        var = new GlobalVariable();
        try {
            JSONObject jsonObj = new JSONObject(loadJSONFromAsset());
            JSONArray jsonArr = jsonObj.getJSONArray(var.EX_MOVIE_ROOT);

            for (int i = 0; i < jsonArr.length(); i++) {
                JSONObject obj = jsonArr.getJSONObject(i);

                Movie movie = new Movie();
                movie.setTitle(obj.getString(var.EX_MOVIE_TITLE));
                movie.setYear(obj.getString(var.EX_MOVIE_YEAR));
                movie.setGroup(obj.getString(var.EX_MOVIE_GROUP));
                movie.setDuration(obj.getString(var.EX_MOVIE_DURATION));
                movie.setGenre(obj.getString(var.EX_MOVIE_GENRE));
                movie.setRating(obj.getString(var.EX_MOVIE_RATING));
                movie.setMetascore(obj.getString(var.EX_MOVIE_METASCORE));
                movie.setSynopsis(obj.getString(var.EX_MOVIE_SYNOPSIS));
                movie.setDirector(obj.getString(var.EX_MOVIE_DIRECTOR));
                movie.setStars(obj.getString(var.EX_MOVIE_STARS));
                movie.setVotes(obj.getString(var.EX_MOVIE_VOTES));
                movie.setGross(obj.getString(var.EX_MOVIE_GROSS));
                movie.setImage(obj.getString(var.EX_MOVIE_THUMB));

                list.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }


    public String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = context.getAssets().open("movie.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
