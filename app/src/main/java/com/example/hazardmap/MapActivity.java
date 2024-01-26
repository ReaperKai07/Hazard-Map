package com.example.hazardmap;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.hazardmap.databinding.ActivityMapBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Vector;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapBinding binding;
    MarkerOptions marker;
    LatLng centerlocation;
    Vector<MarkerOptions> markerOptions;
    private String URL ="http://10.20.130.119/ict602/maklumat/all.php";
    RequestQueue requestQueue;
    Gson gson;
    Maklumat[] maklumats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_map);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.bottom_home) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.bottom_map) {
                    return true;
                } else if (item.getItemId() == R.id.bottom_add) {
                    startActivity(new Intent(getApplicationContext(), AddActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.bottom_about) {
                    startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                }
                return false;
            }
        });

        gson = new GsonBuilder().create();
        getCurrentLocation();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        markerOptions = new Vector<>();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        for (MarkerOptions mark : markerOptions){
            mMap.addMarker(mark);
        }

        enableMyLocation();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerlocation, 20));
        sendRequest();
    }

    private void enableMyLocation() {

        String perms[] = {"android.permission.ACCESS_FINE_LOCATION","android.permission.ACCESS_NETWORK_STATE"};
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
                Log.d("Location","Permission Granted");
            }
        } else {
            // Permission to access the location is missing. Show rationale and request permission

            Log.d("Location","Permission Denied");
            ActivityCompat.requestPermissions(this,perms ,200);

        }
    }

    private void getCurrentLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        String provider = locationManager.getBestProvider(criteria, true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);

        if (location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            centerlocation = new LatLng(latitude, longitude);
        }
        else {
            centerlocation = new LatLng(4.7620, 100.9376);
        }
    }

    public void sendRequest() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL,onSuccess,onError);
        requestQueue.add(stringRequest);
    }

    public Response.Listener<String> onSuccess = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            maklumats = gson.fromJson(response, Maklumat[].class);

            Log.d("Maklumat", "Number of Maklumat Data Point : " + maklumats.length);

            if (maklumats.length <1) {
                Toast.makeText(getApplicationContext(),"Problem retrieving JSON data", Toast.LENGTH_LONG).show();
                return;
            }

            for(Maklumat info: maklumats) {
                double lat = Double.parseDouble(info.lat);
                double lng = Double.parseDouble(info.lng);
                String title = info.name;
                String snippet = info.description;

                MarkerOptions marker = new MarkerOptions()
                        //coordinate
                        .position(new LatLng(lat,lng))
                        //title
                        .title(title)
                        //description
                        .snippet(snippet)
                        //market color
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));

                mMap.addMarker(marker);
            }
        }
    };

    public Response.ErrorListener onError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
        }
    };
}