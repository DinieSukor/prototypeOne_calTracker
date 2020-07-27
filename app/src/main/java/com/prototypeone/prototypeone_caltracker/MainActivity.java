package com.prototypeone.prototypeone_caltracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String WEIGHT_GOAL = "com.prototypeone.prototypeone_caltracker_WEIGHT_GOAL";

    // Initialize variables
    Button submit;
    RadioButton gain, maintain, lose;
    String goal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitButton();

    }

    // Catch user's weight goal and toast it.
    public void weightGoal(){

        // initialize radio buttons
        gain = findViewById(R.id.gain_rbtn);
        maintain = findViewById(R.id.maintain_rbtn);
        lose = findViewById(R.id.lose_rbtn);

        // toast selected radio buttons
        if (gain.isChecked()){
            goal = gain.getText().toString();
        }else if (maintain.isChecked()){
            goal = maintain.getText().toString();
        }else if (lose.isChecked()){
            goal = lose.getText().toString();
        }

        Toast.makeText(this,goal,Toast.LENGTH_SHORT).show();

    }
    // Make button that open next activity
    public void submitButton(){
        submit = findViewById(R.id.submit_btn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUserInput();
            }
        });

    }
    // Open next activity method
    public void openUserInput(){

        Intent intent = new Intent(this,userInput.class);
        weightGoal();
        intent.putExtra(WEIGHT_GOAL,goal);
        startActivity(intent);
    }


}