package learning.shinesdev.mymovies2.model;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import learning.shinesdev.mymovies2.utils.GlobVar;


public class MoviesData {
    private final Context context;

    public MoviesData(Context context) {
        this.context = context;
    }

    public ArrayList<Movie> getListData() {
        ArrayList<Movie> list = new ArrayList<>();
        try {
            JSONObject jsonObj = new JSONObject(loadJSONFromAsset());
            JSONArray jsonArr = jsonObj.getJSONArray(GlobVar.EX_MOVIE_ROOT);

            for (int i = 0; i < jsonArr.length(); i++) {
                JSONObject obj = jsonArr.getJSONObject(i);

                Movie movie = new Movie();
                movie.setTitle(obj.getString(GlobVar.EX_MOVIE_TITLE));
                movie.setYear(obj.getString(GlobVar.EX_MOVIE_YEAR));
                movie.setGroup(obj.getString(GlobVar.EX_MOVIE_GROUP));
                movie.setDuration(obj.getString(GlobVar.EX_MOVIE_DURATION));
                movie.setGenre(obj.getString(GlobVar.EX_MOVIE_GENRE));
                movie.setRating(obj.getString(GlobVar.EX_MOVIE_RATING));
                movie.setMetascore(obj.getString(GlobVar.EX_MOVIE_METASCORE));
                movie.setSynopsis(obj.getString(GlobVar.EX_MOVIE_SYNOPSIS));
                movie.setDirector(obj.getString(GlobVar.EX_MOVIE_DIRECTOR));
                movie.setStars(obj.getString(GlobVar.EX_MOVIE_STARS));
                movie.setVotes(obj.getString(GlobVar.EX_MOVIE_VOTES));
                movie.setGross(obj.getString(GlobVar.EX_MOVIE_GROSS));
                movie.setImage(obj.getString(GlobVar.EX_MOVIE_THUMB));
                list.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }


    private String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = context.getAssets().open("movie.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
