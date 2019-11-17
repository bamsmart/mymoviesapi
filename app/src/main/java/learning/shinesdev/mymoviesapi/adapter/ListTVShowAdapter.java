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
import learning.shinesdev.mymoviesapi.model.TVShowModel;

public class ListTVShowAdapter extends RecyclerView.Adapter<ListTVShowAdapter.ListViewHolder> {
    private final Context context;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    private final ArrayList<TVShowModel> listTVShow;

    public ListTVShowAdapter(Context context, ArrayList<TVShowModel> list) {
        this.context = context;
        this.listTVShow = list;
    }

    @NonNull
    @Override
    public ListTVShowAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_tvshow, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListTVShowAdapter.ListViewHolder holder, int position) {
        TVShowModel tvshow = listTVShow.get(position);

        holder.txtTitle.setText(tvshow.getName());
        holder.txtYear.setText(String.valueOf(tvshow.getFirst_air_date()));
        holder.txtRate.setText(String.valueOf(tvshow.getVote_average()));
        holder.txtOverview.setText(tvshow.getOverview());
        holder.txtVotes.setText(String.valueOf(tvshow.getVote_count()));
        String img_url = "https://image.tmdb.org/t/p/w600_and_h900_bestv2" + tvshow.getPoster_path();
        try {
            Glide.with(context).load(img_url)
                    .centerCrop()
                    .into(holder.imgThumb);
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.itemView.setOnClickListener(v -> onItemClickCallback.onItemClicked(listTVShow.get(holder.getAdapterPosition())));
    }

    public interface OnItemClickCallback {
        void onItemClicked(TVShowModel data);
    }

    @Override
    public int getItemCount() {
        return listTVShow.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtTitle;
        private final TextView txtYear;
        private final TextView txtRate;
        private final TextView txtOverview;
        private final TextView txtVotes;
        private final ImageView imgThumb;

        ListViewHolder(View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txt_tv_title);
            txtYear = itemView.findViewById(R.id.txt_tv_year);
            txtRate = itemView.findViewById(R.id.txt_tv_rating);
            txtOverview = itemView.findViewById(R.id.txt_tv_sinopsis);
            txtVotes = itemView.findViewById(R.id.txt_tv_votes);
            imgThumb = itemView.findViewById(R.id.img_tv_thumb);
        }
    }
}