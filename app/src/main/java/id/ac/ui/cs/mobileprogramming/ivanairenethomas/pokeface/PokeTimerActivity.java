package id.ac.ui.cs.mobileprogramming.ivanairenethomas.pokeface;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pokeface.R;
public class PokeTimerActivity extends AppCompatActivity {

    TextView textView;
    Button start, pause, reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        textView = (TextView)findViewById(R.id.textView);
        start = (Button)findViewById(R.id.start_button);
        pause = (Button)findViewById(R.id.pause_button);
        reset = (Button)findViewById(R.id.reset_button);

        PokefaceApp.getInstance().timerTextView = textView;

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PokefaceApp.getInstance().startTimer();
                reset.setEnabled(false);
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PokefaceApp.getInstance().pauseTimer();
                reset.setEnabled(true);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PokefaceApp.getInstance().resetTimer();
                textView.setText("00:00:00");
            }
        });
    }
}
