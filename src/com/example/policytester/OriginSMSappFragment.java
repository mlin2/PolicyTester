package com.example.policytester;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Moderbord on 2015-12-10.
 */
public class OriginSMSappFragment extends Fragment {

    EditText destinationEditText, messageEditText, frequencyEditText;
    Button button1, button2, button3;

    public final static String EXTRA_MESSAGE = "com.example.policytester.MESSAGE";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.origin_sms_app, container, false);

        destinationEditText = (EditText)rootView.findViewById(R.id.destination);
        messageEditText = (EditText)rootView.findViewById(R.id.message);
        frequencyEditText = (EditText)rootView.findViewById(R.id.frequency);

        button1 = (Button)rootView.findViewById(R.id.button1);
        button2 = (Button)rootView.findViewById(R.id.button2);
        button3 = (Button)rootView.findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sendMessageIMEI(v);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sendMessageNoIMEI(v);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                doInterComponentLeak(v);
            }
        });

        return rootView;
    }


    public void sendMessageIMEI(View view) {
        TelephonyManager telephonyManager = (TelephonyManager)getActivity().getSystemService(Context.TELEPHONY_SERVICE);

        String destination = destinationEditText.getText().toString();

        String message = messageEditText.getText().toString();

        int frequency = Integer.parseInt(frequencyEditText.getText().toString());

        SmsManager smsManager = SmsManager.getDefault();
        for(int i = 0; i < frequency; i++)
            smsManager.sendTextMessage(destination, null, message + telephonyManager.getDeviceId(), null, null);

    }
    public void sendMessageNoIMEI(View view) {

        String destination = destinationEditText.getText().toString();

        String message = messageEditText.getText().toString();

        int frequency = Integer.parseInt(frequencyEditText.getText().toString());

        SmsManager smsManager = SmsManager.getDefault();
        for(int i = 0; i < frequency; i++)
            smsManager.sendTextMessage(destination, null, message, null, null);

    }

    public void doInterComponentLeak(View view) {
        TelephonyManager telephonyManager = (TelephonyManager)getActivity().getSystemService(Context.TELEPHONY_SERVICE);

        String destination = destinationEditText.getText().toString();

        String message = messageEditText.getText().toString();

        int frequency = Integer.parseInt(frequencyEditText.getText().toString());

        Set<String> tmp = new HashSet<String>();
        tmp.add("aaa");
        System.out.println(tmp.toString());

        Intent i = new Intent(getActivity(), DisplayMessageActivity.class);

        i.putExtra("destination", destination);
        i.putExtra("message", message);
        i.putExtra("imei", telephonyManager.getDeviceId());

        startActivity(i);
    }
}