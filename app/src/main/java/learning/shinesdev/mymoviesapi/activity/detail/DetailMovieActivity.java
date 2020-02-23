package learning.shinesdev.mymoviesapi.activity.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.Objects;

import learning.shinesdev.mymoviesapi.R;
import learning.shinesdev.mymoviesapi.activity.home.MainActivity;

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            Intent intent = new Intent(DetailMovieActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
