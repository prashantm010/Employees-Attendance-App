package com.example.shubham.pnbapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Login extends AppCompatActivity {

    EditText employeeid;
    EditText Password;
    RadioGroup Type;
    RadioButton rb;
    Button b1,b2,submit;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    String radio1, radio2;
    String ids,pin,type;
    private ProgressDialog progressDialog;
    private HashMap<String,String> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        b2 = (Button) findViewById(R.id.button26);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent2 = new Intent(Login.this, ForgotPin.class);
                Login.this.startActivity(myintent2);
            }
        });

        hashMap = new HashMap<>();
        employeeid = (EditText) findViewById(R.id.editText);
        Password = (EditText) findViewById(R.id.editText2);
        submit = (Button) findViewById(R.id.button);
        Type = (RadioGroup) findViewById(R.id.type);
        progressDialog = new ProgressDialog(Login.this);
        Type.clearCheck();

        Type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                rb = (RadioButton) radioGroup.findViewById(i);
                if (i == R.id.radioButton) {
                    radio1 = rb.getText().toString();
                    //Toast.makeText(getApplicationContext(),radio1, Toast.LENGTH_SHORT).show();
                } else if (i == R.id.radioButton2) {
                    radio2 = rb.getText().toString();
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = employeeid.getText().toString();
                String emailids = data + "@gmail.com";
                final String pin = Password.getText().toString();

                progressDialog.setMessage("Logining...");
                progressDialog.show();

                if(TextUtils.isEmpty(data)){
                    Toast.makeText(getApplicationContext(),"Please Enter the Id",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(pin)){
                    Toast.makeText(getApplicationContext(),"Please Enter the Pin",Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    final String value = hashMap.get(emailids);
                    if(value.equals(radio1)){
                        mAuth.signInWithEmailAndPassword(emailids, pin)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressDialog.dismiss();
                                        if (!task.isSuccessful()) {
                                            if (pin.length() < 6) {
                                                Toast.makeText(Login.this,"Password is Short",Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(Login.this, "Could Not Login, Please Try again...", Toast.LENGTH_LONG).show();
                                            }
                                        } else {
                                            Intent intent = new Intent(Login.this, EmpStart.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }
                                });
                    }else if(value.equals(radio2)){
                        mAuth.signInWithEmailAndPassword(emailids, pin)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressDialog.dismiss();
                                        if (!task.isSuccessful()) {
                                            if (pin.length() < 6) {
                                                Toast.makeText(Login.this,"Password is Short",Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(Login.this, "Could Not Login, Please Try again...", Toast.LENGTH_LONG).show();
                                            }
                                        } else {
                                            Intent intent = new Intent(Login.this, AdminStart.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }
                                });
                    }else{
                        Toast.makeText(Login.this,"Please Use the Correct Details to Sign In",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                }catch (Exception e){
                    Log.d("TAG",e.toString());
                }


            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseDatabase.getInstance().getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    for (DataSnapshot dataSnapshot11 : dataSnapshot1.getChildren()) {
                        ids = dataSnapshot11.getValue().toString();
                        type = dataSnapshot11.getKey();
                        hashMap.put(ids, type);
                    }
                }
                Log.d("TAG",hashMap.toString());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }
}
