package learning.shinesdev.mymoviesapi.activity.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import learning.shinesdev.mymoviesapi.activity.detail.DetailMovieActivity;
import learning.shinesdev.mymoviesapi.R;
import learning.shinesdev.mymoviesapi.adapter.MovieAdapter;
import learning.shinesdev.mymoviesapi.data.api.response.MovieApiResponse;
import learning.shinesdev.mymoviesapi.viewmodel.MovieViewModel;
import learning.shinesdev.mymoviesapi.model.MovieEntity;
import learning.shinesdev.mymoviesapi.utils.ConnectionDetector;
import learning.shinesdev.mymoviesapi.utils.GlobVar;
import learning.shinesdev.mymoviesapi.utils.SessionManager;


public class MovieFragment extends Fragment {
    private RecyclerView rvMovies;
    private MovieAdapter listMovieAdapter;
    private final ArrayList<MovieEntity> arrListMovie = new ArrayList<>();
    private List<MovieEntity> data;
    private ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SessionManager session = new SessionManager(Objects.requireNonNull(getContext()));
        progressBar = view.findViewById(R.id.progress_movie);
        rvMovies = view.findViewById(R.id.rv_movies);
        rvMovies.setHasFixedSize(true);

        if (savedInstanceState != null) {
            progressBar.setVisibility(View.GONE);
            List<MovieEntity> exdata = savedInstanceState.getParcelableArrayList(GlobVar.EX_MOVIE);
            arrListMovie.addAll(Objects.requireNonNull(exdata));
            setupRecyclerView();
            session.setPrevLang(getResources().getString(R.string.language));
        }else {
            ConnectionDetector conn = new ConnectionDetector(getContext());
            if (conn.isConnectingToInternet()) {
                String prevLang = session.getPrevLang();
                String currLang = Objects.requireNonNull(getActivity()).getResources().getString(R.string.language);

                MovieViewModel movie = ViewModelProviders.of(this).get(MovieViewModel.class);

                movie.init("en-US");
                movie.getListMovie().observe(this, new Observer<MovieApiResponse>() {
                    @Override
                    public void onChanged(MovieApiResponse movie) {
                        progressBar.setVisibility(View.GONE);
                        data = movie.getResults();
                        arrListMovie.addAll(data);
                        setupRecyclerView();
                        session.setPrevLang(getResources().getString(R.string.language));
                    }
                });

            } else {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), R.string.lbl_connection_info, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    private void setupRecyclerView() {
        if (listMovieAdapter == null) {
            listMovieAdapter = new MovieAdapter(getContext(), arrListMovie);
            rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
            rvMovies.setAdapter(listMovieAdapter);
            rvMovies.setItemAnimator(new DefaultItemAnimator());
            rvMovies.setNestedScrollingEnabled(true);
        } else {
            listMovieAdapter.notifyDataSetChanged();
        }

        listMovieAdapter.setOnItemClickCallback(data -> {
            Intent intent = new Intent(getContext(), DetailMovieActivity.class);
            intent.putExtra(GlobVar.EX_MOVIE, data);
            startActivity(intent);


        });

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(GlobVar.EX_MOVIE,arrListMovie);
    }
}
