package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.CompoundButton;
import android.Manifest;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {


    private TextSizeHelper textSizeHelper = new TextSizeHelper();
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 100;

    public void goToWezwanie(View view){
        Intent intent = new Intent(this,WezwijPomoc.class);
        startActivity(intent);
    }

    public void goToPatrol(View view){
        Intent intent = new Intent(this,Patrol.class);
        startActivity(intent);
    }
    public void goToPoradniki(View view){
        Intent intent = new Intent(this,Poradniki.class);
        startActivity(intent);
    }
    public void goToBezpieczna(View view){
        Intent intent = new Intent(this,BezpiecznaTrasa.class);
        startActivity(intent);
    }
    public void goToOstrzezenia(View view){
        Intent intent = new Intent(this, Ostrzezenia.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        }

        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        boolean isBigTextEnabled = prefs.getBoolean("bigTextEnabled", false);
        Log.d("DEBUG", "isBigTextEnabled = " + isBigTextEnabled);
        Switch bigTextSwitch = findViewById(R.id.bigText);
        ViewGroup rootLayout = findViewById(R.id.main);

        textSizeHelper.applyInitialScaling(rootLayout,this);
        bigTextSwitch.setChecked(isBigTextEnabled);
        textSizeHelper.setupSwitch(bigTextSwitch, rootLayout, this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @Override
    protected void onResume() {
        super.onResume();


        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        boolean isBigTextEnabled = prefs.getBoolean("bigTextEnabled", false);

        Switch bigTextSwitch = findViewById(R.id.bigText);
        ViewGroup rootLayout = findViewById(R.id.main);

        bigTextSwitch.setChecked(isBigTextEnabled); // <- to sprawia, że wygląd switcha się zgadza
        textSizeHelper.applyInitialScaling(rootLayout, this); // <- to sprawia, że tekst się dostosowuje
    }


}
