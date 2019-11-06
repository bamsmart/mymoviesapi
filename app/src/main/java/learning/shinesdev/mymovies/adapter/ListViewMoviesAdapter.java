package learning.shinesdev.mymovies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import learning.shinesdev.mymovies.R;
import learning.shinesdev.mymovies.model.Movie;

public class ListViewMoviesAdapter extends BaseAdapter {
    private final Context context;
    private ArrayList<Movie> movies = new ArrayList<>();

    public ListViewMoviesAdapter(Context context) {
        this.context = context;
    }

    public void setMovie(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int i) {
        return movies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view;
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_list_movies, viewGroup, false);
        }

        ViewHolder viewHolder = new ViewHolder(itemView);

        Movie movie = (Movie) getItem(i);
        viewHolder.bind(movie);

        return itemView;
    }

    private class ViewHolder {
        private final TextView txtTitle;
        private final TextView txtYear;
        private final TextView txtAgeGroup;
        private final TextView txtDuration;
        private final TextView txtRate;
        private final TextView txtMetascore;
        private final TextView txtSynopnsis;
        private final TextView txtDirector;
        private final TextView txtStars;
        private final TextView txtVotes;
        private final TextView txtGross;

        private final ImageView imgThumb;

        @SuppressWarnings("unused")
        ViewHolder(View view) {
            TextView txtNumber = view.findViewById(R.id.txt_movie_number);
            txtTitle = view.findViewById(R.id.txt_movie_title);
            txtYear = view.findViewById(R.id.txt_movie_year);
            txtAgeGroup = view.findViewById(R.id.txt_movie_age_group);
            txtDuration = view.findViewById(R.id.txt_movie_minutes);
            txtRate = view.findViewById(R.id.txt_movie_rating);
            txtMetascore = view.findViewById(R.id.txt_movie_metascore);
            txtSynopnsis = view.findViewById(R.id.txt_movie_sinopsis);
            txtDirector = view.findViewById(R.id.txt_movie_director);
            txtStars = view.findViewById(R.id.txt_movie_stars);
            txtVotes = view.findViewById(R.id.txt_movie_votes);
            txtGross = view.findViewById(R.id.txt_movie_gross);
            imgThumb = view.findViewById(R.id.img_movie_thumb);
        }

        void bind(Movie movie) {
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
            imgThumb.setImageResource(context.getResources().getIdentifier(movie.getImage(), "drawable", context.getPackageName()));
        }
    }
}
