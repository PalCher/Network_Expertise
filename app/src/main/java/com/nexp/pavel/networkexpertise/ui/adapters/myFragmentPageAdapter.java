package com.nexp.pavel.networkexpertise.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class myFragmentPageAdapter extends FragmentPagerAdapter {


    ArrayList<Fragment> fragments = new ArrayList<>();

    public myFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }
    public void addFragment(Fragment fragment){
        this.fragments.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public ArrayList<Fragment> getFragments() {
        return fragments;
    }


}
