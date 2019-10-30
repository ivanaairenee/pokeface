package id.ac.ui.cs.mobileprogramming.ivanairenethomas.pokeface;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.Date;
import java.util.List;

@Dao
public interface PokemonDao {

    @Query("SELECT * FROM pokemons")
    List<PokemonEntity> getAll();

    @Query("UPDATE pokemons SET caught = 1, caught_time=:date WHERE name = :name")
    void catchPokemon(String name, Date date);

    @Query("DELETE FROM pokemons")
    void deleteAll();

    @Query("SELECT * FROM pokemons WHERE name LIKE :name LIMIT 1")
    PokemonEntity getByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(PokemonEntity... pokemonEntities);

    @Delete
    void delete(PokemonEntity pokemonEntity);
}
