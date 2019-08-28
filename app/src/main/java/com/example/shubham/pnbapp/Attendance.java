package com.example.shubham.pnbapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Attendance extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference myref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        String Key = intent.getStringExtra("My");
        if(Key==null) {
            myref = FirebaseDatabase.getInstance().getReference().child("Attendance").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        }else{
            myref = FirebaseDatabase.getInstance().getReference().child("Attendance").child(Key);
        }
        FirebaseRecyclerAdapter<String,BlogViewHolder> recyclerAdapter=new FirebaseRecyclerAdapter<String, BlogViewHolder>(
                String.class,
                R.layout.layout,
                BlogViewHolder.class,
                myref
        ) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, String model, int position) {
                if(model.contains("Present")) {
                    viewHolder.setTitle(model);
                    viewHolder.setColor("Green");
                }else{
                    viewHolder.setColor("Red");
                    viewHolder.setTitle(model);
                }
            }
        };
        recyclerView.setAdapter(recyclerAdapter);
    }
    public static class BlogViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView textView_title;
        public BlogViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            textView_title = (TextView)itemView.findViewById(R.id.att);
        }
        public void setTitle(String title)
        {
            textView_title.setText(title);
        }
        public void setColor(String text){
            if(text.equals("Red")){
                textView_title.setBackgroundColor(Color.RED);
            }else{
                textView_title.setBackgroundColor(Color.GREEN);
            }

        }
    }
}
