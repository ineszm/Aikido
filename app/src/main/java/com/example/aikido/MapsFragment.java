package com.example.aikido;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MapsFragment extends Fragment {
    Button button;
    GoogleMap mMap;


    public MapsFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_maps, container, false);
        button=view.findViewById(R.id.btnshow);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT>=28)
                {
                    if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                            &&ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
                    {
                        if(shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION))
                        {
                            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
                            return;
                        }
                        return;
                    }
                }

                SupportMapFragment mapFragment= (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.frg);
                mapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        mMap=googleMap;

                        //hne tsir el consommation mta3 el web service ngibou les adress mta3 les salle
                        Ion.with(getActivity())
                                .load("http://192.168.1.8/Aikido/GetMap.php")
                                .asJsonArray()
                                .setCallback(new FutureCallback<JsonArray>() {
                                    @Override
                                    public void onCompleted(Exception e, JsonArray result) {
                                        if(e!=null)
                                        {
                                            Toast.makeText(getActivity(), "ERROR "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {

                                            Gson gson=new Gson();
                                            Salle [] salle=gson.fromJson(result.toString(),Salle [].class);
                                            ArrayList<Salle> s1=new ArrayList<>();
                                            for(Salle row:salle)
                                            {
                                                s1.add(row);

                                            }
                                            for(int i=0;i<s1.size();i++)
                                            {
                                                double lat= Double.parseDouble(s1.get(i).getLatitude());
                                                double log = Double.parseDouble(s1.get(i).getLongitude());
                                                String country =s1.get(i).getAdress_s();
                                                LatLng localisation=new LatLng(lat,log);

                                                CameraPosition cameraPosition=CameraPosition.builder()
                                                        .target(localisation)
                                                        .zoom(10).build();
                                                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),1000,null);

                                                mMap.addMarker(new MarkerOptions().position(localisation).title(country)).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));//creation mta3 el marker

                                            }


                                        }

                                    }
                                });
                    }
                });
            }
        });

        return view;
    }




}
