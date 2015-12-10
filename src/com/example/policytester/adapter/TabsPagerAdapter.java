package com.example.policytester.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.policytester.ExtendedFragment;
import com.example.policytester.ExtendedPPFragment;
import com.example.policytester.OriginSMSappFragment;


/**
 * Created by Moderbord on 2015-12-10.
 */

public class TabsPagerAdapter extends FragmentPagerAdapter{

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case 0:
                // Returns the first tab
                return new OriginSMSappFragment();
            case 1:
                // Returns the second tab
                return new ExtendedFragment();
            case 2:
                // Returns the third tab
                return new ExtendedPPFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        // Return the number of tabs
        return 3;
    }
}
