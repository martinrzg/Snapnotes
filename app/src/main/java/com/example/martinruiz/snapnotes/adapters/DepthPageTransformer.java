package com.example.martinruiz.snapnotes.adapters;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.martinruiz.snapnotes.R;

/**
 * Created by MartinRuiz on 11/15/2017.
 */

public class DepthPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.75f;

    /*public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0);

        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when moving to the left page
            //view.setAlpha(1);
            //view.setTranslationX(0);
            //view.setScaleX(1);
            //view.setScaleY(1);
            //float currentTranslation = - position * view.getWidth();
            //view.setTranslationX(currentTranslation);
            //view.setTranslationZ(-1);

        } else if (position <= 1) { // (0,1]
            // Fade the page out.
            float currentTranslation = - position * view.getWidth();
            view.setTranslationX(currentTranslation);
            view.setTranslationZ(-1);

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);
        }
    }*/

    @Override
    public void transformPage(View view, float position) {
        int tagID;
        try{
            tagID = (int) view.getTag();
        }catch (Exception e){
            tagID  = 0;
        }
        System.out.println(tagID);

        if(tagID == 0){
            float currentTranslation = - position * view.getWidth();
            view.setTranslationX( currentTranslation );
            view.setTranslationZ( -10);
        } else {
            view.getRootView().findViewById(R.id.view_background).setTranslationZ(100);
            view.setTranslationZ( +10);
        }


    }
}
