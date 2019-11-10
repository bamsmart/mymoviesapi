package learning.shinesdev.mymovies2.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import learning.shinesdev.mymovies2.R;
import learning.shinesdev.mymovies2.model.Movie;
import learning.shinesdev.mymovies2.model.TV;

public class ListTVShowAdapter extends RecyclerView.Adapter<ListTVShowAdapter.ListViewHolder> {
    private final Context context;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    private final ArrayList<TV> listTVShow;

    public ListTVShowAdapter(Context context, ArrayList<TV> list) {
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
        TV tvshow = listTVShow.get(position);

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
        int len = (getItemCount() - 1);
        if (position == (len - 2)) {
            tvshow.setNextTitle1(listTVShow.get(position + 1).getTitle());
            tvshow.setNextTitle2(listTVShow.get(position + 2).getTitle());
            tvshow.setNextTitle3(listTVShow.get(0).getTitle());

            tvshow.setNextImage1(listTVShow.get(position + 1).getImage());
            tvshow.setNextImage2(listTVShow.get(position + 2).getImage());
            tvshow.setNextImage3(listTVShow.get(0).getImage());

        } else if (position == (len - 1)) {
            tvshow.setNextTitle1(listTVShow.get(position + 1).getTitle());
            tvshow.setNextTitle2(listTVShow.get(0).getTitle());
            tvshow.setNextTitle3(listTVShow.get(1).getTitle());

            tvshow.setNextImage1(listTVShow.get(position + 1).getImage());
            tvshow.setNextImage2(listTVShow.get(0).getImage());
            tvshow.setNextImage3(listTVShow.get(1).getImage());

        } else if (position == (len)) {

            tvshow.setNextTitle1(listTVShow.get(0).getTitle());
            tvshow.setNextTitle2(listTVShow.get(1).getTitle());
            tvshow.setNextTitle3(listTVShow.get(2).getTitle());

            tvshow.setNextImage1(listTVShow.get(0).getImage());
            tvshow.setNextImage2(listTVShow.get(1).getImage());
            tvshow.setNextImage3(listTVShow.get(2).getImage());
        } else {
            tvshow.setNextTitle2(listTVShow.get(position + 2).getTitle());
            tvshow.setNextTitle3(listTVShow.get(position + 3).getTitle());

            tvshow.setNextImage1(listTVShow.get(position + 1).getImage());
            tvshow.setNextImage2(listTVShow.get(position + 2).getImage());
            tvshow.setNextImage3(listTVShow.get(position + 3).getImage());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listTVShow.get(holder.getAdapterPosition()));
            }
        });
    }

    public interface OnItemClickCallback {
        void onItemClicked(TV data);
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
