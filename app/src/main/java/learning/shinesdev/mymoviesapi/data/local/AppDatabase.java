package learning.shinesdev.mymoviesapi.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import learning.shinesdev.mymoviesapi.model.MovieEntity;

@Database(entities = {MovieEntity.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase mInstance;

    public static AppDatabase getDatabase(Context context){
        if (mInstance == null){
            mInstance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class,"movie_db")
                    .build();
        }
        return mInstance;
    }

    //define movie dao ( data access object )
    public abstract MovieDao movieDao();

    //method to remove instance
    public static void closeDatabase() {
        mInstance = null;
    }
}
