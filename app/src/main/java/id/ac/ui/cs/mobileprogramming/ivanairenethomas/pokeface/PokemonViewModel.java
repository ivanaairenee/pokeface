package id.ac.ui.cs.mobileprogramming.ivanairenethomas.pokeface;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pokeface.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PokemonViewModel extends ViewModel {
    private final MutableLiveData<PokemonEntity> selectedPokemon = new MutableLiveData<PokemonEntity>();
    private final MutableLiveData<List<PokemonEntity>> allPokemons = new MutableLiveData<>();
    private final MutableLiveData<List<PokemonEntity>> caughtPokemons = new MutableLiveData<>();


    public MutableLiveData<PokemonEntity> getSelectedPokemon() {
        return selectedPokemon;
    }

    public MutableLiveData<List<PokemonEntity>> getAllPokemons() {
        if (allPokemons.getValue() == null) {
            try {
                allPokemons.setValue(this.populateDatabase());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return allPokemons;
    }

    public MutableLiveData<List<PokemonEntity>> getCaughtPokemons() {
        if (caughtPokemons.getValue() == null) {
            try {
                caughtPokemons.setValue(new GetCaughtPokemonsAsyncTask().execute().get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return caughtPokemons;
    }

    public void selectPokemon(PokemonEntity pokemonEntity) {
        selectedPokemon.setValue(pokemonEntity);
    }

    public PokemonEntity trainPokemon(PokemonEntity pokemonEntity) {
        String newLevel = String.valueOf(pokemonEntity.level + 1);
        try {
            return new TrainPokemonAsyncTask().execute(pokemonEntity.name, newLevel).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return pokemonEntity;
    }

    public List<PokemonEntity> populateDatabase() throws ExecutionException, InterruptedException {
        return new PopulateDatabaseAsyncTask().execute().get();
    }

    public PokemonEntity catchPokemon(PokemonEntity pokemonEntity) {
        PokemonEntity newPokemon = pokemonEntity;
        try {
            newPokemon = new CatchPokemonAsyncTask().execute(pokemonEntity.name).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<PokemonEntity> updatedAllPokemons = allPokemons.getValue();
        updatedAllPokemons.remove(pokemonEntity);
        updatedAllPokemons.add(newPokemon);
        allPokemons.setValue(updatedAllPokemons);
        return newPokemon;
    }

    private class TrainPokemonAsyncTask extends AsyncTask<String, Void, PokemonEntity> {
        PokemonDatabase db = PokefaceApp.getInstance().getDatabase();

        @Override
        protected PokemonEntity doInBackground(String... params) {
            db.pokemonDao().trainPokemon(params[0], Integer.parseInt(params[1]));
            return db.pokemonDao().getByName(params[0]);
        }
    }

    private class CatchPokemonAsyncTask extends AsyncTask<String, Void, PokemonEntity> {
        PokemonDatabase db = PokefaceApp.getInstance().getDatabase();

        @Override
        protected PokemonEntity doInBackground(String... name) {
            Date date = new Date();
            db.pokemonDao().catchPokemon(name[0], date);
            return db.pokemonDao().getByName(name[0]);
        }
    }

    private class GetCaughtPokemonsAsyncTask extends AsyncTask<Void, Void, List<PokemonEntity>> {
        PokemonDatabase db = PokefaceApp.getInstance().getDatabase();

        @Override
        protected List<PokemonEntity> doInBackground(Void... url) {
            List<PokemonEntity> allPokemons = db.pokemonDao().getAll();
            List<PokemonEntity> filteredCaughtPokemons = new ArrayList<>();
            for (int i = 0; i < allPokemons.size(); i++) {
                if (allPokemons.get(i).caught) {
                    filteredCaughtPokemons.add(allPokemons.get(i));
                }
            }
            return filteredCaughtPokemons;
        }

        @Override
        protected void onPostExecute(List<PokemonEntity> filteredCaughtPokemons) {
            caughtPokemons.setValue(filteredCaughtPokemons);
        }
    }

    private class PopulateDatabaseAsyncTask extends AsyncTask<Void, Void, List<PokemonEntity>> {
        private String[] imageUrls;
        private String[] names;
        private String[] types;
        private String[] abilities;
        private String[] weaknesses;
        private String[] generations;
        private String currentGeneration;
        PokemonDatabase db = PokefaceApp.getInstance().getDatabase();

        @Override
        protected List<PokemonEntity> doInBackground(Void... url) {
            if (db.pokemonDao().getAll().isEmpty()) {
                Context c = PokefaceApp.getInstance().getApplicationContext();
                if (c != null) {
                    Resources resources = c.getResources();
                    names = resources.getStringArray(R.array.names);
                    imageUrls = resources.getStringArray(R.array.imageUrls);
                    types = resources.getStringArray(R.array.types);
                    abilities = resources.getStringArray(R.array.abilities);
                    weaknesses = resources.getStringArray(R.array.weaknesses);
                    generations = resources.getStringArray(R.array.generations);
                    currentGeneration = resources.getString(R.string.current_generation);
                }
                PokemonGenerationEntity pokemonGenerationEntity = new PokemonGenerationEntity(currentGeneration);
                db.pokemonGenerationDao().insertAll(pokemonGenerationEntity);
                for (int i = 0; i < names.length; i++) {
                    PokemonEntity pokemonEntity = new PokemonEntity(names[i], imageUrls[i], types[i], abilities[i], weaknesses[i], generations[i]);
                    PokeballEntity pokeballEntity = new PokeballEntity(names[i]);
                    db.pokemonDao().insertAll(pokemonEntity);
                    db.pokeballDao().insertAll(pokeballEntity);
                }
            }

            return db.pokemonDao().getAll();
        }

        @Override
        protected void onPostExecute(List<PokemonEntity> populatedPokemons) {
            allPokemons.setValue(populatedPokemons);
        }
    }
}
