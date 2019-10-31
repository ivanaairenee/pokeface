package id.ac.ui.cs.mobileprogramming.ivanairenethomas.pokeface;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "pokeballs", foreignKeys = @ForeignKey(entity = PokemonEntity.class,
        parentColumns = "name",
        childColumns = "pokemon_name"), indices = {@Index("pokemon_name")})
public class PokeballEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String pokemon_name;

    public PokeballEntity(String pokemon_name) {
        this.pokemon_name = pokemon_name;
    }
}
