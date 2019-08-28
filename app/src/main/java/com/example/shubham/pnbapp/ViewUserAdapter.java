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
 * Created by prashant maheshwari on 24-07-2018.
 */

public class ViewUserAdapter extends ArrayAdapter<CreateUserModel> {

    private Activity context;
    List<CreateUserModel> carList;
    private TextView close;
    FirebaseUser user;

    public ViewUserAdapter(@NonNull Activity context, List<CreateUserModel> carList) {
        super(context, R.layout.viewlayout, carList);
        this.context = context;
        this.carList = carList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        @SuppressLint({"ViewHolder", "InflateParams"})
        View listViewItem = inflater.inflate(R.layout.viewlayout, parent, false);

        TextView textViewCar = (TextView) listViewItem.findViewById(R.id.vname);
        TextView textView = (TextView)listViewItem.findViewById(R.id.mobile);


        final CreateUserModel car1 = carList.get(position);
        textViewCar.setText(car1.getName());
        textView.setText(car1.getContactno());

        return listViewItem;
    }
}
