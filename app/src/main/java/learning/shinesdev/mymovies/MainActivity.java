package learning.shinesdev.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import learning.shinesdev.mymovies.adapter.ListViewMoviesAdapter;
import learning.shinesdev.mymovies.model.Movie;
import learning.shinesdev.mymovies.model.MoviesData;
import learning.shinesdev.mymovies.utils.GlobalVariable;

public class MainActivity extends AppCompatActivity {
    private GlobalVariable var;
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Movie movie = new Movie(movieArrayList.get(i).getTitle(), movieArrayList.get(i).getSynopsis());

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(var.EX_MOVIE, movie);
                startActivity(intent);
               // Toast.makeText(MainActivity.this, movieArrayList.get(i).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });


    }


}
