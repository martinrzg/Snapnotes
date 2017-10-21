package com.example.martinruiz.snapnotes.activity;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.martinruiz.snapnotes.R;
import com.example.martinruiz.snapnotes.adapters.MainPageAdapter;
import com.example.martinruiz.snapnotes.views.SnapTabs;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.view_background) View backgroundView;
    @BindView(R.id.ma_view_pager) ViewPager viewPager;
    @BindView(R.id.snapTabs) SnapTabs snapTabs;
    private MainPageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        adapter = new MainPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        snapTabs.setViewPager(viewPager);
        viewPager.setCurrentItem(1);

        final int blue   = ContextCompat.getColor(this, R.color.light_blue);
        final int purple = ContextCompat.getColor(this, R.color.light_purple);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position == 0){
                    backgroundView.setBackgroundColor(blue);
                    backgroundView.setAlpha(1-positionOffset);
                }
                else if(position == 1){
                    backgroundView.setBackgroundColor(purple);
                    backgroundView.setAlpha(positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

}
