package id.ac.ui.cs.mobileprogramming.ivanairenethomas.pokeface;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;


@Entity(tableName = "pokemons",
        foreignKeys = @ForeignKey(entity = PokemonGenerationEntity.class,
                parentColumns = "generation_name",
                childColumns = "generation"), indices = {@Index("generation")})
public class PokemonEntity {

    @ColumnInfo(name = "image_url")
    public String imageUrl;

    @PrimaryKey
    @NonNull
    public String name;

    public String type;

    public String abilities;

    public String weaknesses;

    public Boolean caught;

    @ColumnInfo(name = "caught_time")
    @Nullable
    public Date caughtTime;

    public int level;

    public String generation;

    public PokemonEntity(String name, String imageUrl, String type, String abilities, String weaknesses, String generation) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.type = type;
        this.abilities = abilities;
        this.weaknesses = weaknesses;
        this.caught = false;
        this.caughtTime = null;
        this.level = 1;
        this.generation = generation;
    }
}
