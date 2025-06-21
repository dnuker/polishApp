package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.view.ViewGroup;


import androidx.appcompat.app.AppCompatActivity;

public class Ostrzezenia extends AppCompatActivity {
    private ImageButton backArrow;
    private TextSizeHelper textSizeHelper = new TextSizeHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ostrzezenia);
        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        boolean isBigTextEnabled = prefs.getBoolean("bigTextEnabled", false);
        Switch bigTextSwitch = findViewById(R.id.bigText);
        ViewGroup rootLayout = findViewById(R.id.ostrzezenia);


        textSizeHelper.applyInitialScaling(rootLayout, this);
        bigTextSwitch.setChecked(isBigTextEnabled);
        textSizeHelper.setupSwitch(bigTextSwitch, rootLayout, this);

        backArrow = findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
