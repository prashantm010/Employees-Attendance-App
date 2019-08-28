package com.example.shubham.pnbapp;

import android.content.DialogInterface;
import android.content.Intent;
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

public class ViewEmployee extends AppCompatActivity {
    ListView listViewcars;
    List<CreateUserModel> cars;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    FirebaseUser user;
    String text;
    private DatabaseReference database11;

    ListView listView=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        user = FirebaseAuth.getInstance().getCurrentUser();

        listViewcars = (ListView) findViewById(R.id.listViewCars);
        listViewcars.setClickable(true);
        listViewcars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                final CreateUserModel model = cars.get(position);
                //String email = parent.getItemAtPosition(position).toString();
                //Toast.makeText(ViewEmployee.this,email,Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder1 = new AlertDialog.Builder(ViewEmployee.this);
                builder1.setTitle("Do You want to Edit Employee Details...");
                builder1.setMessage(model.getName());
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                FirebaseDatabase.getInstance().getReference().child("Accounts").orderByChild("id1").equalTo(model.getId1()).getRef().addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                            String Key = snapshot.getKey();
                                            //Toast.makeText(ViewEmployee.this, Key, Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(ViewEmployee.this, FillDetails.class);
                                            i.putExtra("My", Key);
                                            startActivity(i);
                                        }
                                    };
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                    }
                                });
                            }
                        });
                builder1.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        listViewcars.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final CreateUserModel createUserModels = cars.get(position);
                String emails = createUserModels.getId1();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(ViewEmployee.this);
                builder1.setTitle("Do You want to Delete Employee...");
                builder1.setMessage(createUserModels.getName());
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                FirebaseDatabase.getInstance().getReference().child("Accounts").orderByChild("id1").equalTo(createUserModels.getId1()).getRef().addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                            String Key = snapshot.getKey();
                                            //  Toast.makeText(ViewEmployee.this, Key, Toast.LENGTH_SHORT).show();
                                            FirebaseDatabase.getInstance().getReference().child("Accounts").child(Key).child("id1").setValue(null);
                                            FirebaseDatabase.getInstance().getReference().child("Accounts").child(Key).child("name").setValue(null);
                                            FirebaseDatabase.getInstance().getReference().child("Accounts").child(Key).child("pin").setValue(null);
                                            FirebaseDatabase.getInstance().getReference().child("Accounts").child(Key).child("post").setValue(null);
                                            FirebaseDatabase.getInstance().getReference().child("Accounts").child(Key).child("address").setValue(null);
                                            FirebaseDatabase.getInstance().getReference().child("Accounts").child(Key).child("bg").setValue(null);
                                            FirebaseDatabase.getInstance().getReference().child("Accounts").child(Key).child("contactno").setValue(null);
                                            FirebaseDatabase.getInstance().getReference().child("Accounts").child(Key).child("dob").setValue(null);
                                            FirebaseDatabase.getInstance().getReference().child("Accounts").child(Key).child("gender").setValue(null);
                                            startActivity(new Intent(ViewEmployee.this,AdminStart.class));

                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });

                            }
                        });

                builder1.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

                return true;
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
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("Accounts");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cars.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        CreateUserModel car = postSnapshot.getValue(CreateUserModel.class);
                        cars.add(car);

                }
                ViewUserAdapter carsAdapter = new ViewUserAdapter(ViewEmployee.this, cars);
                listViewcars.setAdapter(carsAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        usersRef.addListenerForSingleValueEvent(eventListener);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ViewEmployee.this,AdminStart.class));
    }
}