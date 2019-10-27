package id.ac.ui.cs.mobileprogramming.ivanairenethomas.pokeface;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PokemonViewModel extends ViewModel {
    private final MutableLiveData<PokemonEntity> selectedPokemon = new MutableLiveData<PokemonEntity>();

    public void selectPokemon(PokemonEntity pokemonDatabaseView) {
        selectedPokemon.setValue(pokemonDatabaseView);
    }

    public MutableLiveData<PokemonEntity> getSelectedPokemon() {
        return selectedPokemon;
    }
}
