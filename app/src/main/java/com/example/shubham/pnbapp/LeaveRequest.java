package com.example.shubham.pnbapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class LeaveRequest extends AppCompatActivity {

    private EditText editText1, editText2,editText3;
    private Button button;
    Calendar myCalendar,myCalendar1;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_request);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        editText1 = (EditText) findViewById(R.id.editText11);
        //editText1.setEnabled(false);
        editText2 = (EditText) findViewById(R.id.editText12);
        //editText2.setEnabled(false);
        editText3 = (EditText)findViewById(R.id.editText22);
        button = (Button)findViewById(R.id.button19);

        myCalendar = Calendar.getInstance();
        myCalendar1 = Calendar.getInstance();

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

        final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar1.set(Calendar.YEAR, year);
                myCalendar1.set(Calendar.MONTH, monthOfYear);
                myCalendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel1();
            }

        };


        editText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(LeaveRequest.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        editText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(LeaveRequest.this, date1, myCalendar1
                        .get(Calendar.YEAR), myCalendar1.get(Calendar.MONTH),
                        myCalendar1.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(editText1.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Enter the Date",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(editText2.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Enter the Date",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(editText3.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Enter the Complete Reason",Toast.LENGTH_SHORT).show();
                    return;
                }


              /*  FirebaseDatabase.getInstance().getReference().child("Leave Request").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("From").setValue(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                FirebaseDatabase.getInstance().getReference().child("Leave Request").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("From1").setValue(editText1.getText().toString());
                FirebaseDatabase.getInstance().getReference().child("Leave Request").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Reason").setValue(editText3.getText().toString());
                FirebaseDatabase.getInstance().getReference().child("Leave Request").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("To").setValue(editText2.getText().toString());*/


                CreateRequest mydata = new CreateRequest(editText1.getText().toString(),editText2.getText().toString(),editText3.getText().toString(),FirebaseAuth.getInstance().getCurrentUser().getEmail().toString());
                FirebaseDatabase.getInstance().getReference().child("Leave Request").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).push().setValue(mydata);

                startActivity(new Intent(LeaveRequest.this,EmpStart.class));
                finish();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

        editText1.setText(sdf.format(myCalendar.getTime()));

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateLabel1() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

        editText2.setText(sdf.format(myCalendar1.getTime()));

    }
}
