package com.example.shubham.pnbapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

import java.util.List;

/**
 * Created by prashant maheshwari on 20-07-2018.
 */

public class RequestData extends ArrayAdapter<Mymodel>{

    private Activity context;
    List<Mymodel> carList;
    private TextView close;
    FirebaseUser user;

    public RequestData(@NonNull Activity context, List<Mymodel> carList) {
        super(context, R.layout.requestlayout, carList);
        this.context = context;
        this.carList = carList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        @SuppressLint({"ViewHolder", "InflateParams"})
        View listViewItem = inflater.inflate(R.layout.requestlayout, parent, false);

        TextView textViewCar = (TextView) listViewItem.findViewById(R.id.text1);
        TextView textViewFuel = (TextView) listViewItem.findViewById(R.id.text2);
        TextView textViewRegistration = (TextView) listViewItem.findViewById(R.id.text3);

        final Mymodel car1 = carList.get(position);
        textViewCar.setText(car1.getFrom1()+"-"+car1.getTo());
        textViewFuel.setText(car1.getFrom());
        textViewRegistration.setText(car1.getReason());
        return listViewItem;
    }
}
