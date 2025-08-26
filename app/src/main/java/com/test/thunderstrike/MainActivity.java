package com.test.thunderstrike;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.nvidia.shieldtech.accessories.Accessory;
import com.nvidia.shieldtech.accessories.AccessoryManager;

import static android.os.SystemClock.sleep;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Thunderstrike";

    AccessoryManager accessoryManager;
    Button btnStart, btnAccesories;
    Accessory accesories[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accessoryManager = AccessoryManager.getInstance(this);
        accessoryManager.connect();

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startPairing();
            }
        });


        btnAccesories = (Button) findViewById(R.id.btnAccesories);
        btnAccesories.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                listAccesories();
            }
        });

    }

    public void startPairing()
    {
        Log.e(TAG, "start pairing");
        accessoryManager.startPairingActivity();
    }

    public void listAccesories()
    {
        String accesoryStrings[];
        accesories = accessoryManager.getAccessories();
        Log.e(TAG, "accesories: " + accesories.length);
        for (int i = 0; i < accesories.length; i++) {
            Log.e(TAG, "accesory: " + accesories[i].getName());
            String vibrate;
            if (accesories[i].getFeature("vibrate") == -1)
                vibrate = "false";
            else
                vibrate = "true";
            Log.e(TAG, "vibration: " + vibrate + " " + accesories[i].getFeature("vibrate"));
            Log.e(TAG, "status: " + accesories[i].getConnectionState() + " type: " + accesories[i].getConnectionType());
            accesories[i].vibrate(32000, 32000, 1000);
        }

    }
}
