package learning.shinesdev.mymovies2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

import learning.shinesdev.mymovies2.model.Movie;
import learning.shinesdev.mymovies2.model.TV;
import learning.shinesdev.mymovies2.utils.GlobVar;

public class DetailTVActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_detail_tv);

        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(R.string.lbl_header_tv_detail);

        txtTitle = findViewById(R.id.txt_tv_title);
        txtNextTitle1 = findViewById(R.id.txt_tv_title_1);
        txtNextTitle2 = findViewById(R.id.txt_tv_title_2);
        txtNextTitle3 = findViewById(R.id.txt_tv_title_3);
        txtYear = findViewById(R.id.txt_tv_year);
        txtSynopnsis = findViewById(R.id.txt_tv_synopsis);
        txtStars = findViewById(R.id.txt_tv_stars);
        txtVotes = findViewById(R.id.txt_tv_votes);
        imgThumb = findViewById(R.id.img_tv_thumb);
        imgThumb1 = findViewById(R.id.img_thumb_1);
        imgThumb2 = findViewById(R.id.img_thumb_2);
        imgThumb3 = findViewById(R.id.img_thumb_3);

        loadData();
    }

    private void loadData() {
        TV tvshow = getIntent().getParcelableExtra(GlobVar.EX_TV);
        txtTitle.setText(tvshow.getTitle());
        txtNextTitle1.setText(tvshow.getNextTitle1());
        txtNextTitle2.setText(tvshow.getNextTitle2());
        txtNextTitle3.setText(tvshow.getNextTitle3());

        txtYear.setText(tvshow.getYear());
        txtSynopnsis.setText(tvshow.getSynopsis());
        txtStars.setText(tvshow.getStars());
        txtVotes.setText(tvshow.getVotes());

        imgThumb.setImageResource(getResources().getIdentifier(tvshow.getNextImage1(), "drawable", getPackageName()));
        imgThumb2.setImageResource(getResources().getIdentifier(tvshow.getNextImage2(), "drawable", getPackageName()));
        imgThumb3.setImageResource(getResources().getIdentifier(tvshow.getNextImage3(), "drawable", getPackageName()));
    }
}
