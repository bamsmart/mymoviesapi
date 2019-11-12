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

import learning.shinesdev.mymovies2.adapter.ListRandTVShowAdapter;
import learning.shinesdev.mymovies2.model.TVShowModel;
import learning.shinesdev.mymovies2.model.TVShowData;
import learning.shinesdev.mymovies2.utils.GlobVar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailTVShowFragment extends Fragment {

    public DetailTVShowFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView txtTitle = view.findViewById(R.id.txt_tv_title);
        TextView txtYear = view.findViewById(R.id.txt_tv_year);
        TextView txtSynopnsis = view.findViewById(R.id.txt_tv_synopsis);
        TextView txtStars = view.findViewById(R.id.txt_tv_stars);
        TextView txtVotes = view.findViewById(R.id.txt_tv_votes);
        ImageView imgThumb = view.findViewById(R.id.img_tv_thumb);
        RecyclerView randTVShowRecyclerView = view.findViewById(R.id.rv_detail_tv);

        TVShowModel tvShowModel = Objects.requireNonNull(getActivity()).getIntent().getParcelableExtra(GlobVar.EX_TV);
        int idx = getActivity().getIntent().getIntExtra(GlobVar.EX_IDX, 0);

        txtTitle.setText(tvShowModel.getTitle());
        txtYear.setText(tvShowModel.getYear());
        txtSynopnsis.setText(tvShowModel.getSynopsis());
        txtStars.setText(tvShowModel.getStars());
        txtVotes.setText(tvShowModel.getVotes());
        imgThumb.setImageResource(getResources().getIdentifier(tvShowModel.getImage(), "drawable", getActivity().getPackageName()));

        TVShowData tvShowData = new TVShowData(getContext());
        ArrayList<TVShowModel> randTVShowArrList = tvShowData.getRandListData(idx);
        ListRandTVShowAdapter randTVShowAdapter = new ListRandTVShowAdapter(getContext(), randTVShowArrList);
        randTVShowRecyclerView.setAdapter(randTVShowAdapter);
        randTVShowRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        randTVShowAdapter.setOnItemClickCallback(new ListRandTVShowAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(TVShowModel data) {
                DetailTVShowFragment fragment = new DetailTVShowFragment();
                Bundle mBundle = new Bundle();
                mBundle.putParcelable(GlobVar.EX_TV,data);
                fragment.setArguments(mBundle);

                FragmentManager mFragmentManager = getFragmentManager();
                if (mFragmentManager != null) {
                    mFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_container_tvshow, fragment, DetailTVShowFragment.class.getSimpleName())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

        if(getArguments() != null){
            tvShowModel =  getArguments().getParcelable(GlobVar.EX_TV);
            txtTitle.setText(Objects.requireNonNull(tvShowModel).getTitle());
            txtYear.setText(tvShowModel.getYear());
            txtSynopnsis.setText(tvShowModel.getSynopsis());
            txtStars.setText(tvShowModel.getStars());
            txtVotes.setText(tvShowModel.getVotes());
            imgThumb.setImageResource(getResources().getIdentifier(tvShowModel.getImage(), "drawable", getActivity().getPackageName()));
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_tvshow, container, false);
    }
}
