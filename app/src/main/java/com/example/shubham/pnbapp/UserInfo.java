package com.example.shubham.pnbapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserInfo extends AppCompatActivity {
    String name,id,contactno,gender1,dob1,address1,bloodgroup1,pin1,post1;
    EditText username, userid, cno, dob, address, bloodgroup, post,pin,gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        username=(EditText)findViewById(R.id.editText3);
        username.setEnabled(false);
        userid=(EditText)findViewById(R.id.editText4);
        userid.setEnabled(false);
        cno=(EditText)findViewById(R.id.editText5);
        cno.setEnabled(false);
        gender=(EditText) findViewById(R.id.editText15);
        gender.setEnabled(false);
        dob=(EditText)findViewById(R.id.editText6);
        dob.setEnabled(false);
        address=(EditText)findViewById(R.id.editText7);
        address.setEnabled(false);
        bloodgroup=(EditText)findViewById(R.id.editText8);
        bloodgroup.setEnabled(false);
        post=(EditText)findViewById(R.id.editText9);
        post.setEnabled(false);
        pin=(EditText)findViewById(R.id.editText13);
        pin.setEnabled(false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseDatabase.getInstance().getReference().child("Accounts").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getChildren()!=null){
                    try{
                        name = dataSnapshot.child("name").getValue().toString();
                    id = dataSnapshot.child("id1").getValue().toString();
                    contactno = dataSnapshot.child("contactno").getValue().toString();
                    gender1 = dataSnapshot.child("gender").getValue().toString();
                    dob1 = dataSnapshot.child("dob").getValue().toString();
                    address1 = dataSnapshot.child("address").getValue().toString();
                    bloodgroup1 = dataSnapshot.child("bg").getValue().toString();
                    pin1 = dataSnapshot.child("pin").getValue().toString();
                    post1 = dataSnapshot.child("post").getValue().toString();

                        username.setText(name);
                        userid.setText(id);
                        cno.setText(contactno);
                        gender.setText(gender1);
                        dob.setText(dob1);
                        address.setText(address1);
                        bloodgroup.setText(bloodgroup1);
                        pin.setText(pin1);
                        post.setText(post1);
                    }catch (Exception e){
                        Log.d("Tag",e.toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

