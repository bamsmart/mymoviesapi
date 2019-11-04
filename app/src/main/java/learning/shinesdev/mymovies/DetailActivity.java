package learning.shinesdev.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import learning.shinesdev.mymovies.model.Movie;
import learning.shinesdev.mymovies.utils.GlobalVariable;

public class DetailActivity extends AppCompatActivity {
    private GlobalVariable var;
    private TextView txtNumber;
    private TextView txtTitle;
    private TextView txtYear;
    private TextView txtAgeGroup;
    private TextView txtDuration;
    private TextView txtGenre;
    private TextView txtRate;
    private TextView txtMetascore;
    private TextView txtSynopnsis;
    private TextView txtDirector;
    private TextView txtStars;
    private TextView txtVotes;
    private TextView txtGross;
    private ImageView imgThumb;

    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtNumber = findViewById(R.id.txt_movie_number);
        txtTitle = findViewById(R.id.txt_movie_title);
        txtYear = findViewById(R.id.txt_movie_year);
        txtYear = findViewById(R.id.txt_movie_year);
        txtAgeGroup = findViewById(R.id.txt_movie_age_group);
        txtDuration = findViewById(R.id.txt_movie_minutes);
        txtRate = findViewById(R.id.txt_movie_rating);
        txtMetascore = findViewById(R.id.txt_movie_metascore);
        txtSynopnsis = findViewById(R.id.txt_movie_sinopsis);
        txtDirector = findViewById(R.id.txt_movie_director);
        txtStars = findViewById(R.id.txt_movie_stars);
        txtVotes = findViewById(R.id.txt_movie_votes);
        txtGross = findViewById(R.id.txt_movie_gross);
        imgThumb = findViewById(R.id.img_movie_thumb);

        loadData();
    }

    void loadData(){
        Movie movie = getIntent().getParcelableExtra(var.EX_MOVIE);

        txtNumber.setText(movie.getNumber());
        txtTitle.setText(movie.getTitle());
        txtYear.setText(movie.getYear());
        txtAgeGroup.setText(movie.getGroup());
        txtDuration.setText(movie.getDuration());
        txtRate.setText(movie.getRating());
        txtMetascore.setText(movie.getMetascore());
        txtSynopnsis.setText(movie.getSynopsis());
        txtDirector.setText(movie.getDirector());
        txtStars.setText(movie.getStars());
        txtVotes.setText(movie.getVotes());
        txtGross.setText(movie.getGross());

        Glide.with(getApplicationContext())
                .load(movie.getImage())
                .apply(new RequestOptions().override(100))
                .into(imgThumb);
    }
}
