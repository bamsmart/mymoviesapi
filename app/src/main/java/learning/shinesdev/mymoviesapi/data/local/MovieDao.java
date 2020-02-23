package learning.shinesdev.mymoviesapi.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import learning.shinesdev.mymoviesapi.model.MovieModel;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM MovieModel")
    LiveData<List<MovieModel>> getAllFavoriteMovie();

    // Dao method to insert note
    @Insert
    void insertMovie(MovieModel movie);

    @Update
    void updateMovie(MovieModel movie);

    @Delete
    void deleteMovie(MovieModel movie);

    @Delete
    public void deleteAll(MovieModel movie1,MovieModel movie2);

    @Query("SELECT * FROM MovieModel WHERE title LIKE :keyTitle ")
    MovieModel findByName(String keyTitle);

}
