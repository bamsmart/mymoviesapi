package learning.shinesdev.mymovies2.model;

import android.content.Context;

import java.util.ArrayList;

import learning.shinesdev.mymovies2.R;


@SuppressWarnings("ResultOfMethodCallIgnored")
public class TVData {
    private final Context context;


    public TVData(Context context) {
        this.context = context;
    }

    public ArrayList<TV> getArrListData(){
        ArrayList<TV> arrList = new ArrayList<>();
        String [] data_title = context.getResources().getStringArray(R.array.tv_data_title);
        String [] data_year = context.getResources().getStringArray(R.array.tv_data_year);
        String [] data_group = context.getResources().getStringArray(R.array.tv_data_group);
        String [] data_duration = context.getResources().getStringArray(R.array.tv_data_duration);
        String [] data_genre = context.getResources().getStringArray(R.array.tv_data_genre);
        String [] data_rating = context.getResources().getStringArray(R.array.tv_data_rating);
        String [] data_overview = context.getResources().getStringArray(R.array.tv_data_overview);
        String [] data_stars = context.getResources().getStringArray(R.array.tv_data_stars);
        String [] data_votes = context.getResources().getStringArray(R.array.tv_data_votes);
        String [] data_image = context.getResources().getStringArray(R.array.tv_data_image);

        for(int i = 0 ; i < data_title.length; i++){
            TV tv = new TV();
            tv.setTitle(data_title[i]);
            tv.setYear(data_year[i]);
            tv.setGroup(data_group[i]);
            tv.setDuration(data_duration[i]);
            tv.setGenre(data_genre[i]);
            tv.setRating(data_rating[i]);
            tv.setSynopsis(data_overview[i]);
            tv.setStars(data_stars[i]);
            tv.setVotes(data_votes[i]);
            tv.setImage(data_image[i]);

            arrList.add(tv);
        }
        return arrList;
    }
}
