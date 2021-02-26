package com.boumaiza.agecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ageCalculated extends AppCompatActivity {
    private TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_calculated);
        txt = findViewById(R.id.textView6);

        String FirstName = getIntent().getStringExtra("firstName");
        String LastName = getIntent().getStringExtra("lastName");
        String age = getIntent().getStringExtra("age");

        txt.setText(FirstName +" "+ LastName + "    " +age);
    }
}