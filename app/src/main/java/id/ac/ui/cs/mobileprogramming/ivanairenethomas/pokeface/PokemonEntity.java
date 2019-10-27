package id.ac.ui.cs.mobileprogramming.ivanairenethomas.pokeface;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pokemons")
public class PokemonEntity {

//    @PrimaryKey
//    public int id;

    @ColumnInfo(name = "image_url")
    public String imageUrl;

    @PrimaryKey
    @NonNull
    public String name;

    public String type;

    public String abilities;

    public String weaknesses;

    public Boolean caught;

    public int level;

    public PokemonEntity(String name, String imageUrl, String type, String abilities, String weaknesses) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.type = type;
        this.abilities = abilities;
        this.weaknesses = weaknesses;
        this.caught = false;
        this.level = 1;
    }
}
