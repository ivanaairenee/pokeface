package id.ac.ui.cs.mobileprogramming.ivanairenethomas.pokeface;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PokeballDao {
    @Query("SELECT * FROM pokeballs")
    List<PokeballEntity> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(PokeballEntity... pokeballEntities);

    @Query("DELETE FROM pokeballs")
    void deleteAll();
}
