package com.example.martinruiz.snapnotes.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.martinruiz.snapnotes.fragments.CameraFragment;
import com.example.martinruiz.snapnotes.fragments.GalleryFragment;
import com.example.martinruiz.snapnotes.fragments.ProfileFragment;

/**
 * Created by MartinRuiz on 10/17/2017.
 */

public class MainPageAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 3;


    public MainPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return GalleryFragment.create();
            case 1:
                return CameraFragment.create();
            case 2:
                return ProfileFragment.create();
        }
        return null;
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}
