package com.example.shubham.pnbapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AnyRequest extends AppCompatActivity {
    public static final String Car_Model = "https://bookamechanic-411b3.firebaseio.com/";
    public static final String Car_id = "https://bookamechanic-411b3.firebaseio.com/";

    ListView listViewcars;
    List<Mymodel> cars;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    FirebaseUser user;
    String text;
    private DatabaseReference database11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_any_request);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        user = FirebaseAuth.getInstance().getCurrentUser();
       // database11 = FirebaseDatabase.getInstance().getReference(user.getUid());

        listViewcars = (ListView) findViewById(R.id.listViewCars);
        listViewcars.setClickable(true);
        listViewcars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Mymodel car = cars.get(i);
                final String name = car.getFrom();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(AnyRequest.this);
                builder1.setTitle("Leave Request Permission...");
                builder1.setMessage(car.getFrom());
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Accept",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                               FirebaseDatabase.getInstance().getReference().child("Accounts").orderByChild("id1").equalTo(name).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                                            String names = dataSnapshot1.getKey();
                                            FirebaseDatabase.getInstance().getReference().child("Response").child(names).setValue("You can take Holiday");
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });

                            }
                        });

                builder1.setNegativeButton(
                        "Decline",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                FirebaseDatabase.getInstance().getReference().child("Accounts").orderByChild("id1").equalTo(name).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                                            final String names = dataSnapshot1.getKey();
                                            FirebaseDatabase.getInstance().getReference().child("Response").child(names).setValue("You are Not Allowed to take Holiday");

                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });


        user = FirebaseAuth.getInstance().getCurrentUser();
        text = user.getUid();
        cars = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
       // DatabaseReference rootRef = database.getInstance().getReference();
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("Leave Request");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cars.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot dataSnapshot1 : postSnapshot.getChildren()) {
                        Mymodel car = dataSnapshot1.getValue(Mymodel.class);
                        cars.add(car);
                    }
                }
                RequestData carsAdapter = new RequestData(AnyRequest.this, cars);
                listViewcars.setAdapter(carsAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        usersRef.addListenerForSingleValueEvent(eventListener);
    }
}