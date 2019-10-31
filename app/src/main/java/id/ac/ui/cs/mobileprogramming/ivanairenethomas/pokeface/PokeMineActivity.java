package id.ac.ui.cs.mobileprogramming.ivanairenethomas.pokeface;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokeface.R;

import java.util.List;

public class PokeMineActivity extends AppCompatActivity {
    PokemonViewModel pokemonViewModel;
    List<PokemonEntity> caughtPokemons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pokemonViewModel = ViewModelProviders.of(this).get(PokemonViewModel.class);
        caughtPokemons = pokemonViewModel.getCaughtPokemons().getValue();
        setContentView(R.layout.activity_pokemine);
        RecyclerView recyclerView = findViewById(R.id.pokemine_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PokeMineAdapter(this, caughtPokemons));
    }
}
