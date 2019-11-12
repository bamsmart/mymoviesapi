package learning.shinesdev.mymovies2;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

import learning.shinesdev.mymovies2.adapter.ListRandMovieAdapter;
import learning.shinesdev.mymovies2.model.MovieModel;
import learning.shinesdev.mymovies2.model.MoviesData;
import learning.shinesdev.mymovies2.utils.GlobVar;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailMovieFragment extends Fragment {

    public DetailMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView txtTitle = view.findViewById(R.id.txt_movie_title);
        TextView txtYear = view.findViewById(R.id.txt_movie_year);
        TextView txtSynopnsis = view.findViewById(R.id.txt_movie_synopsis);
        @SuppressWarnings("unused") TextView txtDirector = view.findViewById(R.id.txt_movie_director);
        TextView txtStars = view.findViewById(R.id.txt_movie_stars);
        TextView txtVotes = view.findViewById(R.id.txt_movie_votes);
        ImageView imgThumb = view.findViewById(R.id.img_movie_thumb);
        RecyclerView randMovieRecyclerView = view.findViewById(R.id.rv_detail_movie);

        MovieModel movieModel = Objects.requireNonNull(getActivity()).getIntent().getParcelableExtra(GlobVar.EX_MOVIE);
        int idx = getActivity().getIntent().getIntExtra(GlobVar.EX_IDX, 0);
        txtTitle.setText(movieModel.getTitle());
        txtYear.setText(movieModel.getYear());
        txtSynopnsis.setText(movieModel.getSynopsis());
        txtStars.setText(movieModel.getStars());
        txtVotes.setText(movieModel.getVotes());
        imgThumb.setImageResource(getResources().getIdentifier(movieModel.getImage(), "drawable", getActivity().getPackageName()));

        MoviesData movieData = new MoviesData(getContext());
        ArrayList<MovieModel> randMovieArrList = movieData.getRandListData(idx);
        ListRandMovieAdapter randMovieAdapter = new ListRandMovieAdapter(getContext(), randMovieArrList);
        randMovieRecyclerView.setAdapter(randMovieAdapter);
        randMovieRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        randMovieAdapter.setOnItemClickCallback(new ListRandMovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(MovieModel data,int idx) {
                DetailMovieFragment fragment = new DetailMovieFragment();
                Bundle mBundle = new Bundle();
                mBundle.putParcelable(GlobVar.EX_MOVIE,data);
                mBundle.putInt(GlobVar.EX_IDX,idx);
                fragment.setArguments(mBundle);

                FragmentManager mFragmentManager = getFragmentManager();
                if (mFragmentManager != null) {
                    mFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_container_movie, fragment, DetailMovieFragment.class.getSimpleName())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

        if(getArguments() != null){
            movieModel =  getArguments().getParcelable(GlobVar.EX_MOVIE);
            txtTitle.setText(Objects.requireNonNull(movieModel).getTitle());
            txtYear.setText(movieModel.getYear());
            txtSynopnsis.setText(movieModel.getSynopsis());
            txtStars.setText(movieModel.getStars());
            txtVotes.setText(movieModel.getVotes());
            imgThumb.setImageResource(getResources().getIdentifier(movieModel.getImage(), "drawable", getActivity().getPackageName()));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_movie, container, false);
    }
}
