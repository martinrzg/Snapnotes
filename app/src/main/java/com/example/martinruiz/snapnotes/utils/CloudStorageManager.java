package com.example.martinruiz.snapnotes.utils;

import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

/**
 * Created by MartinRuiz on 11/13/2017.
 */

public class CloudStorageManager {

    private static FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    private static StorageReference storageReference = firebaseStorage.getReference();
    private static String firebaseUserID;
    private static StorageReference imagesRef ;
    public static void uploadImage(String path, String folder){
        try{
            Uri file = Uri.fromFile(new File(path));
            firebaseUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            imagesRef = storageReference.child(firebaseUserID+"/images/"+folder+"/"+file.getLastPathSegment());
            UploadTask uploadTask = imagesRef.putFile(file);
            uploadTask
                    .addOnSuccessListener(taskSnapshot -> System.out.println("-------------------------------------->SUCESSS"))
                    .addOnFailureListener(e -> System.out.println("-------------------------------------->FAILED"));
        }catch (Exception e){
            System.out.println(e);
        }
    }





}
