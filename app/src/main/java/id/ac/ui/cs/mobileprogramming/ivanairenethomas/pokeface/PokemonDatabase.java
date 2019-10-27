package id.ac.ui.cs.mobileprogramming.ivanairenethomas.pokeface;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {PokemonEntity.class}, version = 1)
public abstract class PokemonDatabase extends RoomDatabase {
    public abstract PokemonDao pokemonDao();
}
