package id.ac.ui.cs.mobileprogramming.ivanairenethomas.pokeface;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.pokeface.R;

public class BatteryLevelReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Toast.makeText(context, R.string.low_battery_reminder, Toast.LENGTH_LONG).show();
    }
}
