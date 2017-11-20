package com.example.martinruiz.snapnotes.fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.martinruiz.snapnotes.DatabaseModel.Courses;
import com.example.martinruiz.snapnotes.DatabaseModel.Days;
import com.example.martinruiz.snapnotes.R;
import com.example.martinruiz.snapnotes.utils.CloudStorageManager;
import com.github.florent37.camerafragment.PreviewActivity;
import com.github.florent37.camerafragment.configuration.Configuration;
import com.github.florent37.camerafragment.internal.ui.BaseAnncaFragment;
import com.github.florent37.camerafragment.listeners.CameraFragmentResultListener;
import com.github.florent37.camerafragment.listeners.CameraFragmentStateListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;
import static com.example.martinruiz.snapnotes.utils.DatabaseCRUD.CALENDAR;

/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleCameraFragment extends BaseAnncaFragment{
    private final String TAG = "CameraFragment";
    public static View backgroundView;
    public static View lineView;

    private StorageReference storageReference ;

    private static SimpleCameraFragment cameraFragment;
    public static int flashIcon ;
    public static int cameraIcon ;

    public SimpleCameraFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingPermission")
    public static SimpleCameraFragment create(){
        Configuration.Builder builder = new Configuration.Builder();
        builder
                .setFlashMode(Configuration.FLASH_MODE_OFF)
                .setCamera(Configuration.CAMERA_FACE_REAR)
                .setMediaAction(Configuration.MEDIA_ACTION_PHOTO);

        flashIcon = R.drawable.ic_flash_off_white_24dp;
        cameraIcon = R.drawable.ic_camera_front_white_24dp;


       // cameraFragment = SimpleCameraFragment.newInstance(builder.build());

        cameraFragment = (SimpleCameraFragment) BaseAnncaFragment.newInstance(new SimpleCameraFragment(), builder.build());

        init();
        return cameraFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("ON CREATE VIEW");
        View view = inflater.inflate(R.layout.simple_camera_layout , container, false);
        backgroundView = view.findViewById(R.id.view_background);
        lineView = view.findViewById(R.id.viewLine);

        //TODO Now we can set a tag...
        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    public static void setBackground(int color, float positionOffset){
        backgroundView.setBackgroundColor(color);
        backgroundView.setAlpha( positionOffset);
        lineView.setAlpha(.25f - positionOffset*.5f);
    }

    public static void init(){

        cameraFragment.setStateListener(new CameraFragmentStateListener() {
            @Override
            public void onCurrentCameraBack() {
                cameraIcon = R.drawable.ic_camera_front_white_24dp;
            }

            @Override
            public void onCurrentCameraFront() {
                cameraIcon = R.drawable.ic_camera_rear_white_24dp;
            }

            @Override
            public void onFlashAuto() {
                flashIcon = R.drawable.ic_flash_auto_white_24dp;

            }

            @Override
            public void onFlashOn() {
                flashIcon = R.drawable.ic_flash_on_white_24dp;

            }

            @Override
            public void onFlashOff() {
                flashIcon = R.drawable.ic_flash_off_white_24dp;
            }

            @Override
            public void onCameraSetupForPhoto() {

            }

            @Override
            public void onCameraSetupForVideo() {

            }

            @Override
            public void onRecordStateVideoReadyForRecord() {

            }

            @Override
            public void onRecordStateVideoInProgress() {

            }

            @Override
            public void onRecordStatePhoto() {

            }

            @Override
            public void shouldRotateControls(int degrees) {

            }

            @Override
            public void onStartVideoRecord(File outputFile) {

            }

            @Override
            public void onStopVideoRecord() {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case PreviewActivity.ACTION_CONFIRM:
                if(resultCode == RESULT_OK){
                    //Toast.makeText(getContext(), "TO UPLOAD", Toast.LENGTH_SHORT).show();
                    String filePath = data.getStringExtra("file_path_arg");
                    getBoardHour(FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                , filePath);
                }
             default:
                 super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private static String createImageFileName() {
        // Create an image file name
        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        //System.out.println("----------------------------->>>>>>>>>>>>>>>>>>>>>"+cameraFragment.getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath());
        return imageFileName;
    }

    public static void takePhoto(){

        cameraFragment.takePhotoOrCaptureVideo(new CameraFragmentResultListener() {
            @Override
            public void onVideoRecorded(String filePath) {
            }
            @Override
            public void onPhotoTaken(byte[] bytes, String filePath) {
                Intent intentPreview  = PreviewActivity.newIntentPhoto(cameraFragment.getActivity(), filePath);
                cameraFragment.startActivityForResult(intentPreview,PreviewActivity.ACTION_CONFIRM);
            }
        }, cameraFragment.getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath()
        , createImageFileName() );
    }

    public static void flashToggle(){
        cameraFragment.toggleFlashMode();
    }
    public static void switchFrontBack(){
        cameraFragment.switchCameraTypeFrontBack();
    }

    public static void getBoardHour(DatabaseReference mDatabase, String filePath)  {
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        SimpleDateFormat day = new SimpleDateFormat("E");
        DateFormat df = new SimpleDateFormat("HH:mm");

        mDatabase.child(CALENDAR).child(day.format(currentTime)).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get board value

                        String name = "";
//                        Log.d("entra","entra");
                        Days days = dataSnapshot.getValue(Days.class);


                        Date currentHour = new Date();
                        try {
                            currentHour = df.parse(format.format(currentTime));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        // [START_EXCLUDE]
                        if (days == null) {

                            // Board is null, error out
                            //Log.e(TAG, "Days is unexpectedly null");
                        } else {

                            // Write new Board
                            for (String key: days.getCourses().keySet()){
                                Courses courses = days.getCourses().get(key);

                                Date startDate = new Date();
                                Date endDate= new Date();
                                try {
                                    startDate = df.parse(courses.getStart());
                                    String newDateString = df.format(startDate);
                                    endDate = df.parse(courses.getEnd());
                                    System.out.println(newDateString);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                Log.d("horas: ", startDate+" "+endDate+""+currentHour );
                                if(currentHour.before(endDate) && currentHour.after(startDate)){
                                    Log.d("hora","si hay: "+courses.getName());
                                    name = courses.getName();
                                }
                            }
                            //TODO: Send name to the
                            CloudStorageManager.uploadImage(filePath, name, cameraFragment.getContext());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //Log.w(TAG, "getPost:onCancelled", databaseError.toException());

                    }
                });
    }



}
