package com.example.shubham.pnbapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class EmpStart extends AppCompatActivity {

    Button b2,b3,b4,b5,b7,b8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_start);

        b2=(Button)findViewById(R.id.button11);
        b3=(Button)findViewById(R.id.button12);
        b4=(Button)findViewById(R.id.button13);
        b5=(Button)findViewById(R.id.button14);
        b7=(Button)findViewById(R.id.button16);
        b8=(Button)findViewById(R.id.button17);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newintent = new Intent(EmpStart.this,UserInfo.class);
                startActivity(newintent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newintent = new Intent(EmpStart.this,MarkAttendance.class);
                startActivity(newintent);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newintent = new Intent(EmpStart.this,Attendance.class);
                startActivity(newintent);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newintent = new Intent(EmpStart.this,LeaveRequest.class);
                startActivity(newintent);
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newintent = new Intent(EmpStart.this,Holiday.class);
                startActivity(newintent);
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newintent = new Intent(EmpStart.this,Response.class);
                startActivity(newintent);
            }
        });
    }
}
