package learning.shinesdev.mymoviesapi;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import learning.shinesdev.mymoviesapi.R;
import learning.shinesdev.mymoviesapi.adapter.ListRandMovieAdapter;
import learning.shinesdev.mymoviesapi.model.Movie;
import learning.shinesdev.mymoviesapi.model.MovieCredits;
import learning.shinesdev.mymoviesapi.model.MovieModel;
import learning.shinesdev.mymoviesapi.utils.GlobVar;


/**
 * A simple {@link Fragment} subclass.
 */
@SuppressWarnings("ALL")
public class DetailMovieFragment extends Fragment {
    private Movie movieBunddle;
    private MovieModel movie;
    private MovieCredits movieCredits;
    TextView txtTitle;
    TextView txtYear;
    TextView txtSynopnsis;
    TextView txtDirector;
    TextView txtStars;
    TextView txtVotes;
    TextView txtGross;
    ImageView imgThumb;
    RecyclerView recommMovieRecyclerView;
    ListRandMovieAdapter recommMovieAdapter;
    private ArrayList<MovieModel> recommMovieArrList = new ArrayList<>();

    public DetailMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMax(100);
        progressDialog.setMessage("Loading....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        txtTitle = view.findViewById(R.id.txt_movie_title);
        txtYear = view.findViewById(R.id.txt_movie_year);
        txtSynopnsis = view.findViewById(R.id.txt_movie_synopsis);
        txtStars = view.findViewById(R.id.txt_movie_stars);
        txtVotes = view.findViewById(R.id.txt_movie_votes);
        txtGross = view.findViewById(R.id.txt_movie_gross);
        imgThumb = view.findViewById(R.id.img_movie_thumb);
        recommMovieRecyclerView = view.findViewById(R.id.rv_detail_movie);

        MovieModel extraData = Objects.requireNonNull(getActivity()).getIntent().getParcelableExtra(GlobVar.EX_MOVIE);

        movie = ViewModelProviders.of(getActivity()).get(MovieModel.class);
        movie.init(extraData.getId());
        movie.getMovieRepository().observe(getActivity(), response -> {
            progressDialog.dismiss();
            setupUI(response);
        });

        movieBunddle = ViewModelProviders.of(getActivity()).get(Movie.class);
        movieBunddle.initRecommendation(extraData.getId());
        movieBunddle.getMovieRepository().observe(getActivity(), response -> {
            List<MovieModel> data = response.getMovieList();
            recommMovieArrList.addAll(data);
            setupRecommRecyclerView();
        });

        movieCredits = ViewModelProviders.of(getActivity()).get(MovieCredits.class);
        movieCredits.init(extraData.getId());
        movieCredits.getMovieRepository().observe(getActivity(),response -> {
            List<MovieCredits> credits = response.getCreditsList();
            String strCredits = "";
            if(!credits.isEmpty()){
            for (int i = 0; i < credits.size(); i++){
                strCredits += credits.get(i).getName();
                if(i < (credits.size() -1)){
                    strCredits +=", ";
                }
            }
            }
            txtStars.setText(strCredits);
        });


        if (getArguments() != null) {
            extraData = getArguments().getParcelable(GlobVar.EX_MOVIE);
            txtTitle.setText(extraData.getTitle());
            txtYear.setText(extraData.getDate());
            txtSynopnsis.setText(extraData.getOverview());
            txtVotes.setText(String.valueOf(extraData.getVote()));
            txtGross.setText(String.valueOf(extraData.getRevenue()));
            String img_url = "https://image.tmdb.org/t/p/w600_and_h900_bestv2" + extraData.getImage();

            Glide.with(getContext()).load(img_url)
                    .centerCrop()
                    .into(imgThumb);
        }
    }

    private void setupRecommRecyclerView() {
        if (recommMovieAdapter == null) {
            recommMovieAdapter = new ListRandMovieAdapter(getContext(),recommMovieArrList);
            recommMovieRecyclerView.setAdapter(recommMovieAdapter);
            recommMovieRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            recommMovieRecyclerView.setItemAnimator(new DefaultItemAnimator());
            recommMovieRecyclerView.setNestedScrollingEnabled(true);
        } else {
            recommMovieAdapter.notifyDataSetChanged();
        }
        recommMovieAdapter.setOnItemClickCallback(new ListRandMovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(MovieModel data) {
                Intent intent = new Intent(getContext(), DetailMovieActivity.class);
                intent.putExtra(GlobVar.EX_MOVIE, data);
                startActivity(intent);
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_movie, container, false);
    }

    public void setupUI(MovieModel data) {
        txtTitle.setText(data.getTitle());
        txtYear.setText(data.getDate());
        txtSynopnsis.setText(data.getOverview());
        txtVotes.setText(String.valueOf(data.getVote()));
        txtGross.setText(String.valueOf(data.getRevenue()));
        String img_url = "https://image.tmdb.org/t/p/w600_and_h900_bestv2" + data.getImage();

        Glide.with(getContext()).load(img_url)
                .centerCrop()
                .into(imgThumb);
    }
}
