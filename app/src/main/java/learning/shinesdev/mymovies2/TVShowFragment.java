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
import learning.shinesdev.mymovies2.adapter.ListTVShowAdapter;
import learning.shinesdev.mymovies2.model.Movie;
import learning.shinesdev.mymovies2.model.MoviesData;
import learning.shinesdev.mymovies2.model.TV;
import learning.shinesdev.mymovies2.model.TVData;
import learning.shinesdev.mymovies2.utils.GlobVar;


/**
 * A simple {@link Fragment} subclass.
 */
public class TVShowFragment extends Fragment {
    private RecyclerView rvTVShow;
    private final ArrayList<TV> list = new ArrayList<>();

    public TVShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTVShow = view.findViewById(R.id.rv_tvshow);
        rvTVShow.setHasFixedSize(true);

        TVData data = new TVData(getContext());
        list.addAll(data.getArrListData());
        showRecyclerList();
    }

    private void showRecyclerList(){
        rvTVShow.setLayoutManager(new LinearLayoutManager(getContext()));
        ListTVShowAdapter listAdapter = new ListTVShowAdapter(getContext(),list);
        rvTVShow.setAdapter(listAdapter);

        listAdapter.setOnItemClickCallback(new ListTVShowAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(TV data) {
                showSelectedTVShow(data);
            }
        });
    }

    private void showSelectedTVShow(TV tv) {
        Intent intent = new Intent(getContext(), DetailTVActivity.class);
        intent.putExtra(GlobVar.EX_TV, tv);
        startActivity(intent);
    }
}
