package learning.shinesdev.mymovies2.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import learning.shinesdev.mymovies2.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import learning.shinesdev.mymovies2.model.MovieModel;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.ListViewHolder> {
    private final Context context;
    private OnItemClickCallback onItemClickCallback;

    @SuppressLint("SimpleDateFormat")
    private final SimpleDateFormat format = new SimpleDateFormat("yyyy");

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    private final ArrayList<MovieModel> listMovie;
    public ListMovieAdapter(Context context,ArrayList<MovieModel> list) {
        this.context = context;
        this.listMovie = list;
    }

    @NonNull
    @Override
    public ListMovieAdapter.ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_movies, viewGroup, false);
        return new ListViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ListMovieAdapter.ListViewHolder holder, int position) {
        MovieModel movie = listMovie.get(position);
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listMovie.get(holder.getAdapterPosition()), holder.getAdapterPosition());
            }
        });
    }

    public interface OnItemClickCallback {
        void onItemClicked(MovieModel data, int idx);
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
