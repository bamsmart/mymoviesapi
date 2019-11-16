package learning.shinesdev.mymoviesapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

        holder.txtTitle.setText(tvshow.getTitle());
        holder.txtYear.setText("("+tvshow.getYear());
        holder.txtGroup.setText(tvshow.getGroup());
        holder.txtDuration.setText(tvshow.getDuration());
        holder.txtRate.setText(tvshow.getRating());
        holder.txtOverview.setText(tvshow.getSynopsis());
        holder.txtStars.setText(tvshow.getStars());
        holder.txtVotes.setText(tvshow.getVotes());
        try{
            holder.imgThumb.setImageResource(context.getResources().getIdentifier(tvshow.getImage(), "drawable", context.getPackageName()));
        }catch (Exception e){
            e.printStackTrace();
        }

        holder.itemView.setOnClickListener(v -> onItemClickCallback.onItemClicked(listTVShow.get(holder.getAdapterPosition()),holder.getAdapterPosition()));
    }

    public interface OnItemClickCallback {
        void onItemClicked(TVShowModel data, int idx);
    }

    @Override
    public int getItemCount() {
        return listTVShow.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtTitle;
        private final TextView txtYear;
        private final TextView txtGroup;
        private final TextView txtDuration;
        private final TextView txtRate;
        private final TextView txtOverview;
        private final TextView txtStars;
        private final TextView txtVotes;
        private final ImageView imgThumb;

        ListViewHolder(View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txt_tv_title);
            txtYear = itemView.findViewById(R.id.txt_tv_year);
            txtGroup = itemView.findViewById(R.id.txt_tv_age_group);
            txtDuration = itemView.findViewById(R.id.txt_tv_minutes);
            txtRate = itemView.findViewById(R.id.txt_tv_rating);
            txtOverview = itemView.findViewById(R.id.txt_tv_sinopsis);
            txtStars = itemView.findViewById(R.id.txt_tv_stars);
            txtVotes = itemView.findViewById(R.id.txt_tv_votes);
            imgThumb = itemView.findViewById(R.id.img_tv_thumb);
        }
    }
}
