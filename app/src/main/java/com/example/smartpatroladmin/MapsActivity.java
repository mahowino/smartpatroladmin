package com.example.smartpatroladmin;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.smartpatroladmin.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    DocumentReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db=FirebaseFirestore.getInstance().collection("Patrols").document("JvSWtCdH3Vv2kLJCWuVr");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        LatLng ruai = new LatLng(-1.259080, 37.010280);
        LatLng utawala = new LatLng(-1.286330, 36.967251);
        LatLng embakasi = new LatLng(-1.310500, 36.914661);

        mMap.addMarker(new MarkerOptions().position(ruai).title("Marker in Ruai"));
        mMap.addMarker(new MarkerOptions().position(utawala).title("Marker in Utawala"));
        mMap.addMarker(new MarkerOptions().position(embakasi).title("Marker in Embakasi"));
        /*LatLngBounds.Builder builder = LatLngBounds.builder()
                .include(ruai)
                .include(utawala)
                .include(embakasi);
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 100));*/
        mMap.addPolyline((new PolylineOptions()).add(ruai, utawala, embakasi).
                        width(5)
                .color(Color.BLACK)
                .geodesic(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ruai, 13));

    }
}