package id.ac.ui.cs.mobileprogramming.ivanairenethomas.pokeface;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokeface.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.NOTIFICATION_SERVICE;

public class PokeMineAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private List<PokemonEntity> caughtPokemonList;
    Context context;

    public PokeMineAdapter(Context context, List<PokemonEntity> pokemonList){
        this.caughtPokemonList = pokemonList;
        this.context = context;
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

        @BindView(R.id.pokemon_train_button)
        Button pokemonTrainButton;

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

            if (caughtPokemonList.size() > 0) {
                mPokemon = caughtPokemonList.get(position);
                Context c = coverImageView.getContext();
                int imageResourceId = c.getResources().getIdentifier(mPokemon.imageUrl, null, c.getPackageName());
                coverImageView.setImageResource(imageResourceId);

                if (mPokemon.name != null) {
                    pokemonNameTextView.setText(mPokemon.name);
                }

                if (mPokemon.level != 0) {
                    pokemonLevelTextView.setText("Level: " + mPokemon.level);
                }

                pokemonTrainButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pokemonTrainButton.setEnabled(false);
                        pokemonTrainButton.setText(R.string.training_in_progress);
                        context.startService(new Intent(context, TrainingService.class));

                        new CountDownTimer(10000 * mPokemon.level, 1000) {
                            public void onTick(long millisUntilFinished) {
                                pokemonLevelTextView.setText("seconds remaining: " + millisUntilFinished / 1000);

                            }

                            public void onFinish() {
                                PokemonEntity leveledUpPokemon = pokemonViewModel.trainPokemon(mPokemon);
                                pokemonLevelTextView.setText("Level: " + leveledUpPokemon.level);
                                pokemonTrainButton.setText(R.string.train_pokemon_button);
                                pokemonTrainButton.setEnabled(true);
                                context.stopService(new Intent(context, TrainingService.class));
                            }
                        }.start();
                    }
                });
            }

        }
    }
}
