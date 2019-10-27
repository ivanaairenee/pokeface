package id.ac.ui.cs.mobileprogramming.ivanairenethomas.pokeface;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pokeface.R;

public class PokedexListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pokemon);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_list_pokemon, PokedexListFragment.newInstance(), "pokemonList")
                    .commit();
        }
    }

    public void onPokemonSelected() {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .add(R.id.activity_list_pokemon, PokedexDetailsFragment.newInstance(), "pokemonDetails")
                .commit();
    }
}
