package com.example.policytester;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Moderbord on 2015-12-10.
 */

public class ExtendedFragment extends Fragment {

    private static final String LOGGEY = "Funkey";
    Button locationButton, logButton;
    TextView longView, latView, logView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.extended_fragment, container, false);

        locationButton = (Button) rootView.findViewById(R.id.locationButton);
        logButton = (Button) rootView.findViewById(R.id.logButton);
        longView = (TextView) rootView.findViewById(R.id.longView);
        latView = (TextView) rootView.findViewById(R.id.latView);
        logView = (TextView) rootView.findViewById(R.id.logView);

        // Adding a button that will use data that is should not be allowed to use after applying the policy
        locationButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getLocation(v);
            }
        });

        logButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logMe(v);
            }
        });

        return rootView;
    }

    public void getLocation(View view) {
        String bestProvider;
        Criteria criteria;

        LocationManager locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();
        bestProvider = String.valueOf(locationManager.getBestProvider(criteria, true));

        Location location = new Location(bestProvider);
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        System.out.println(latitude);
        System.out.println(longitude);

        latView.setText("Lat: " + Double.toString(latitude));
        longView.setText("Long: "+Double.toString(longitude));
    }

    public void logMe(View view){
        Log.d(LOGGEY, "Uptown funk you up?");
        logView.setText("Inhibit you must");
    }
}