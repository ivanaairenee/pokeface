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

public class PokeMineAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private List<PokemonEntity> caughtPokemonList;

    public PokeMineAdapter(List<PokemonEntity> pokemonList){
        this.caughtPokemonList = pokemonList;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PokeMineAdapter.ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemine_list_item, parent, false));
    }

    @Override
    public int getItemCount() {
        if (caughtPokemonList != null && caughtPokemonList.size() > 0) {
            return caughtPokemonList.size();
        } else {
            return 1;
        }
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.thumbnail)
        ImageView coverImageView;

        @BindView(R.id.pokemon_name)
        TextView pokemonNameTextView;

        @BindView(R.id.pokemon_level)
        TextView pokemonLevelTextView;

        private PokemonEntity mPokemon;
        private PokemonViewModel pokemonViewModel;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            pokemonViewModel = ViewModelProviders.of((PokeMineActivity) itemView.getContext()).get(PokemonViewModel.class);
        }

        protected void clear() {
            coverImageView.setImageDrawable(null);
            pokemonNameTextView.setText("");
            pokemonLevelTextView.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);

            mPokemon = caughtPokemonList.get(position);
            Context c = coverImageView.getContext();
            int imageResourceId = c.getResources().getIdentifier(mPokemon.imageUrl, null, c.getPackageName());
            coverImageView.setImageResource(imageResourceId);

            if (mPokemon.name != null) {
                pokemonNameTextView.setText(mPokemon.name);
            }

            if (mPokemon.level != 0) {
                pokemonLevelTextView.setText("Level: "+mPokemon.level);
            }
        }
    }
}
