package id.ac.ui.cs.mobileprogramming.ivanairenethomas.pokeface;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {PokemonEntity.class}, version = 2)
@TypeConverters({Converters.class})
public abstract class PokemonDatabase extends RoomDatabase {
    public abstract PokemonDao pokemonDao();
}
