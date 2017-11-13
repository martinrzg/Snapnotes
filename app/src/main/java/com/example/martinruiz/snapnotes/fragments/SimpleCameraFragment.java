package com.example.martinruiz.snapnotes.fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.example.martinruiz.snapnotes.R;
import com.github.florent37.camerafragment.CameraFragment;
import com.github.florent37.camerafragment.PreviewActivity;
import com.github.florent37.camerafragment.configuration.Configuration;
import com.github.florent37.camerafragment.listeners.CameraFragmentResultListener;
import com.github.florent37.camerafragment.listeners.CameraFragmentStateListener;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleCameraFragment extends Fragment {
    private final String TAG = "CameraFragment";
    private static final int REQUEST_PREVIEW_CODE = 300;



    private ImageView imageViewCameraButton;
    private static CameraFragment cameraFragment;
    public static int flashIcon ;
    public static int cameraIcon ;

    public SimpleCameraFragment() {
        // Required empty public constructor
    }




    @SuppressLint("MissingPermission")
    public static CameraFragment create(){
        Configuration.Builder builder = new Configuration.Builder();
        builder
                .setFlashMode(Configuration.FLASH_MODE_OFF)
                .setCamera(Configuration.CAMERA_FACE_REAR)
                .setMediaAction(Configuration.MEDIA_ACTION_PHOTO);

        flashIcon = R.drawable.ic_flash_off_white_24dp;
        cameraIcon = R.drawable.ic_camera_front_white_24dp;

        cameraFragment = CameraFragment.newInstance(builder.build());
        init();
        return cameraFragment;
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

        cameraFragment.setResultListener(new CameraFragmentResultListener() {
            @Override
            public void onVideoRecorded(String filePath) {

            }

            @Override
            public void onPhotoTaken(byte[] bytes, String filePath) {
                Intent intentPreview  = PreviewActivity.newIntentPhoto(cameraFragment.getContext(), filePath);
                cameraFragment.startActivityForResult(intentPreview,REQUEST_PREVIEW_CODE);
            }
        });
    }

    private static String createImageFileName() {
        // Create an image file name
        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        System.out.println("----------------------------->>>>>>>>>>>>>>>>>>>>>"+cameraFragment.getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath());
        return imageFileName;
    }



    public static void takePhoto(){
        //System.out.println("TAKING PHOTOOOOOOOOOOOOOOOOOOo");
        cameraFragment.takePhotoOrCaptureVideo(new CameraFragmentResultListener() {
            @Override
            public void onVideoRecorded(String filePath) {

            }

            @Override
            public void onPhotoTaken(byte[] bytes, String filePath) {

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



}