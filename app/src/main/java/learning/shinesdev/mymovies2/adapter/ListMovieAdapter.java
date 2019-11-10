package learning.shinesdev.mymovies2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import learning.shinesdev.mymovies2.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import learning.shinesdev.mymovies2.model.Movie;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.ListViewHolder> {
    private final Context context;
    private OnItemClickCallback onItemClickCallback;

    SimpleDateFormat format = new SimpleDateFormat("yyyy");

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    private final ArrayList<Movie> listMovie;
    public ListMovieAdapter(Context context,ArrayList<Movie> list) {
        this.context = context;
        this.listMovie = list;
    }

    @NonNull
    @Override
    public ListMovieAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_movies, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListMovieAdapter.ListViewHolder holder, int position) {
        Movie movie = listMovie.get(position);

        holder.txtTitle.setText(movie.getTitle());
        try {
            Date date = format.parse(movie.getYear());
            holder.txtYear.setText("("+format.format(date)+")");
        }catch (Exception e){
            holder.txtYear.setText("("+movie.getYear());
        }
        holder.txtAgeGroup.setText(movie.getGroup());
        holder.txtDuration.setText(movie.getDuration());
        holder.txtRate.setText(movie.getRating());
        holder.txtMetascore.setText(movie.getMetascore());
        holder.txtSynopnsis.setText(movie.getSynopsis());
        holder.txtDirector.setText(movie.getDirector());
        holder.txtStars.setText(movie.getStars());
        holder.txtVotes.setText(movie.getVotes());
        holder.txtGross.setText(movie.getGross());
        holder.imgThumb.setImageResource(context.getResources().getIdentifier(movie.getImage(), "drawable", context.getPackageName()));


        int len = (getItemCount() - 1);
        if (position == (len - 2)) {
            movie.setNextTitle1(listMovie.get(position + 1).getTitle());
            movie.setNextTitle2(listMovie.get(position + 2).getTitle());
            movie.setNextTitle3(listMovie.get(0).getTitle());

            movie.setNextImage1(listMovie.get(position + 1).getImage());
            movie.setNextImage2(listMovie.get(position + 2).getImage());
            movie.setNextImage3(listMovie.get(0).getImage());

        } else if (position == (len - 1)) {
            movie.setNextTitle1(listMovie.get(position + 1).getTitle());
            movie.setNextTitle2(listMovie.get(0).getTitle());
            movie.setNextTitle3(listMovie.get(1).getTitle());

            movie.setNextImage1(listMovie.get(position + 1).getImage());
            movie.setNextImage2(listMovie.get(0).getImage());
            movie.setNextImage3(listMovie.get(1).getImage());

        } else if (position == (len)) {

            movie.setNextTitle1(listMovie.get(0).getTitle());
            movie.setNextTitle2(listMovie.get(1).getTitle());
            movie.setNextTitle3(listMovie.get(2).getTitle());

            movie.setNextImage1(listMovie.get(0).getImage());
            movie.setNextImage2(listMovie.get(1).getImage());
            movie.setNextImage3(listMovie.get(2).getImage());
        } else {
            movie.setNextTitle1(listMovie.get(position + 1).getTitle());
            movie.setNextTitle2(listMovie.get(position + 2).getTitle());
            movie.setNextTitle3(listMovie.get(position + 3).getTitle());

            movie.setNextImage1(listMovie.get(position + 1).getImage());
            movie.setNextImage2(listMovie.get(position + 2).getImage());
            movie.setNextImage3(listMovie.get(position + 3).getImage());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listMovie.get(holder.getAdapterPosition()));
            }
        });
    }

    public interface OnItemClickCallback {
        void onItemClicked(Movie data);
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

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

        ListViewHolder(View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txt_movie_title);
            txtYear = itemView.findViewById(R.id.txt_movie_year);
            txtAgeGroup = itemView.findViewById(R.id.txt_movie_age_group);
            txtDuration = itemView.findViewById(R.id.txt_movie_minutes);
            txtRate = itemView.findViewById(R.id.txt_movie_rating);
            txtMetascore = itemView.findViewById(R.id.txt_movie_metascore);
            txtSynopnsis = itemView.findViewById(R.id.txt_movie_sinopsis);
            txtDirector = itemView.findViewById(R.id.txt_movie_director);
            txtStars = itemView.findViewById(R.id.txt_movie_stars);
            txtVotes = itemView.findViewById(R.id.txt_movie_votes);
            txtGross = itemView.findViewById(R.id.txt_movie_gross);
            imgThumb = itemView.findViewById(R.id.img_movie_thumb);
        }
    }
}
