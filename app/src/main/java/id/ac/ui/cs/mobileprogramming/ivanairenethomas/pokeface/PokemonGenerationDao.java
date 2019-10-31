package id.ac.ui.cs.mobileprogramming.ivanairenethomas.pokeface;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PokemonGenerationDao {
    @Query("SELECT * FROM pokemon_generations")
    List<PokemonGenerationEntity> getAll();

    @Query("DELETE FROM pokemon_generations")
    void deleteAll();

    @Insert
    void insertAll(PokemonGenerationEntity... pokemonGenerationEntities);
}
