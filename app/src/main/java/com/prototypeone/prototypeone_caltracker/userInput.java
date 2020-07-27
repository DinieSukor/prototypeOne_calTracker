package com.prototypeone.prototypeone_caltracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class userInput extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final String EXTRA_TEXT = "com.prototypeone.prototypeone_caltracker_EXTRA_TEXT";
    public static final String EXTRA_NUM = "com.prototypeone.prototypeone_caltracker_EXTRA_NUM";


    // Initialize variables
    EditText weight, height, age;
    RadioButton male, female;
    Button submit;
    Spinner spinner;
    Double weightVal, heightVal, ageVal, temp, calories;
    String wGoal;
    int pos;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_input);

        // Initialize variable
        weight = findViewById(R.id.weight_et);
        height = findViewById(R.id.height_et);
        age = findViewById(R.id.age_et);
        male = findViewById(R.id.male_rbtn);
        female = findViewById(R.id.female_rbtn);
        spinner = findViewById(R.id.spinner);
        submit = findViewById(R.id.submit_btn);

        // Take user's weight goal from MainActivity.class
        Intent intent = getIntent();
        wGoal = intent.getStringExtra(MainActivity.WEIGHT_GOAL);

        // Make spinner
        makeSpinner();
        // Show calculated calories
        submitButton();
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        pos = i;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    // Put activities into spinner
    public void makeSpinner(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.activity, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    // Calculate calories method
    public void calculation(){


        // Convert weight, height and age to Double;
        weightVal = Double.parseDouble(weight.getText().toString());
        heightVal = Double.parseDouble(height.getText().toString());
        ageVal = Double.parseDouble(age.getText().toString());

        // Calories calculation formula (The Miffilin Eq)
        // Incorporate gender
        if (male.isChecked()){
            temp = (10.0*weightVal) + (6.25*heightVal) - (5.0*ageVal) + 5.0;
        }else if(female.isChecked()){
            temp = (10.0*weightVal) + (6.25*heightVal) - (5.0*ageVal) -161.0;
        }

        // Incorporate activity
        if (pos == 1){
            temp = temp*1.2;
        }else if (pos == 2){
            temp = temp*1.375;
        }else if (pos == 3){
            temp = temp*1.55;
        }else if (pos == 4){
            temp = temp*1.725;
        }else if (pos == 5){
            temp = temp*1.9;
        }

        // Incorporate user's weight goal
        switch (wGoal) {
            case "Gain Weight":
                calories = temp + 1000;
                break;
            case "Maintain Weight":
                calories = temp;
                break;
            case "Lose Weight":
                calories = temp - 1000;
                break;
        }


    }

    // Make button that calculate calories
    public void submitButton(){

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calculation();
                if (pos ==0){
                    Toast.makeText(userInput.this,"Pick an activity " + calories ,Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(userInput.this,"To "+ wGoal + " You Need To Consume " + calories + "Kcal/day",Toast.LENGTH_SHORT).show();
                }
                openDisplayData();

            }
        });

    }

    public void openDisplayData(){
        Intent intent = new Intent(this,displayData.class);
        String cal = calories.toString();

        intent.putExtra(EXTRA_TEXT,wGoal);
        intent.putExtra(EXTRA_NUM,cal);

        startActivity(intent);
    }
}