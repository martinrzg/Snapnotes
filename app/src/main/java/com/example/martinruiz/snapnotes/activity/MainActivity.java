package com.example.martinruiz.snapnotes.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.martinruiz.snapnotes.R;
import com.example.martinruiz.snapnotes.adapters.DepthPageTransformer;
import com.example.martinruiz.snapnotes.adapters.MainPageAdapter;
import com.example.martinruiz.snapnotes.fragments.GalleryFragment;
import com.example.martinruiz.snapnotes.fragments.SimpleCameraFragment;
import com.example.martinruiz.snapnotes.views.SnapTabs;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA_PERMISSION = 200;

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
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        snapTabs.setViewPager(viewPager);

        checkPermission();
        viewPager.setCurrentItem(1);





        final int blue   = ContextCompat.getColor(this, R.color.light_blue);
        final int purple = ContextCompat.getColor(this, R.color.light_purple);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {

                    SimpleCameraFragment.setBackground(blue,( 1 - positionOffset) );

                    //backgroundView.setBackgroundColor(blue);

                    //backgroundView.setAlpha(1 - positionOffset);


                    //textureView.setBackgroundColor(blue);
                    //textureView.setAlpha(1-positionOffset);
                } else if (position == 1) {

                    SimpleCameraFragment.setBackground(purple, positionOffset);
                    //backgroundView.setBackgroundColor(purple);
                    //backgroundView.setAlpha(positionOffset);

                    //textureView.setBackgroundColor(purple);
                    //textureView.setAlpha(positionOffset);
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

    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CAMERA_PERMISSION);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                // close the app
                Toast.makeText(this, "Sorry!!!, you can't use this app without granting permission", Toast.LENGTH_LONG).show();
                //finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        if(viewPager.getCurrentItem() == 1){
            super.onBackPressed();
        }else if(viewPager.getCurrentItem() == 0){
            viewPager.setCurrentItem(1);
        }else{
            viewPager.setCurrentItem(1);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if(currentUser != null){

        }else{
            Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
