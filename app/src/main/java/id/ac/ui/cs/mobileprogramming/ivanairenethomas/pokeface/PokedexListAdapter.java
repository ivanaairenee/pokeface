package id.ac.ui.cs.mobileprogramming.ivanairenethomas.pokeface;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokeface.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PokedexListAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private List<PokemonEntity> mPokemonList;

    public PokedexListAdapter(List<PokemonEntity> pokemonList){
        this.mPokemonList = pokemonList;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.pokedex_list_item, parent, false));
    }

    @Override
    public int getItemCount() {
        if (mPokemonList != null && mPokemonList.size() > 0) {
            return mPokemonList.size();
        } else {
            return 1;
        }
    }

    public class ViewHolder extends BaseViewHolder implements View.OnClickListener {

        @BindView(R.id.thumbnail)
        ImageView coverImageView;

        @BindView(R.id.pokemon_name)
        TextView pokemonNameTextView;

        private PokemonEntity mPokemon;
        private PokemonViewModel pokemonViewModel;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            pokemonViewModel = ViewModelProviders.of((PokedexListActivity) itemView.getContext()).get(PokemonViewModel.class);
        }

        @Override
        public void onClick(View view) {
            PokedexListActivity pokedexListActivity = (PokedexListActivity) view.getContext();
            pokemonViewModel.selectPokemon(mPokemon);
            pokedexListActivity.onPokemonSelected();
        }

        protected void clear() {
            coverImageView.setImageDrawable(null);
            pokemonNameTextView.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);

            mPokemon = mPokemonList.get(position);
            Context c = coverImageView.getContext();
            int imageResourceId = c.getResources().getIdentifier(mPokemon.imageUrl, null, c.getPackageName());
            coverImageView.setImageResource(imageResourceId);

            if (mPokemon.name != null) {
                pokemonNameTextView.setText(mPokemon.name);
            }
        }
    }
}
