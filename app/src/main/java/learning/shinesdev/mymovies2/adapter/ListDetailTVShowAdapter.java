package learning.shinesdev.mymovies2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import learning.shinesdev.mymovies2.R;
import learning.shinesdev.mymovies2.model.TV;

    public class ListDetailTVShowAdapter extends RecyclerView.Adapter<ListDetailTVShowAdapter.MyViewHolder> {
        private Context context;
        private LayoutInflater inflater;
        private ArrayList<TV> arrayList;
        private OnItemClickCallback onItemClickCallback;

        public ListDetailTVShowAdapter(Context ctx, ArrayList<TV> imageModelArrayList){
            inflater = LayoutInflater.from(ctx);
            this.context = ctx;
            this.arrayList = imageModelArrayList;
        }

        @Override
        public ListDetailTVShowAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = inflater.inflate(R.layout.item_list_detail_tv, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final ListDetailTVShowAdapter.MyViewHolder holder, int position) {
            holder.iv.setImageResource(context.getResources().getIdentifier(arrayList.get(position).getImage(), "drawable", context.getPackageName()));
            holder.time.setText(arrayList.get(position).getTitle());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickCallback.onItemClicked(arrayList.get(holder.getAdapterPosition()));
                }
            });
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder{

            TextView time;
            ImageView iv;

            public MyViewHolder(View itemView) {
                super(itemView);
                time = itemView.findViewById(R.id.txt_tv_title);
                iv =  itemView.findViewById(R.id.img_tv_thumb);
            }
        }

        public interface OnItemClickCallback {
            void onItemClicked(TV data);
        }
        public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
            this.onItemClickCallback = onItemClickCallback;
        }
    }
