package com.example.shubham.pnbapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
import com.google.firebase.database.FirebaseDatabase;

public class CreateUser extends AppCompatActivity {

    EditText editText1,editText2, editText3,editText4,editText5,editText6,editText7,editText8;
    Button button;
    String radioButton1,radioButton2,id, name , password,post,contactno,Gender,Address, BG,DOB;
    RadioGroup Type,Type1;
    RadioButton rb;
    ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        mAuth = FirebaseAuth.getInstance();
        editText8 = (EditText)findViewById(R.id.editText110);
        editText1 = (EditText)findViewById(R.id.editText111);
        editText2 = (EditText)findViewById(R.id.editText112);
        editText3 = (EditText)findViewById(R.id.editText113);
        editText4 = (EditText)findViewById(R.id.editText114);
        editText5 = (EditText)findViewById(R.id.editText115);
        editText6 = (EditText)findViewById(R.id.editText116);
        editText7 = (EditText)findViewById(R.id.editText117);
        Type = (RadioGroup) findViewById(R.id.type);
        Type1 = (RadioGroup) findViewById(R.id.type1);
        button = (Button) findViewById(R.id.button);
        progressDialog = new ProgressDialog(CreateUser.this);

        Type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                rb = (RadioButton) radioGroup.findViewById(i);
                if (i == R.id.radio1) {
                    radioButton1 = rb.getText().toString();
                    //Toast.makeText(getApplicationContext(),radio1, Toast.LENGTH_SHORT).show();
                } else if (i == R.id.radio2) {
                    radioButton1 = rb.getText().toString();
                }
            }
        });
        Type1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                rb = (RadioButton) radioGroup.findViewById(i);
                if (i == R.id.radio11) {
                    radioButton2 = rb.getText().toString();
                    //Toast.makeText(getApplicationContext(),radio1, Toast.LENGTH_SHORT).show();
                } else if (i == R.id.radio22) {
                    radioButton2 = rb.getText().toString();
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = editText1.getText().toString();
                id = data + "@gmail.com";
                password = editText2.getText().toString();
                name = editText8.getText().toString();
                post = editText3.getText().toString();
                contactno = editText4.getText().toString();
                DOB = editText5.getText().toString();
                Gender = radioButton1;
                Address = editText6.getText().toString();
                BG = editText7.getText().toString();



                progressDialog.setMessage("Creating User...");
                progressDialog.show();

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(getApplicationContext(),"Enter the Employee Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(data)){
                    Toast.makeText(getApplicationContext(),"Enter the Employee ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"Enter the Employee Pin", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(Gender)){
                    Toast.makeText(getApplicationContext(),"Select the Employee Gender", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(post)){
                    Toast.makeText(getApplicationContext(),"Enter the post of Employee", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(contactno)){
                    Toast.makeText(getApplicationContext(),"Enter the contact No. of Employee", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(DOB)){
                    Toast.makeText(getApplicationContext(),"Enter the Date of Birth", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(Address)){
                    Toast.makeText(getApplicationContext(),"Enter the Address of Employee", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(BG)){
                    Toast.makeText(getApplicationContext(),"Enter the Blood Group of Employee", Toast.LENGTH_SHORT).show();
                    return;
                }

                final CreateUserModel crm = new CreateUserModel(name,id,password,Gender,post,contactno,DOB,Address,BG);

                mAuth.createUserWithEmailAndPassword(id,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (!task.isSuccessful()) {
                            if (password.length() < 6) {
                                Toast.makeText(CreateUser.this,"Password is Short",Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(CreateUser.this, "Could Not Login, Please Try again...", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            FirebaseDatabase.getInstance().getReference().child("Users"). push().child(radioButton2).setValue(id);
                            FirebaseDatabase.getInstance().getReference().child("Accounts").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(crm);
                            Intent intent = new Intent(CreateUser.this, AdminStart.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });
    }
}
