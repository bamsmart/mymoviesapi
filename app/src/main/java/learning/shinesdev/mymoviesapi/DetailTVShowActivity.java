package learning.shinesdev.mymoviesapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import java.util.Objects;

import learning.shinesdev.mymoviesapi.R;

public class DetailTVShowActivity extends AppCompatActivity {

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
                    .add(R.id.frame_container_tvshow, detailTVShowFragment, DetailTVShowFragment.class.getSimpleName())
                    .commit();
        }
    }
}
