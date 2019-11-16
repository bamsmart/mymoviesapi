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

public class ListRandMovieAdapter extends RecyclerView.Adapter<ListRandMovieAdapter.MyViewHolder> {
    private final Context context;
    private final LayoutInflater inflater;
    private final ArrayList<MovieModel> movieList;
    private OnItemClickCallback onItemClickCallbacks;

    public ListRandMovieAdapter(Context ctx, ArrayList<MovieModel> list){
        inflater = LayoutInflater.from(ctx);
        this.context = ctx;
        this.movieList = list;
    }

    @NonNull
    @Override
    public ListRandMovieAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_list_detail_random, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListRandMovieAdapter.MyViewHolder holder, int position) {
        holder.time.setText(movieList.get(position).getTitle());
        String img_url = "https://image.tmdb.org/t/p/w600_and_h900_bestv2"+movieList.get(position).getImage();
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
        void onItemClicked(MovieModel data);
    }
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallbacks = onItemClickCallback;
    }
}
