package com.kindeyeindustries.jonissoundboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private static final int MY_PERMISSIONS_REQUEST = 0;
    public final String SHARES_PREFS="sharedPrefs";
    public static final String DIRPATH_KEY = "DirectoryPath";
    public static final String SOUNDS_KEY = "Sounds";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //<editor-fold desc="Check if u have permission to external storage">
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
        //</editor-fold>

        final EditText etDirPath = findViewById(R.id.actity_main_et_dirPath);
        Button btnNext = findViewById(R.id.actity_main_btn_next);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARES_PREFS,MODE_PRIVATE);
        Log.d(TAG, "onCreate: SharedPreferences null : "+(sharedPreferences == null));
        if (sharedPreferences != null){
            String directoryPath = sharedPreferences.getString(DIRPATH_KEY, null);
            Log.d(TAG, "onCreate: From shared prefs, directory is : " + directoryPath);
            String soundsJson = sharedPreferences.getString(DIRPATH_KEY, null);
            ArrayList<Sound> newSounds;
            newSounds = new ArrayList<>();
            if (!soundsJson.equals("")){
                if (!soundsJson.equals("No favourites")){
                    Gson gson = new Gson();
                    Sound[] toAdd = gson.fromJson(soundsJson, Sound[].class);
                    newSounds.addAll(Arrays.asList(toAdd));
                }
            }

            Model.getInstance(directoryPath,newSounds);

            Intent nextIntent = new Intent(this, SoundsActivity.class);
            startActivity(nextIntent);

        } else {
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String newDirPath;
                    if (!etDirPath.getText().toString().equals("")){
                        newDirPath = etDirPath.getText().toString();
                        Log.d(TAG, "onClick: From EditText directory is : "+ newDirPath);
                        Model.getInstance(newDirPath, new ArrayList<Sound>());

                        Intent nextIntent = new Intent(getApplicationContext(), SoundsActivity.class);
                        startActivity(nextIntent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Fill in the correct information",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        //TODO: use in recyclerview
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Sound s;
//                String directoryPath = Model.getInstance().getDirectoryPath();
//                String filename = s.getFilename();
//                String path = directoryPath+filename;
//                Log.d(TAG, "onClick: PATH = "+path); //TODO add position
//                File file = new File(path);
//                Log.d(TAG, "onClick: file exists : "+file.exists()+", can read : "+file.canRead()); //TODO add position
//                MediaPlayer sound = MediaPlayer.create(getApplicationContext(), Uri.parse(path));
//                sound.start();
//            }
//        });
    }
}
