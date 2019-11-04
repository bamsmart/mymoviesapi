package learning.shinesdev.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import learning.shinesdev.mymovies.adapter.ListViewMoviesAdapter;
import learning.shinesdev.mymovies.model.Movie;
import learning.shinesdev.mymovies.model.MoviesData;

public class MainActivity extends AppCompatActivity {
private ListView listView;
private ListViewMoviesAdapter adapter;
private ArrayList<Movie> movieArrayList;
private MoviesData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_movies);
        adapter = new ListViewMoviesAdapter(this);
        data = new MoviesData(getApplicationContext());
        listView.setAdapter(adapter);

        movieArrayList = new ArrayList<>();
        try {
            movieArrayList.addAll(data.getListData());

            adapter.setMovie(movieArrayList);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
