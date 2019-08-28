package com.example.shubham.pnbapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MarkAttendance extends AppCompatActivity {
    Button present, absent;
    String date1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        present=(Button)findViewById(R.id.button10);
        absent = (Button)findViewById(R.id.button11);

        present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLocationEnabled(MarkAttendance.this)) {
                    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    Criteria criteria = new Criteria();
                    String bestProvider = String.valueOf(locationManager.getBestProvider(criteria, true));

                    if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        // Toast.makeText(markatt.this, "GPS is Enabled in your devide", Toast.LENGTH_SHORT).show();
                        if (ActivityCompat.checkSelfPermission(MarkAttendance.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MarkAttendance.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                            return;
                        }
                        Location location = locationManager.getLastKnownLocation(bestProvider);
                        onLocationChanged(location);

                    } else {
                        showGPSDisabledAlertToUser();
                    }

                }
            }
        });

        SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy");
        date1 = sdf.format(new Date());

        absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("Attendance").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(date1).setValue(date1 + " Absent");
                Toast.makeText(getApplicationContext(), "You Have been Marked Absent for Today, i.e. " + date1, Toast.LENGTH_SHORT).show();

            }
        });
    }

    public static boolean isLocationEnabled(Context context)
    {
        //...............
        return true;
    }

    private void showGPSDisabledAlertToUser () {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?")
                .setCancelable(false)
                .setPositiveButton("Goto Settings Page To Enable GPS",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent callGPSSettingIntent = new Intent(
                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(callGPSSettingIntent);
                            }
                        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();


    }
    public void onLocationChanged(Location location) {
        if(location!=null) {
            double currentlat = location.getLatitude();
            double currentlong = location.getLongitude();

            //String lat = String.valueOf(currentlat);
            //String lon = String.valueOf(currentlong);
          //  Toast.makeText(getApplicationContext(), "Button is Working...", Toast.LENGTH_SHORT).show();

            if (distance(28.623531, 77.211879, currentlat, currentlong) < 0.310686) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy");
                String date = sdf.format(new Date());
                Toast.makeText(getApplicationContext(), "You Have been Marked Present for Today, i.e. " + date, Toast.LENGTH_SHORT).show();
                FirebaseDatabase.getInstance().getReference().child("Attendance").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(date).setValue(date + " Present");
            } else {
                Toast.makeText(getApplicationContext(), "Plz Reach the Nearest Location to Marked yourself as present.", Toast.LENGTH_SHORT).show();

            }
        }

    }
    private double distance(double lat, double lon, double currentlat, double currentlong) {

        double earthRadius = 3958.75;
        double dLat = Math.toRadians(currentlat-lat);
        double dLng = Math.toRadians(currentlong-lon);

        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);

        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(currentlat));

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        double dist = earthRadius * c;

        return dist;
    }
}



