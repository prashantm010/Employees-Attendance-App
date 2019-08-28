package com.example.shubham.pnbapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.GregorianCalendar;


public class Holiday extends AppCompatActivity {


    public GregorianCalendar cal_month, cal_month_copy;
    private HwAdapter hwAdapter;
    private TextView tv_month;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holiday);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        HomeCollection.date_collection_arr=new ArrayList<HomeCollection>();


        /*FirebaseDatabase.getInstance().getReference().child("Events").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    String date = dataSnapshot1.child("date").getValue().toString();
                    String name = dataSnapshot1.child("name").getValue().toString();
                    String subject = dataSnapshot1.child("subject").getValue().toString();
                    String description = dataSnapshot1.child("description").getValue().toString();
                    HomeCollection.date_collection_arr.add(new HomeCollection(date,name,subject,description));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
        HomeCollection.date_collection_arr.add( new HomeCollection("2018-07-08" ,"Diwali","Holiday","this is holiday"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2018-07-18" ,"Holi","Holiday","this is holiday"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2018-07-28" ,"Statehood Day","Holiday","this is holiday"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2018-08-08" ,"Republic Unian","Holiday","this is holiday"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2018-07-09" ,"ABC","Holiday","this is holiday"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2018-06-15" ,"demo","Holiday","this is holiday"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2018-09-26" ,"weekly off","Holiday","this is holiday"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2018-01-08" ,"Events","Holiday","this is holiday"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2018-01-16" ,"Dasahara","Holiday","this is holiday"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2018-02-09" ,"Christmas","Holiday","this is holiday"));



        cal_month = (GregorianCalendar) GregorianCalendar.getInstance();
        cal_month_copy = (GregorianCalendar) cal_month.clone();
        hwAdapter = new HwAdapter(this, cal_month,HomeCollection.date_collection_arr);

        tv_month = (TextView) findViewById(R.id.tv_month);
        tv_month.setText(android.text.format.DateFormat.format("MMMM yyyy", cal_month));


        GridView gridview = (GridView) findViewById(R.id.gv_calendar);
        gridview.setAdapter(hwAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String selectedGridDate = HwAdapter.day_string.get(position);
                ((HwAdapter) parent.getAdapter()).getPositionList(selectedGridDate, Holiday.this);
            }

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(),"This will show Holidays of Current Month Only.",Toast.LENGTH_SHORT).show();
    }
}