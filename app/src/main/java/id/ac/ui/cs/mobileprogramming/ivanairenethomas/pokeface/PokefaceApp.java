package id.ac.ui.cs.mobileprogramming.ivanairenethomas.pokeface;

import android.app.Application;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.TextView;

import androidx.room.Room;


public class PokefaceApp extends Application {
    PokemonDatabase db;
    private static PokefaceApp mInstance;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L;
    Handler handler;
    int Seconds, Minutes, MilliSeconds;
    TextView timerTextView;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        db = Room.databaseBuilder(getApplicationContext(), PokemonDatabase.class, "pokefave-database").build();
        handler = new Handler();
        timerTextView = new TextView(this);
    }

    public static synchronized PokefaceApp getInstance() {
        return mInstance;
    }

    public PokemonDatabase getDatabase() {
        return db;
    }

    public void startTimer() {
        StartTime = SystemClock.uptimeMillis();
        handler.postDelayed(runnable, 0);
    }

    public void pauseTimer() {
        TimeBuff += MillisecondTime;
        handler.removeCallbacks(runnable);
    }

    public void resetTimer() {
        MillisecondTime = 0L;
        StartTime = 0L;
        TimeBuff = 0L;
        UpdateTime = 0L;
        Seconds = 0;
        Minutes = 0;
        MilliSeconds = 0;
    }


    public Runnable runnable = new Runnable() {
        public void run() {
            MillisecondTime = SystemClock.uptimeMillis() - StartTime;
            UpdateTime = TimeBuff + MillisecondTime;
            Seconds = (int) (UpdateTime / 1000);
            Minutes = Seconds / 60;
            Seconds = Seconds % 60;
            MilliSeconds = (int) (UpdateTime % 1000);
            handler.postDelayed(this, 0);
            timerTextView.setText("" + Minutes + ":"
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds));

        }

    };
}
