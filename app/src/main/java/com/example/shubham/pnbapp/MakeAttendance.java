package com.example.shubham.pnbapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class MakeAttendance extends AppCompatActivity {

    Button present, absent;
    String date1;
    Calendar myCalendar;
    EditText editText1;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_attendance);

        present=(Button)findViewById(R.id.button10);
        absent = (Button)findViewById(R.id.button11);
        editText1 = (EditText)findViewById(R.id.editText10);
       // present.setEnabled(true);
        //present.setClickable(true);
        myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        editText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MakeAttendance.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        Intent intent = getIntent();
        final String data = intent.getStringExtra("My");

        absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    FirebaseDatabase.getInstance().getReference().child("Attendance").child(data).child(editText1.getText().toString()).setValue(editText1.getText().toString() + " Absent");
                    Toast.makeText(getApplicationContext(), "You Have been Marked Absent for Today, i.e. " + editText1.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    FirebaseDatabase.getInstance().getReference().child("Attendance").child(data).child(editText1.getText().toString()).setValue(editText1.getText().toString() + " Present");
                    Toast.makeText(getApplicationContext(), "You Have been Marked Present for Today, i.e. " + editText1.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateLabel() {
        String myFormat = "dd:MM:yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

        editText1.setText(sdf.format(myCalendar.getTime()));

    }
}
