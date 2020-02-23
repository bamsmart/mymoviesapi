package learning.shinesdev.mymoviesapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import learning.shinesdev.mymoviesapi.R;
import learning.shinesdev.mymoviesapi.model.MovieModel;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ListViewHolder> {
    private final Context context;

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    private final ArrayList<MovieModel> listMovie;

    public MovieAdapter(Context context, ArrayList<MovieModel> list) {
        this.context = context;
        this.listMovie = list;
    }

    @NonNull
    @Override
    public MovieAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_movies, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieAdapter.ListViewHolder holder, int position) {
        MovieModel movieModel = listMovie.get(position);
        holder.txtTitle.setText(movieModel.getTitle());
        holder.txtYear.setText(movieModel.getDate());
        holder.txtOverview.setText(movieModel.getOverview());
        holder.txtRate.setText(String.valueOf(movieModel.getRating()));
        holder.txtVotes.setText(String.valueOf(movieModel.getVote()));
        String img_url = "https://image.tmdb.org/t/p/w600_and_h900_bestv2"+movieModel.getImage();
        try {
            Glide.with(context).load(img_url)
                    .centerCrop()
                    .into(holder.imgThumb);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.itemView.setOnClickListener(v -> onItemClickCallback.onItemClicked(listMovie.get(holder.getAdapterPosition())));
    }



    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        private final TextView
                txtTitle,
                txtYear,
                txtRate,
                txtOverview,
                txtVotes;
        private final ImageView imgThumb;

        ListViewHolder(View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txt_movie_title);
            txtYear = itemView.findViewById(R.id.txt_movie_year);
            txtRate = itemView.findViewById(R.id.txt_movie_rating);
            txtOverview = itemView.findViewById(R.id.txt_movie_sinopsis);
            txtVotes = itemView.findViewById(R.id.txt_movie_votes);
            imgThumb = itemView.findViewById(R.id.img_movie_thumb);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(MovieModel data);
    }
}
