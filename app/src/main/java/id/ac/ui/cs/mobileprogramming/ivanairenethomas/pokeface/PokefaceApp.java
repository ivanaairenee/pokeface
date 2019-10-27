package id.ac.ui.cs.mobileprogramming.ivanairenethomas.pokeface;

import android.app.Application;
import android.content.res.Resources;
import android.os.AsyncTask;

import androidx.room.Room;

import com.example.pokeface.R;

public class PokefaceApp extends Application {
    PokemonDatabase db;
    private static PokefaceApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        db = Room.databaseBuilder(getApplicationContext(), PokemonDatabase.class, "pokefave-database").build();
        this.populateDatabse();
    }

    public static synchronized PokefaceApp getInstance() {
        return mInstance;
    }

    public PokemonDatabase getDatabase() {
        return db;
    }

    private void populateDatabse() {
        new PopulateDatabaseAsyncTask().execute();
    }

    private class PopulateDatabaseAsyncTask extends AsyncTask<Void, Void, Void> {
        private String[] imageUrls;
        private String[] names;
        private String[] types;
        private String[] abilities;
        private String[] weaknesses;

        @Override
        protected Void doInBackground(Void... url) {
            db.pokemonDao().deleteAll();
            if (db.pokemonDao().getAll().isEmpty()) {
                if (getApplicationContext() != null) {
                    Resources resources = getApplicationContext().getResources();
                    names = resources.getStringArray(R.array.names);
                    imageUrls = resources.getStringArray(R.array.imageUrls);
                    types = resources.getStringArray(R.array.types);
                    abilities = resources.getStringArray(R.array.abilities);
                    weaknesses = resources.getStringArray(R.array.weaknesses);
                }
                for (int i = 0; i < names.length; i++) {
                    PokemonEntity pokemonEntity = new PokemonEntity(names[i], imageUrls[i], types[i], abilities[i], weaknesses[i]);
                    db.pokemonDao().insertAll(pokemonEntity);
                }
            }

            return null;
        }
    }
}
