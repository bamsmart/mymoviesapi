package learning.shinesdev.mymoviesapi.activity.home;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
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

import cn.pedant.SweetAlert.SweetAlertDialog;
import learning.shinesdev.mymoviesapi.R;
import learning.shinesdev.mymoviesapi.adapter.FavoriteAdapter;
import learning.shinesdev.mymoviesapi.model.MovieEntity;
import learning.shinesdev.mymoviesapi.utils.SessionManager;
import learning.shinesdev.mymoviesapi.viewmodel.MovieViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
@SuppressWarnings("ALL")
public class FavoriteFragment extends Fragment implements FavoriteAdapter.OnItemClickListener {

    private List<MovieEntity> mMovie = new ArrayList<>();

    private FavoriteAdapter listMovieAdapter;
    private ProgressBar progressBar;
    private MovieViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SessionManager session = new SessionManager(Objects.requireNonNull(getContext()));
        progressBar = view.findViewById(R.id.progress_movie);

        RecyclerView rvMovies = view.findViewById(R.id.rv_movies);
        rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMovies.setHasFixedSize(true);

        listMovieAdapter = new FavoriteAdapter(getContext());
        rvMovies.setAdapter(listMovieAdapter);

        listMovieAdapter.setOnItemClickListener(this);

        viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        viewModel.init(Objects.requireNonNull(getActivity()).getApplication());
        viewModel.getFavoriteMovie().observe(this, movie -> {
            progressBar.setVisibility(View.GONE);
            listMovieAdapter.setData(movie);
            mMovie = movie;
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_movie, container, false);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putParcelableArrayList(GlobVar.EX_TV, arrListMovie);
    }

    @Override
    public void onItemClick(int position) {
        new SweetAlertDialog(Objects.requireNonNull(getContext()), SweetAlertDialog.WARNING_TYPE)
                .setTitleText(Objects.requireNonNull(getActivity()).getResources().getString(R.string.lbl_delete_confirm))
                .setContentText(getActivity().getResources().getString(R.string.lbl_delete_desc))
                .setConfirmText(getActivity().getResources().getString(R.string.lbl_confirm_yes))
                .setConfirmClickListener(sDialog -> {
                    sDialog.dismissWithAnimation();

                    viewModel.init(getActivity().getApplication());
                    viewModel.deleteFromFavorite(mMovie.get(position));

                    Toast.makeText(getContext(), getActivity().getResources().getString(R.string.lbl_movie_gross) + mMovie.get(position).getTitle() + " Deleted", Toast.LENGTH_LONG).show();
                })
                .setCancelButton(getResources().getString(R.string.dialog_cancel), SweetAlertDialog::dismissWithAnimation)
                .show();
    }
}
