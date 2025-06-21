package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

public class WezwijPomoc extends AppCompatActivity {
    private TextSizeHelper textSizeHelper = new TextSizeHelper();
    private Button cancelButton;
    private ImageButton backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wezwij_pomoc);

        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        boolean isBigTextEnabled = prefs.getBoolean("bigTextEnabled", false);

        Switch bigTextSwitch = findViewById(R.id.bigText);
        ViewGroup rootLayout = findViewById(R.id.pomoc);

        textSizeHelper.applyInitialScaling(rootLayout, this);
        bigTextSwitch.setChecked(isBigTextEnabled);

        textSizeHelper.setupSwitch(bigTextSwitch, rootLayout, this);
        cancelButton = findViewById(R.id.cancelButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });
        backArrow = findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}