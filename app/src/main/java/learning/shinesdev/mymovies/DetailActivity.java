package learning.shinesdev.mymovies;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import learning.shinesdev.mymovies.model.Movie;
import learning.shinesdev.mymovies.utils.GlobalVariable;

public class DetailActivity extends AppCompatActivity {
    private GlobalVariable var;
    private TextView txtTitle;
    private TextView txtNextTitle1;
    private TextView txtNextTitle2;
    private TextView txtNextTitle3;
    private TextView txtYear;
    private TextView txtSynopnsis;
    private TextView txtDirector;
    private TextView txtStars;
    private TextView txtVotes;
    private ImageView imgThumb;
    private ImageView imgThumb1;
    private ImageView imgThumb2;
    private ImageView imgThumb3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        var = new GlobalVariable();

        txtTitle = findViewById(R.id.txt_movie_title);
        txtNextTitle1 = findViewById(R.id.txt_movie_title_1);
        txtNextTitle2 = findViewById(R.id.txt_movie_title_2);
        txtNextTitle3 = findViewById(R.id.txt_movie_title_3);
        txtYear = findViewById(R.id.txt_movie_year);
        txtSynopnsis = findViewById(R.id.txt_movie_synopsis);
        txtDirector = findViewById(R.id.txt_movie_director);
        txtStars = findViewById(R.id.txt_movie_stars);
        txtVotes = findViewById(R.id.txt_movie_votes);
        imgThumb = findViewById(R.id.img_movie_thumb);
        imgThumb1 = findViewById(R.id.img_thumb_1);
        imgThumb2 = findViewById(R.id.img_thumb_2);
        imgThumb3 = findViewById(R.id.img_thumb_3);

        loadData();
    }

    void loadData() {
        Movie movie = getIntent().getParcelableExtra(var.EX_MOVIE);
        txtTitle.setText(movie.getTitle());
        txtNextTitle1.setText(movie.getNextTitle1());
        txtNextTitle2.setText(movie.getNextTitle2());
        txtNextTitle3.setText(movie.getNextTitle3());

        txtYear.setText(movie.getYear());
        txtSynopnsis.setText(movie.getSynopsis());
        txtDirector.setText(movie.getDirector());
        txtStars.setText(movie.getStars());
        txtVotes.setText(movie.getVotes());

        imgThumb.setImageResource(getResources().getIdentifier(movie.getImage(), "drawable", getPackageName()));
        imgThumb1.setImageResource(getResources().getIdentifier(movie.getNextImage1(), "drawable", getPackageName()));
        imgThumb2.setImageResource(getResources().getIdentifier(movie.getNextImage2(), "drawable", getPackageName()));
        imgThumb3.setImageResource(getResources().getIdentifier(movie.getNextImage3(), "drawable", getPackageName()));
    }
}
