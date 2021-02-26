package com.boumaiza.agecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;

public class MainActivity extends AppCompatActivity{
    private EditText Fname, Lname, ageT;
    private String age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ageT = findViewById(R.id.editTextTextPersonName3);
        ageT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar C = Calendar.getInstance();
                int mYear = C.get(Calendar.YEAR);
                int mMonth = C.get(Calendar.MONTH);
                int mDay = C.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dateDialog = new DatePickerDialog(v.getContext(),datePickerListener,mYear,mMonth,mDay);
                dateDialog.getDatePicker().setMaxDate(new Date().getTime());
                dateDialog.show();
            }
        });


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fname = findViewById(R.id.editTextTextPersonName);
                Lname = findViewById(R.id.editTextTextPersonName2);
                String Firstname = Fname.getText().toString();
                String Lastname = Lname.getText().toString();

                Intent intent = new Intent(MainActivity.this,ageCalculated.class);
                intent.putExtra("firstName",Firstname);
                intent.putExtra("lastName",Lastname);
                intent.putExtra("age",age);
                startActivity(intent);
            }
        });
    }
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR,year);
            c.set(Calendar.MONTH,month);
            c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            String format = new SimpleDateFormat("dd MM YYYY").format(c.getTime());
            age = Integer.toString(calculateAge(c.getTimeInMillis()));

            String date = month + " / " + dayOfMonth + " / " + year;
            ageT.setText(date);
        }
    };

    int calculateAge (long date){
        Calendar dob = Calendar.getInstance();
        dob.setTimeInMillis(date);
        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if(today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH))
            age--;
        return  age;
    }

}