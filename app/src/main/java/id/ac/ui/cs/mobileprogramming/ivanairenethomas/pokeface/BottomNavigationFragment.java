package id.ac.ui.cs.mobileprogramming.ivanairenethomas.pokeface;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pokeface.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class BottomNavigationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_navigation, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        BottomNavigationView bottomNavigationView = getView().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.pokedex_menu_item:
                        if (getActivity().getClass() != PokedexListActivity.class || getParentFragment().getClass() != PokedexListFragment.class) {
                            Intent intent = new Intent(getActivity(), PokedexListActivity.class);
                            startActivity(intent);
                        }
                        break;
                    case R.id.poketimer_menu_item:
                        if (getActivity().getClass() != PokeTimerActivity.class) {
                            Intent intent = new Intent(getActivity(), PokeTimerActivity.class);
                            startActivity(intent);
                        }
                        break;
                }
                return true;
            }
        });
    }

}
