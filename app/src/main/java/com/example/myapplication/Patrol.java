package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Switch;
import android.view.ViewGroup;

public class Patrol extends AppCompatActivity{
    private EditText inputSituation, inputLocation;
    private Button submitBtn;
    private ImageButton backArrow;
    private TextSizeHelper textSizeHelper = new TextSizeHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.partol);
        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        boolean isBigTextEnabled = prefs.getBoolean("bigTextEnabled", false);

        Switch bigTextSwitch = findViewById(R.id.bigText);
        ViewGroup rootLayout = findViewById(R.id.patrol);

        textSizeHelper.applyInitialScaling(rootLayout, this);
        bigTextSwitch.setChecked(isBigTextEnabled);

        textSizeHelper.setupSwitch(bigTextSwitch, rootLayout, this);

        inputSituation = findViewById(R.id.inputSituation);
        inputLocation = findViewById(R.id.inputLocation);
        submitBtn = findViewById(R.id.submitBtn);


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String situationText = inputSituation.getText().toString().trim();
                String locationText = inputLocation.getText().toString().trim();

                if (situationText.isEmpty() || locationText.isEmpty()) {
                    Toast.makeText(Patrol.this, "Uzupełnij oba pola", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Patrol.this, "Zgłoszenie wysłane!", Toast.LENGTH_SHORT).show();

                    inputSituation.setText("");
                    inputLocation.setText("");
                }


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