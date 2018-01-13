package com.example.martinruiz.snapnotes.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import com.example.martinruiz.snapnotes.DatabaseModel.Notes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

/**
 * Created by MartinRuiz on 11/13/2017.
 */

public class CloudStorageManager {

    private static FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    private static String firebaseUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();

    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(firebaseUserID);
    private static StorageReference storageReference = firebaseStorage.getReference();
    private static StorageReference imagesRef ;


    public static void uploadImage(String path, String folder, Context context){

        try{
            Uri file = Uri.fromFile(new File(path));
            String textRecognize = ImageOCR.imageToText(context,path);
            imagesRef = storageReference.child(firebaseUserID+"/images/"+folder+"/"+file.getLastPathSegment());
            UploadTask uploadTask = imagesRef.putFile(file);
            uploadTask
                    .addOnSuccessListener(taskSnapshot -> {
                        String id = file.getLastPathSegment().substring(0, (file.getLastPathSegment().length() - 4));
                        Notes note = new Notes(id , taskSnapshot.getDownloadUrl().toString(), textRecognize);
                        DatabaseCRUD.writeNewNote( databaseReference, note, folder );
                    })
                    .addOnFailureListener(e -> System.out.println("-------------------------------------->FAILED"));
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
