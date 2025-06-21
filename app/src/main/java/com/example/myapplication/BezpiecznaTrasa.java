package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Switch;
import android.view.ViewGroup;

public class BezpiecznaTrasa extends AppCompatActivity {
    private EditText phoneInput;
    private Button activateButton;
    private TextSizeHelper textSizeHelper = new TextSizeHelper();
    private ImageButton backArrow;
    private TextView activatedMessage;
    private boolean isActivated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bezpieczna_trasa);
        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        boolean isBigTextEnabled = prefs.getBoolean("bigTextEnabled", false);
        Switch bigTextSwitch = findViewById(R.id.bigText);
        ViewGroup rootLayout = findViewById(R.id.bezpieczna);
        activatedMessage = findViewById(R.id.activatedMessage);

        textSizeHelper.applyInitialScaling(rootLayout, this);
        bigTextSwitch.setChecked(isBigTextEnabled);
        textSizeHelper.setupSwitch(bigTextSwitch, rootLayout, this);
        phoneInput = findViewById(R.id.phoneInput);
        activateButton = findViewById(R.id.activateButton);

        activateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phoneInput.getText().toString().trim();
                if(!isActivated){
                if (phone.isEmpty() || !PhoneNumberUtils.isGlobalPhoneNumber(phone) || phone.length()!=9) {
                    Toast.makeText(BezpiecznaTrasa.this, "Wprowadź poprawny numer telefonu", Toast.LENGTH_SHORT).show();
                    return;
                }
                    activateButton.setBackgroundTintList(getResources().getColorStateList(android.R.color.holo_green_dark));
                    activatedMessage.setVisibility(View.VISIBLE);
                    Toast.makeText(BezpiecznaTrasa.this, "Moduł bezpieczna trasa aktywowany!", Toast.LENGTH_LONG).show();
                    isActivated = true;


                } else {
                    activateButton.setBackgroundTintList(getResources().getColorStateList(android.R.color.holo_red_dark));
                    activatedMessage.setVisibility(View.GONE);
                    Toast.makeText(BezpiecznaTrasa.this, "Moduł bezpieczna trasa dezaktywowany!", Toast.LENGTH_SHORT).show();
                    isActivated = false;
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
