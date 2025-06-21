package com.example.myapplication;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.content.Context;
public class TextSizeHelper {

    private boolean isTextBig = false;
    private final float SCALE_FACTOR = 1.5f;

    public void setupSwitch(Switch bigTextSwitch, ViewGroup rootLayout, Context context) {
        bigTextSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
            prefs.edit().putBoolean("bigTextEnabled", isChecked).apply();
            if (isChecked != isTextBig) {
                changeTextSize(rootLayout, isChecked, context);
                isTextBig = isChecked;
                Log.e("s", "switch aktywny");


            }
        });
    }

    public void applyInitialScaling(ViewGroup rootLayout, Context context) {
        SharedPreferences prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        boolean isBig = prefs.getBoolean("bigTextEnabled", false);

        if (isBig != isTextBig) {
            changeTextSize(rootLayout, true, context);
            isTextBig = isBig;
        }
    }
    public void changeTextSize(View view, boolean enlarge, Context context) {

        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int i = 0; i < group.getChildCount(); i++) {
                changeTextSize(group.getChildAt(i), enlarge, context);
            }
        } else if (view instanceof TextView) {
            TextView textView = (TextView) view;
            float originalSize = textView.getTextSize() / context.getResources().getDisplayMetrics().scaledDensity;
            textView.setTextSize(enlarge ? originalSize * SCALE_FACTOR : originalSize / SCALE_FACTOR);
        }
    }
}