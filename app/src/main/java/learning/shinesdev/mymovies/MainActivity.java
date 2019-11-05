package learning.shinesdev.mymovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import learning.shinesdev.mymovies.adapter.ListViewMoviesAdapter;
import learning.shinesdev.mymovies.model.Movie;
import learning.shinesdev.mymovies.model.MoviesData;
import learning.shinesdev.mymovies.utils.GlobalVariable;

public class MainActivity extends AppCompatActivity {
    private GlobalVariable var;
    private ListView listView;
    private ListViewMoviesAdapter adapter;
    private ArrayList<Movie> movieArrList;
    private MoviesData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        var = new GlobalVariable();

        listView = findViewById(R.id.list_movies);
        adapter = new ListViewMoviesAdapter(getApplicationContext());
        data = new MoviesData(getApplicationContext());
        listView.setAdapter(adapter);

        movieArrList = new ArrayList<>();
        try {
            movieArrList.addAll(data.getListData());
            adapter.setMovie(movieArrList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final int len = (movieArrList.size() - 1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Movie movie = new Movie();
                movie.setTitle(movieArrList.get(i).getTitle());
                movie.setYear(movieArrList.get(i).getYear());
                movie.setDirector(movieArrList.get(i).getDirector());
                movie.setStars(movieArrList.get(i).getStars());
                movie.setSynopsis(movieArrList.get(i).getSynopsis());
                movie.setVotes(movieArrList.get(i).getVotes());
                movie.setImage(movieArrList.get(i).getImage());

                if (i == (len - 2)) {
                    movie.setNextTitle1(movieArrList.get(i + 1).getTitle());
                    movie.setNextTitle2(movieArrList.get(i + 2).getTitle());
                    movie.setNextTitle3(movieArrList.get(0).getTitle());

                    movie.setNextImage1(movieArrList.get(i + 1).getImage());
                    movie.setNextImage2(movieArrList.get(i + 1).getImage());
                    movie.setNextImage3(movieArrList.get(0).getImage());
                } else if (i == (len - 1)) {
                    movie.setNextTitle1(movieArrList.get(i + 1).getTitle());
                    movie.setNextTitle2(movieArrList.get(0).getTitle());
                    movie.setNextTitle3(movieArrList.get(1).getTitle());
                    movie.setNextImage1(movieArrList.get(i + 1).getImage());
                    movie.setNextImage2(movieArrList.get(0).getImage());
                    movie.setNextImage3(movieArrList.get(1).getImage());
                } else if (i == (len)) {
                    movie.setNextTitle1(movieArrList.get(0).getTitle());
                    movie.setNextTitle2(movieArrList.get(1).getTitle());
                    movie.setNextTitle3(movieArrList.get(2).getTitle());
                    movie.setNextImage1(movieArrList.get(0).getImage());
                    movie.setNextImage2(movieArrList.get(1).getImage());
                    movie.setNextImage3(movieArrList.get(2).getImage());
                } else {
                    movie.setNextTitle1(movieArrList.get(i + 1).getTitle());
                    movie.setNextTitle2(movieArrList.get(i + 2).getTitle());
                    movie.setNextTitle3(movieArrList.get(i + 3).getTitle());

                    movie.setNextImage1(movieArrList.get(i + 1).getImage());
                    movie.setNextImage2(movieArrList.get(i + 2).getImage());
                    movie.setNextImage3(movieArrList.get(1 + 3).getImage());
                }

                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra(var.EX_MOVIE, movie);
                startActivity(intent);
            }
        });
    }
}
