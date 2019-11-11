package learning.shinesdev.mymovies2;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import learning.shinesdev.mymovies2.adapter.ListDetailTVShowAdapter;
import learning.shinesdev.mymovies2.model.TV;
import learning.shinesdev.mymovies2.model.TVData;
import learning.shinesdev.mymovies2.utils.GlobVar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailTVShowFragment extends Fragment {
    private TextView txtTitle;
    private TextView txtYear;
    private TextView txtSynopnsis;
    private TextView txtStars;
    private TextView txtVotes;
    private ImageView imgThumb;

    private RecyclerView recyclerView;
    private ArrayList<TV> imageModelArrayList;
    private ListDetailTVShowAdapter adapter;

    private String imgList [];
    private String titleList [];
    private int start,end,max;

    public DetailTVShowFragment() {
        // Required empty public constructo
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtTitle = view.findViewById(R.id.txt_tv_title);
        txtYear = view.findViewById(R.id.txt_tv_year);
        txtSynopnsis = view.findViewById(R.id.txt_tv_synopsis);
        txtStars = view.findViewById(R.id.txt_tv_stars);
        txtVotes = view.findViewById(R.id.txt_tv_votes);
        imgThumb = view.findViewById(R.id.img_tv_thumb);
        recyclerView = view.findViewById(R.id.rv_detail_tv);

        TV tvshow = getActivity().getIntent().getParcelableExtra(GlobVar.EX_TV);
        int idx = getActivity().getIntent().getIntExtra(GlobVar.EX_IDX,0);

        txtTitle.setText(tvshow.getTitle());
        txtYear.setText(tvshow.getYear());
        txtSynopnsis.setText(tvshow.getSynopsis());
        txtStars.setText(tvshow.getStars());
        txtVotes.setText(tvshow.getVotes());
        imgThumb.setImageResource(getResources().getIdentifier(tvshow.getImage(), "drawable", getActivity().getPackageName()));

        TVData data = new TVData(getContext());

        imageModelArrayList = data.getSumListData(idx);
        adapter = new ListDetailTVShowAdapter(getContext(), imageModelArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter.setOnItemClickCallback(new ListDetailTVShowAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(TV data) {
                DetailTVShowFragment fragment = new DetailTVShowFragment();
                Bundle mBundle = new Bundle();
                mBundle.putParcelable(GlobVar.EX_TV,data);
                fragment.setArguments(mBundle);

                FragmentManager mFragmentManager = getFragmentManager();
                if (mFragmentManager != null) {
                    mFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_container, fragment, DetailTVShowFragment.class.getSimpleName())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

        if(getArguments() != null){
            TV _data =  getArguments().getParcelable(GlobVar.EX_TV);
            txtTitle.setText(_data.getTitle());
            txtYear.setText(_data.getYear());
            txtSynopnsis.setText(_data.getSynopsis());
            txtStars.setText(_data.getStars());
            txtVotes.setText(_data.getVotes());
            imgThumb.setImageResource(getResources().getIdentifier(_data.getImage(), "drawable", getActivity().getPackageName()));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_tvshow, container, false);
    }
}
