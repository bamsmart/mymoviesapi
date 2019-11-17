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

import learning.shinesdev.mymoviesapi.adapter.ListRandTVShowAdapter;
import learning.shinesdev.mymoviesapi.model.TVShow;
import learning.shinesdev.mymoviesapi.model.TVShowCredits;
import learning.shinesdev.mymoviesapi.model.TVShowModel;
import learning.shinesdev.mymoviesapi.utils.GlobVar;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressWarnings({"ALL", "WeakerAccess"})
public class DetailTVShowFragment extends Fragment {
    private TVShow tvShow;
    private TVShowModel tvShowModel;
    private TVShowCredits tvShowCredits;
    private RecyclerView recommTVShowRecyclerView;
    private ListRandTVShowAdapter recommTVShowAdapter;
    private ArrayList<TVShowModel> recommTVShowArrList = new ArrayList<>();


    TextView txtTitle;
    TextView txtYear;
    TextView txtSynopnsis;
    TextView txtStars;
    TextView txtVotes;
    ImageView imgThumb;

    public DetailTVShowFragment() {
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

        txtTitle = view.findViewById(R.id.txt_tv_title);
        txtYear = view.findViewById(R.id.txt_tv_year);
        txtSynopnsis = view.findViewById(R.id.txt_tv_synopsis);
        txtStars = view.findViewById(R.id.txt_tv_stars);
        txtVotes = view.findViewById(R.id.txt_tv_votes);
        imgThumb = view.findViewById(R.id.img_tv_thumb);
        recommTVShowRecyclerView = view.findViewById(R.id.rv_detail_tv);

        final TVShowModel EX = Objects.requireNonNull(getActivity()).getIntent().getParcelableExtra(GlobVar.EX_TV);
        tvShowModel = ViewModelProviders.of(getActivity()).get(TVShowModel.class);

        tvShowModel.init((EX.getId()));
        tvShowModel.getTVShowRepository().observe(getActivity(), response -> {
            progressDialog.dismiss();
            setupUI(response);
        });
        tvShow = ViewModelProviders.of(getActivity()).get(TVShow.class);
        tvShow.initRecommendation(EX.getId());
        tvShow.getTVShowRepository().observe(getActivity(), response -> {
            List<TVShowModel> data = response.getTVShowList();
            recommTVShowArrList.addAll(data);
            setupRecommRecyclerView();
        });

        tvShowCredits = ViewModelProviders.of(getActivity()).get(TVShowCredits.class);
        tvShowCredits.init(EX.getId());
        tvShowCredits.getTVShowRepository().observe(getActivity(), response -> {
            List<TVShowCredits> credits = response.getCreditsList();
            String strCredits = "";
            if (!credits.isEmpty()) {
                for (int i = 0; i < credits.size(); i++) {
                    strCredits += credits.get(i).getName();
                    if (i < (credits.size() - 1)) {
                        strCredits += ", ";
                    }
                }
            }
            txtStars.setText(strCredits);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_tvshow, container, false);
    }

    public void setupUI(TVShowModel data) {
        txtTitle.setText(data.getName());
        txtYear.setText(data.getFirst_air_date());
        txtSynopnsis.setText(data.getOverview());
        txtVotes.setText(String.valueOf(data.getVote_count()));
        String img_url = "https://image.tmdb.org/t/p/w600_and_h900_bestv2" + data.getPoster_path();

        Glide.with(getContext()).load(img_url)
                .centerCrop()
                .into(imgThumb);
    }

    private void setupRecommRecyclerView() {
        if (recommTVShowAdapter == null) {
            recommTVShowAdapter = new ListRandTVShowAdapter(getContext(), recommTVShowArrList);
            recommTVShowRecyclerView.setAdapter(recommTVShowAdapter);
            recommTVShowRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            recommTVShowRecyclerView.setItemAnimator(new DefaultItemAnimator());
            recommTVShowRecyclerView.setNestedScrollingEnabled(true);
        } else {
            recommTVShowAdapter.notifyDataSetChanged();
        }
        recommTVShowAdapter.setOnItemClickCallback(new ListRandTVShowAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(TVShowModel data) {
                Intent intent = new Intent(getContext(), DetailTVShowActivity.class);
                intent.putExtra(GlobVar.EX_TV, data);
                startActivity(intent);
            }
        });
    }
}
