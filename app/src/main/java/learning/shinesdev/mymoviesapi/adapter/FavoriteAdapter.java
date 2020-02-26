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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import learning.shinesdev.mymoviesapi.R;
import learning.shinesdev.mymoviesapi.model.MovieEntity;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MovieHolder> {

    private final Context context;
    private List<MovieEntity> listMovie = new ArrayList<>();

    private OnItemClickListener mOnItemClickListener;

    public FavoriteAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<MovieEntity> list){
        this.listMovie = list;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_movies, viewGroup, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieHolder holder, int position) {
        MovieEntity movieModel = listMovie.get(position);
        holder.txtTitle.setText(movieModel.getTitle());
        holder.txtYear.setText(movieModel.getDate());
        holder.txtOverview.setText(movieModel.getOverview());
        holder.txtRate.setText(String.valueOf(movieModel.getRating()));
        holder.txtVotes.setText(String.valueOf(movieModel.getVote()));

        String img_url = "https://image.tmdb.org/t/p/w600_and_h900_bestv2" + movieModel.getImage();

        try {
            Glide.with(context).load(img_url)
                    .centerCrop()
                    .placeholder(R.drawable.ic_image_black_24dp)
                    .error(R.drawable.ic_broken_image_black_24dp)
                    .into(holder.imgThumb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    @Override
    public long getItemId(int position) {
        return listMovie.get(position).getId();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.txt_movie_title) TextView txtTitle;
        @BindView(R.id.txt_movie_year) TextView txtYear;
        @BindView(R.id.txt_movie_rating) TextView txtRate;
        @BindView(R.id.txt_movie_synopsis) TextView txtOverview;
        @BindView(R.id.txt_movie_votes) TextView txtVotes;
        @BindView(R.id.img_movie_thumb) ImageView imgThumb;

        MovieHolder(View itemView) {
            super(itemView);

            // implement ButterKnife
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            postItemClick(this);
        }

        private void postItemClick(MovieHolder holder) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(holder.getAdapterPosition());
            }
        }
    }
}
