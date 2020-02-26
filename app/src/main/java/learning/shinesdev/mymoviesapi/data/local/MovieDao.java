package learning.shinesdev.mymoviesapi.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import learning.shinesdev.mymoviesapi.model.MovieEntity;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM MovieEntity")
    LiveData<List<MovieEntity>> getAllFavoriteMovie();

    // Dao method to insert note
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(MovieEntity movie);

    @Update
    void updateMovie(MovieEntity movie);

    @Delete
    void deleteMovie(MovieEntity movie);

    @Delete
    public void deleteAll(MovieEntity movie1, MovieEntity movie2);

    @Query("SELECT * FROM MovieEntity WHERE title LIKE :keyTitle ")
    MovieEntity findByName(String keyTitle);

}
