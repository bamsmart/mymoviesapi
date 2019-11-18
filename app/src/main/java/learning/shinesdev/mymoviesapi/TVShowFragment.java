package learning.shinesdev.mymoviesapi;


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
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import learning.shinesdev.mymoviesapi.adapter.ListTVShowAdapter;
import learning.shinesdev.mymoviesapi.model.TVShow;
import learning.shinesdev.mymoviesapi.model.TVShowModel;
import learning.shinesdev.mymoviesapi.utils.ConnectionDetector;
import learning.shinesdev.mymoviesapi.utils.GlobVar;
import learning.shinesdev.mymoviesapi.utils.SessionManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class TVShowFragment extends Fragment {
    private RecyclerView rvTVShow;
    private ListTVShowAdapter listTVShowAdapter;
    private final ArrayList<TVShowModel> tvShowList = new ArrayList<>();
    private ProgressBar progressBar;

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
        SessionManager session = new SessionManager(Objects.requireNonNull(getContext()));

        rvTVShow = view.findViewById(R.id.rv_tvshow);
        rvTVShow.setHasFixedSize(true);
        progressBar = view.findViewById(R.id.progress_tvshow);

        if (savedInstanceState != null) {
            progressBar.setVisibility(View.GONE);
            List<TVShowModel> exdata = savedInstanceState.getParcelableArrayList(GlobVar.EX_TV);
            tvShowList.addAll(Objects.requireNonNull(exdata));
            showRecyclerList();
            session.setPrevLang(getResources().getString(R.string.language));
        }else {
            ConnectionDetector conn = new ConnectionDetector(getContext());
            if (conn.isConnectingToInternet()) {
                String prevLang = session.getPrevLang();
                String currLang = Objects.requireNonNull(getActivity()).getResources().getString(R.string.language);

                TVShow tvshow = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(TVShow.class);
                tvshow.init(prevLang, currLang);
                tvshow.getTVShowRepository().observe(getActivity(), response -> {
                    progressBar.setVisibility(View.GONE);
                    List<TVShowModel> data = response.getTVShowList();
                    tvShowList.addAll(data);
                    showRecyclerList();
                    session.setPrevLang(getResources().getString(R.string.language));
                });
            } else {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), R.string.koneksi, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void showRecyclerList() {
        if (listTVShowAdapter == null) {
            listTVShowAdapter = new ListTVShowAdapter(getContext(), tvShowList);
            rvTVShow.setLayoutManager(new LinearLayoutManager(getContext()));
            rvTVShow.setAdapter(listTVShowAdapter);
            rvTVShow.setItemAnimator(new DefaultItemAnimator());
            rvTVShow.setNestedScrollingEnabled(true);

        }else{
            listTVShowAdapter.notifyDataSetChanged();
        }
        listTVShowAdapter.setOnItemClickCallback(data -> {
            Intent intent = new Intent(getContext(), DetailTVShowActivity.class);
            intent.putExtra(GlobVar.EX_TV, data);
            startActivity(intent);
        });
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(GlobVar.EX_TV,tvShowList);
    }
}
