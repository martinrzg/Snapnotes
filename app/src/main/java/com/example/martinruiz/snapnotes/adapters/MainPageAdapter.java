package com.example.martinruiz.snapnotes.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.martinruiz.snapnotes.fragments.GalleryFragment;
import com.example.martinruiz.snapnotes.fragments.ProfileFragment;
import com.example.martinruiz.snapnotes.fragments.SimpleCameraFragment;

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
                 //CameraFragment cameraFragment = new CameraFragment.newInstance(new Configuration.Builder().setCamera(Configuration.CAMERA_FACE_REAR).build());
                //return cameraFragment;
                //SimpleCameraFragment cameraFragment =
                return SimpleCameraFragment.create();

                //return CustomCameraFragment.create();
            case 2:
                return ProfileFragment.create();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}
