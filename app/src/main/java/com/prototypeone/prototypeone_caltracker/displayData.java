package com.prototypeone.prototypeone_caltracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class displayData extends AppCompatActivity {

    // Initialize variables
    TextView calValue;
    String wGoal, calories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        // Find ids
        calValue = findViewById(R.id.calValue_tv);

        Intent intent = getIntent();
        wGoal = intent.getStringExtra(userInput.EXTRA_TEXT);
        calories = intent.getStringExtra(userInput.EXTRA_NUM);
        calValue.setText("You'll Need " + calories + " Kcal/Day For You To " + wGoal  );

    }
}