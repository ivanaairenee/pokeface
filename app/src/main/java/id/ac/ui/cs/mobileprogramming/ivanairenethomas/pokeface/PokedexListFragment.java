package id.ac.ui.cs.mobileprogramming.ivanairenethomas.pokeface;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pokeface.R;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class PokedexListFragment extends Fragment {
    List<PokemonEntity> mPokemons;

    public PokedexListFragment() {
        // Required empty public constructor
    }

    public static PokedexListFragment newInstance() {
        PokedexListFragment fragment = new PokedexListFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            mPokemons = new GetPokemonsAsyncTask().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        View view = inflater.inflate(R.layout.fragment_pokemon_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(new PokedexListAdapter(mPokemons));
        return view;
    }

    private class GetPokemonsAsyncTask extends AsyncTask<Void, Void, List<PokemonEntity>> {
        public List<PokemonEntity> result;

        @Override
        protected List<PokemonEntity> doInBackground(Void... url) {
            return PokefaceApp.getInstance().getDatabase().pokemonDao().getAll();
        }

        @Override
        protected void onPostExecute(List<PokemonEntity> result) {
            mPokemons = result;
        }
    }
}
