package learning.shinesdev.mymovies2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import learning.shinesdev.mymovies2.adapter.ListDetailTVShowAdapter;
import learning.shinesdev.mymovies2.adapter.ListMovieAdapter;
import learning.shinesdev.mymovies2.model.Movie;
import learning.shinesdev.mymovies2.model.TV;
import learning.shinesdev.mymovies2.model.TVData;
import learning.shinesdev.mymovies2.utils.GlobVar;

public class DetailTVActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(R.string.lbl_header_tv_detail);

        FragmentManager fragmentManager = getSupportFragmentManager();
        DetailTVShowFragment detailTVShowFragment = new DetailTVShowFragment();
        Fragment fragment  = fragmentManager.findFragmentByTag(DetailTVShowFragment.class.getSimpleName());
        if(!(fragment instanceof DetailTVShowFragment)){
            fragmentManager
                    .beginTransaction()
                    .add(R.id.frame_container, detailTVShowFragment, DetailTVShowFragment.class.getSimpleName())
                    .commit();
        }
    }
}
