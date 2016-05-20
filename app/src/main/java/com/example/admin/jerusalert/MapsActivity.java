package com.example.admin.jerusalert;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int PERMISSION_REQUEST_CODE = 0x1243;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        myLocation();
        ArrayList<ReportObj> reportList = new ArrayList<ReportObj>();
        ReportObj obj1 = new ReportObj();
        ReportObj obj2 = new ReportObj();
        ReportObj obj3 = new ReportObj();

        obj1.Location_x = 31.787120;
        obj1.Location_y = 35.201333;

        obj2.Location_x = 31.787291;
        obj2.Location_y = 35.201140;

        obj3.Location_x = 31.786735;
        obj3.Location_y = 35.201225;

        reportList.add(obj1);
        reportList.add(obj2);
        reportList.add(obj3);
        markReports(reportList);
    }

    public void markReports(ArrayList<ReportObj> reportList){
        for (int i=0 ; i <reportList.size() ; i++){
            LatLng mylocation = new LatLng(reportList.get(i).Location_x,reportList.get(i).Location_y);
            mMap.addMarker(new MarkerOptions().position(mylocation));
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        // LatLng sydney = new LatLng(-34, 151);

         mMap.setMyLocationEnabled(true);


//        Location myLocation = googleMap.getMyLocation();
//        double lat = myLocation.getLatitude();
//        double lng = myLocation.getLongitude();
//        LatLng location = new LatLng(lat,lng);

    }
    public void myLocation() {

        // Get the location manager
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the locatioin provider -> use
        // default
        Criteria c = new Criteria();
        c.setAccuracy(Criteria.ACCURACY_COARSE);
        String provider = locationManager.getBestProvider(c, false);
        Location location = locationManager.getLastKnownLocation(provider);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 400, 10, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                LatLng mylocation = new LatLng(location.getLatitude(),location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(mylocation).title("I'm Here"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(mylocation));
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
                Log.d("a", "d");

            }

            @Override
            public void onProviderEnabled(String s) {
                Log.d("a", "c");

            }

            @Override
            public void onProviderDisabled(String s) {
                Log.d("a", "b");

            }
        });
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 400, 10, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                LatLng mylocation = new LatLng(location.getLatitude(),location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(mylocation).title("I'm Here"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(mylocation));
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
                Log.d("a", "d");

            }

            @Override
            public void onProviderEnabled(String s) {
                Log.d("a", "c");
            }

            @Override
            public void onProviderDisabled(String s) {
                Log.d("a", "b");
            }

        });


    }


}

