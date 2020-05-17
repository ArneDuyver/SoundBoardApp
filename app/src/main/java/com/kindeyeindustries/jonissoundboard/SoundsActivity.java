package com.kindeyeindustries.jonissoundboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class SoundsActivity extends AppCompatActivity {
    public static final String TAG = "SoundsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sounds_view);


    }

    //<editor-fold desc="Menu">
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i(TAG, "onCreateOptionsMenu");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent startIntent;
        switch (item.getItemId()) {
            case R.id.menu_sounds:
                Log.i(TAG, "onOptionsItemSelected: sounds");
                startIntent = new Intent(getApplicationContext(), SoundsActivity.class);
                startActivity(startIntent);
                return true;
            case R.id.menu_settings:
                Log.i(TAG, "onOptionsItemSelected: settings");
                startIntent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(startIntent);
                return true;
            case R.id.menu_add:
                Log.i(TAG, "onOptionsItemSelected: add");
                startIntent = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(startIntent);
                return true;
            default:
                Log.i(TAG, "onOptionsItemSelected: nothing selected");
                return true;
        }
    }
    //</editor-fold>

}
