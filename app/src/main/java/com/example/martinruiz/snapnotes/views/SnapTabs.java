package com.example.martinruiz.snapnotes.views;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.martinruiz.snapnotes.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MartinRuiz on 10/19/2017.
 */

public class SnapTabs extends FrameLayout implements ViewPager.OnPageChangeListener {

    @BindView(R.id.imageViewCameraButton)ImageView imageViewCameraButton;
    @BindView(R.id.imageViewGalleryButton)ImageView imageViewGalleryButton;
    @BindView(R.id.imageViewProfileButton)ImageView imageViewProfileButton;
    @BindView(R.id.viewPageIndicator) View viewPageIndicator;

    private int center, side, distanceTranslationX, indicatorTranslationX, distanceTranslationY;
    private ArgbEvaluator argbEvaluator;

    public SnapTabs(@NonNull Context context) {
        this(context, null);
    }

    public SnapTabs(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0 );
    }

    public SnapTabs(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_snap_tabs,this, true);
        ButterKnife.bind(view,this);

        argbEvaluator = new ArgbEvaluator();
        center = ContextCompat.getColor(getContext(),R.color.white);
        side = ContextCompat.getColor(getContext(),R.color.light_grey);
        indicatorTranslationX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 88, getResources().getDisplayMetrics());


        imageViewCameraButton.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                distanceTranslationX = (int) ( (Math.abs(imageViewProfileButton.getTranslationX() - imageViewGalleryButton.getTranslationX())) + indicatorTranslationX);
                System.out.println(distanceTranslationX);
                imageViewCameraButton.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                distanceTranslationY = getHeight() - viewPageIndicator.getBottom();
            }
        });

    }

    public void setViewPager(ViewPager viewPager){
        viewPager.addOnPageChangeListener(this);
        imageViewGalleryButton.setOnClickListener((view) ->{
            if(viewPager.getCurrentItem() != 0){
                viewPager.setCurrentItem(0);
            }
        });
        imageViewProfileButton.setOnClickListener(view ->{
            if(viewPager.getCurrentItem()!=2){
                viewPager.setCurrentItem(2);
            }
        });
        /*imageViewCameraButton.setOnClickListener(view -> {

            if(viewPager.getCurrentItem() != 1){
                viewPager.setCurrentItem(1);
            }
        });*/
    }

    private void animateElements (float percentFromCenter){
        float scale = .8f +  ( (1-percentFromCenter)*.2f);

        imageViewGalleryButton.setTranslationX( percentFromCenter  * distanceTranslationX);
        imageViewGalleryButton.setScaleX(scale);
        imageViewGalleryButton.setScaleY(scale);
        imageViewProfileButton.setTranslationX( -percentFromCenter * distanceTranslationX);
        imageViewProfileButton.setScaleX(scale);
        imageViewProfileButton.setScaleY(scale);

        viewPageIndicator.setAlpha(percentFromCenter);
        viewPageIndicator.setScaleX(percentFromCenter);
    }
    private void animateCamera(float percent  ){
          float scale = .75f +  ( (1-percent)*.25f);
          imageViewCameraButton.setScaleX(scale);
          imageViewCameraButton.setScaleY(scale);
          imageViewCameraButton.setTranslationY(distanceTranslationY * percent);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if(position == 0){
            setColor(1 - positionOffset);
            animateElements(1 - positionOffset);
            animateCamera(1-positionOffset);
            viewPageIndicator.setTranslationX((positionOffset - 1) * indicatorTranslationX);
        }else if(position == 1){
            setColor(positionOffset);
            animateElements(positionOffset);
            animateCamera(positionOffset);
            viewPageIndicator.setTranslationX(positionOffset * indicatorTranslationX);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setColor (float percent){
        int color = (int) argbEvaluator.evaluate(percent, center, side);

        imageViewCameraButton.setColorFilter(color);
        imageViewProfileButton.setColorFilter(color);
        imageViewGalleryButton.setColorFilter(color);
    }

}
