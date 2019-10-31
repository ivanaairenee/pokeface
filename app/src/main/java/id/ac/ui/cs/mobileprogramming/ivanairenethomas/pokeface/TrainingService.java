package id.ac.ui.cs.mobileprogramming.ivanairenethomas.pokeface;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.example.pokeface.R;

public class TrainingService extends Service {
    public TrainingService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        Toast.makeText(this, R.string.training_started_text, Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
