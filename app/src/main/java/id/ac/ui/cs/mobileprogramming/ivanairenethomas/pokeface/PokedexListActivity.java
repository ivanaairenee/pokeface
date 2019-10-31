package id.ac.ui.cs.mobileprogramming.ivanairenethomas.pokeface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.example.pokeface.R;

import java.util.concurrent.ExecutionException;

public class PokedexListActivity extends AppCompatActivity {
    private BroadcastReceiver batteryLevelReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PokemonViewModel pokemonViewModel = ViewModelProviders.of(this).get(PokemonViewModel.class);
        if (pokemonViewModel.getAllPokemons() == null) {
            try {
                pokemonViewModel.populateDatabase();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        setContentView(R.layout.activity_list_pokedex);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_list_pokemon, PokedexListFragment.newInstance(), "pokemonList")
                    .commit();
        }

        batteryLevelReceiver = new BatteryLevelReceiver();
    }

    @Override
    protected void onStart() {
        registerReceiver(batteryLevelReceiver, new IntentFilter(Intent.ACTION_BATTERY_LOW));
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(batteryLevelReceiver);
        super.onStop();
    }

    public void onPokemonSelected() {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .add(R.id.activity_list_pokemon, PokedexDetailsFragment.newInstance(), "pokemonDetails")
                .commit();
    }
}
