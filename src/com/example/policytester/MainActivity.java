package com.example.policytester;

import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.policytester.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Adding a button that will use data that is should not be allowed to use after applying the policy
		Button locationButton = (Button) findViewById(R.id.locationButton);
		locationButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getLocation(v);
			}
		});

	}

	public void sendMessageIMEI(View view) {
		TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		 
		EditText destiantionEditText = (EditText) findViewById(R.id.destination);
		String destination = destiantionEditText.getText().toString();
		
		EditText messageEditText = (EditText) findViewById(R.id.message);
		String message = messageEditText.getText().toString();
		
		EditText frequencyEditText = (EditText) findViewById(R.id.frequency);
		int frequency = Integer.parseInt(frequencyEditText.getText().toString());
		
		SmsManager smsManager = SmsManager.getDefault();
		for(int i = 0; i < frequency; i++)
			smsManager.sendTextMessage(destination, null, message + telephonyManager.getDeviceId(), null, null);
	
	}
	public void sendMessageNoIMEI(View view) {
		
		EditText destiantionEditText = (EditText) findViewById(R.id.destination);
		String destination = destiantionEditText.getText().toString();
		
		EditText messageEditText = (EditText) findViewById(R.id.message);
		String message = messageEditText.getText().toString();
		
		EditText frequencyEditText = (EditText) findViewById(R.id.frequency);
		int frequency = Integer.parseInt(frequencyEditText.getText().toString());
		
		SmsManager smsManager = SmsManager.getDefault();
		for(int i = 0; i < frequency; i++)
			smsManager.sendTextMessage(destination, null, message, null, null);
		
	}
	
	public void doInterComponentLeak(View view) {
		TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		
		EditText destinationEditText = (EditText) findViewById(R.id.destination);
		String destination = destinationEditText.getText().toString();
		
		EditText messageEditText = (EditText) findViewById(R.id.message);
		String message = messageEditText.getText().toString();
		
		EditText frequencyEditText = (EditText) findViewById(R.id.frequency);
		int frequency = Integer.parseInt(frequencyEditText.getText().toString());
		
		Set<String> tmp = new HashSet<String>();
		tmp.add("aaa");
		System.out.println(tmp.toString());
		
		Intent i = new Intent(this, DisplayMessageActivity.class);
		
		i.putExtra("destination", destination);
		i.putExtra("message", message);
		i.putExtra("imei", telephonyManager.getDeviceId());
		
		startActivity(i); 
	}
	
	public void getLocation(View view) {
		String bestProvider;
		Criteria criteria;

		LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		criteria = new Criteria();
		bestProvider = String.valueOf(locationManager.getBestProvider(criteria, true)).toString();

		Location location = new Location(bestProvider);
		double latitude = location.getLatitude();
		double longitude = location.getLongitude();
		System.out.println(latitude);
		System.out.println(longitude);

	}
	

}
