package id.ac.ui.cs.mobileprogramming.ivanairenethomas.pokeface;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pokeface.R;


public class PokedexDetailsFragment extends Fragment {
    static PokemonEntity mPokemon;
    static PokemonViewModel pokemonViewModel;

    public PokedexDetailsFragment() {
        // Required empty public constructor
    }

    public static PokedexDetailsFragment newInstance() {
        PokedexDetailsFragment fragment = new PokedexDetailsFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        pokemonViewModel = ViewModelProviders.of(getActivity()).get(PokemonViewModel.class);
        mPokemon = pokemonViewModel.getSelectedPokemon().getValue();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pokemon_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ImageView pokemonImage = getView().findViewById(R.id.pokemon_image);
        TextView pokemonName = getView().findViewById(R.id.pokemon_name);
        TextView pokemonType = getView().findViewById(R.id.pokemon_type);
        TextView pokemonAbilities = getView().findViewById(R.id.pokemon_abilities);
        TextView pokemonWeaknesses = getView().findViewById(R.id.pokemon_weaknesses);

        if (mPokemon.imageUrl != null) {
            Glide.with(getContext())
                    .load(mPokemon.imageUrl)
                    .into(pokemonImage);
        }

        if (mPokemon.name != null) {
            pokemonName.setText(mPokemon.name);
        }

        if (mPokemon.type != null) {
            pokemonType.setText(getString(R.string.type_text)+": "+ mPokemon.type);
        }

        if (mPokemon.abilities != null) {
            pokemonAbilities.setText(getString(R.string.abilities_text)+": " + mPokemon.abilities);
        }

        if (mPokemon.weaknesses != null) {
            pokemonWeaknesses.setText(getString(R.string.weaknesses_text)+": " + mPokemon.weaknesses);
        }
    }
}
