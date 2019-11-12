package learning.shinesdev.mymovies2.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;

import learning.shinesdev.mymovies2.R;


public class TVShowData {
    private final Context context;


    public TVShowData(Context context) {
        this.context = context;
    }

    public ArrayList<TVShowModel> getArrListData(){
        ArrayList<TVShowModel> arrList = new ArrayList<>();
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
            TVShowModel tv = new TVShowModel();
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
    public ArrayList<TVShowModel> getRandListData(int idx){
        ArrayList<TVShowModel> arrList = new ArrayList<>();

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

        ArrayList<Integer> list = new ArrayList<>();
        for (int i=0; i< getCount(); i++) {
            if(i != idx){
                list.add(i);
            }
        }
        Collections.shuffle(list);
        for (int i=0; i<3; i++) {
            TVShowModel tv = new TVShowModel();
            tv.setTitle(data_title[list.get(i)]);
            tv.setYear(data_year[list.get(i)]);
            tv.setGroup(data_group[list.get(i)]);
            tv.setDuration(data_duration[list.get(i)]);
            tv.setGenre(data_genre[list.get(i)]);
            tv.setRating(data_rating[list.get(i)]);
            tv.setSynopsis(data_overview[list.get(i)]);
            tv.setStars(data_stars[list.get(i)]);
            tv.setVotes(data_votes[list.get(i)]);
            tv.setImage(data_image[list.get(i)]);
            arrList.add(tv);
        }
        return arrList;
    }

    private int getCount(){
        String [] data_title = context.getResources().getStringArray(R.array.tv_data_title);
        return data_title.length;
    }
}
