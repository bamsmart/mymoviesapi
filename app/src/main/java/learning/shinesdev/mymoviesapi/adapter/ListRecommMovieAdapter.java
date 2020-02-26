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
import learning.shinesdev.mymoviesapi.model.MovieEntity;

@SuppressWarnings("ALL")
public class ListRecommMovieAdapter extends RecyclerView.Adapter<ListRecommMovieAdapter.MyViewHolder> {
    private final Context context;
    private final LayoutInflater inflater;
    private final ArrayList<MovieEntity> movieList;
    private OnItemClickCallback onItemClickCallbacks;

    public ListRecommMovieAdapter(Context ctx, ArrayList<MovieEntity> list){
        inflater = LayoutInflater.from(ctx);
        this.context = ctx;
        this.movieList = list;
    }

    @NonNull
    @Override
    public ListRecommMovieAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_list_detail_random, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListRecommMovieAdapter.MyViewHolder holder, int position) {
        MovieEntity movieModel = movieList.get(position);
        holder.time.setText(movieModel.getTitle());
        String img_url = "https://image.tmdb.org/t/p/w600_and_h900_bestv2"+movieModel.getImage();
        try {
            Glide.with(context).load(img_url)
                    .centerCrop()
                    .into(holder.image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.itemView.setOnClickListener(v -> onItemClickCallbacks.onItemClicked(movieList.get(holder.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        final TextView time;
        final ImageView image;

        MyViewHolder(View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.txt_tv_title);
            image =  itemView.findViewById(R.id.img_tv_thumb);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(MovieEntity data);
    }
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallbacks = onItemClickCallback;
    }
}
