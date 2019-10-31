package id.ac.ui.cs.mobileprogramming.ivanairenethomas.pokeface;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.pokeface.R;

import java.util.List;


public class PokedexListFragment extends Fragment {
    List<PokemonEntity> mPokemons;
    PokemonViewModel pokemonViewModel;

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
        pokemonViewModel = ViewModelProviders.of(this).get(PokemonViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mPokemons = pokemonViewModel.getAllPokemons().getValue();

        View view = inflater.inflate(R.layout.fragment_pokedex_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        int grid = this.getScreenResolution(getContext()) > 1600 ? 6 : 3;
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), grid));
        recyclerView.setAdapter(new PokedexListAdapter(mPokemons));
        return view;
    }

    private static int getScreenResolution(Context context)
    {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;

        return width;
    }
}
