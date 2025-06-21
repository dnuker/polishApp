package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Switch;
import android.view.ViewGroup;

public class Poradniki extends AppCompatActivity {
    private TextSizeHelper textSizeHelper = new TextSizeHelper();
    private ImageButton backArrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poradniki);

        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        boolean isBigTextEnabled = prefs.getBoolean("bigTextEnabled", false);

        Switch bigTextSwitch = findViewById(R.id.bigText);
        ViewGroup rootLayout = findViewById(R.id.poradniki);

        textSizeHelper.applyInitialScaling(rootLayout, this);
        bigTextSwitch.setChecked(isBigTextEnabled);

        textSizeHelper.setupSwitch(bigTextSwitch, rootLayout, this);

        TextView link1 = findViewById(R.id.link1);
        TextView link2 = findViewById(R.id.link2);
        TextView link3 = findViewById(R.id.link3);


        link1.setMovementMethod(LinkMovementMethod.getInstance());
        link2.setMovementMethod(LinkMovementMethod.getInstance());
        link3.setMovementMethod(LinkMovementMethod.getInstance());

        backArrow = findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
