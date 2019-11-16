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

    public class ListRandTVShowAdapter extends RecyclerView.Adapter<ListRandTVShowAdapter.MyViewHolder> {
        private final Context context;
        private final LayoutInflater inflater;
        private final ArrayList<TVShowModel> arrayList;
        private OnItemClickCallback onItemClickCallback;

        public ListRandTVShowAdapter(Context ctx, ArrayList<TVShowModel> imageModelArrayList){
            inflater = LayoutInflater.from(ctx);
            this.context = ctx;
            this.arrayList = imageModelArrayList;
        }

        @NonNull
        @Override
        public ListRandTVShowAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = inflater.inflate(R.layout.item_list_detail_random, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ListRandTVShowAdapter.MyViewHolder holder, int position) {
            holder.iv.setImageResource(context.getResources().getIdentifier(arrayList.get(position).getImage(), "drawable", context.getPackageName()));
            holder.time.setText(arrayList.get(position).getTitle());

            holder.itemView.setOnClickListener(v -> onItemClickCallback.onItemClicked(arrayList.get(holder.getAdapterPosition())));
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder{

            final TextView time;
            final ImageView iv;

            MyViewHolder(View itemView) {
                super(itemView);
                time = itemView.findViewById(R.id.txt_tv_title);
                iv =  itemView.findViewById(R.id.img_tv_thumb);
            }
        }

        public interface OnItemClickCallback {
            void onItemClicked(TVShowModel data);
        }
        public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
            this.onItemClickCallback = onItemClickCallback;
        }
    }
