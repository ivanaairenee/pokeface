package id.ac.ui.cs.mobileprogramming.ivanairenethomas.pokeface;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "pokemon_generations")
public class PokemonGenerationEntity {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name="generation_name")
    public String name;

    public PokemonGenerationEntity(String name) {
        this.name = name;
    }
}
