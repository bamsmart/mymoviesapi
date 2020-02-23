package learning.shinesdev.mymoviesapi.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import learning.shinesdev.mymoviesapi.model.MovieModel;

@Database(entities = {MovieModel.class}, version = 1,exportSchema = false)
public abstract class DatabaseConfig extends RoomDatabase {
    private static DatabaseConfig mInstance;

    public static DatabaseConfig getDatabase(Context context){
        if (mInstance == null){
            mInstance = Room.databaseBuilder(context.getApplicationContext(),
                    DatabaseConfig.class,"movie_db")
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
