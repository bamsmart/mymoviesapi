package learning.shinesdev.mymoviesapi;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.Objects;

import learning.shinesdev.mymoviesapi.R;

public class DetailMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

       Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(R.string.lbl_header_movie_detail);

        FragmentManager fragmentManager = getSupportFragmentManager();
        DetailMovieFragment detailMovieFragment = new DetailMovieFragment();
        Fragment fragment  = fragmentManager.findFragmentByTag(DetailMovieFragment.class.getSimpleName());
        if(!(fragment instanceof DetailMovieFragment)){
            fragmentManager
                    .beginTransaction()
                    .add(R.id.frame_container_movie, detailMovieFragment, DetailMovieFragment.class.getSimpleName())
                    .commit();
        }

    }
}
