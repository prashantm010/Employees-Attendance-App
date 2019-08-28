package com.example.shubham.pnbapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FillDetails extends AppCompatActivity {

    String name, id, contactno, gender1, dob1, address1, bloodgroup1, pin1, post1;
    EditText username, userid, cno, dob, address, bloodgroup, post, pin, gender;
    Button Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_details);

        username = (EditText) findViewById(R.id.editText3);
        userid = (EditText) findViewById(R.id.editText4);
        cno = (EditText) findViewById(R.id.editText5);
        gender = (EditText) findViewById(R.id.editText15);
        dob = (EditText) findViewById(R.id.editText6);
        address = (EditText) findViewById(R.id.editText7);
        bloodgroup = (EditText) findViewById(R.id.editText8);
        post = (EditText) findViewById(R.id.editText9);
        pin = (EditText) findViewById(R.id.editText13);
        Submit = (Button)findViewById(R.id.button22);

        Intent intent = getIntent();
        final String Key = intent.getStringExtra("My");

        FirebaseDatabase.getInstance().getReference().child("Accounts").child(Key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                CreateUserModel createUserModel = dataSnapshot.getValue(CreateUserModel.class);
                username.setText(createUserModel.getName());
                userid.setText(createUserModel.getId1());
                cno.setText(createUserModel.getContactno());
                gender.setText(createUserModel.getGender());
                dob.setText(createUserModel.getDob());
                address.setText(createUserModel.getAddress());
                bloodgroup.setText(createUserModel.getBG());
                pin.setText(createUserModel.getPin());
                post.setText(createUserModel.getPost());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = username.getText().toString();
                id = userid.getText().toString();
                contactno = cno.getText().toString();
                gender1 = gender.getText().toString();
                dob1 = dob.getText().toString();
                address1 = address.getText().toString();
                bloodgroup1 = bloodgroup.getText().toString();
                pin1 = pin.getText().toString();
                post1 = post.getText().toString();


                CreateUserModel cran = new CreateUserModel(name,id,pin1,gender1,post1,contactno,dob1,address1,bloodgroup1);
                FirebaseDatabase.getInstance().getReference().child("Accounts").child(Key).setValue(cran);

                startActivity(new Intent(FillDetails.this,ViewEmployee.class));
                finish();

            }
        });
    }
}
