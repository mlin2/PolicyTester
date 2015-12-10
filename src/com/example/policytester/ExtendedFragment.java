package com.example.policytester;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Moderbord on 2015-12-10.
 */

public class ExtendedFragment extends Fragment {

    Button locationButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.extended_fragment, container, false);

       locationButton = (Button) rootView.findViewById(R.id.locationButton);

        return rootView;
    }

    public void getLocation(View view) {
        String bestProvider;
        Criteria criteria;

        LocationManager locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();
        bestProvider = String.valueOf(locationManager.getBestProvider(criteria, true)).toString();

        Location location = new Location(bestProvider);
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        System.out.println(latitude);
        System.out.println(longitude);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Adding a button that will use data that is should not be allowed to use after applying the policy
        locationButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getLocation(v);
            }
        });

    }
}