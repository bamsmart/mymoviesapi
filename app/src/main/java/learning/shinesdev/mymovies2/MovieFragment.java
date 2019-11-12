package learning.shinesdev.mymovies2;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import learning.shinesdev.mymovies2.adapter.ListMovieAdapter;
import learning.shinesdev.mymovies2.model.MovieModel;
import learning.shinesdev.mymovies2.model.MoviesData;
import learning.shinesdev.mymovies2.utils.GlobVar;

public class MovieFragment extends Fragment {

    private RecyclerView rvMovies;
    private final ArrayList<MovieModel> list = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovies = view.findViewById(R.id.rv_movies);
        rvMovies.setHasFixedSize(true);

        MoviesData data = new MoviesData(getContext());
       list.addAll(data.getArrListData());
        showRecyclerList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    private void showRecyclerList(){
        rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        ListMovieAdapter listMovieAdapter = new ListMovieAdapter(getContext(),list);
        rvMovies.setAdapter(listMovieAdapter);

        listMovieAdapter.setOnItemClickCallback(new ListMovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(MovieModel data, int idx) {
                showSelectedMovie(data,idx);
            }
        });
    }

    private void showSelectedMovie(MovieModel movie,int idx) {
        Intent intent = new Intent(getContext(), DetailMovieActivity.class);
        intent.putExtra(GlobVar.EX_MOVIE, movie);
        intent.putExtra(GlobVar.EX_IDX, idx);
        startActivity(intent);
    }
}
