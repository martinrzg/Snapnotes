package com.example.martinruiz.snapnotes.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.SparseArray;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

/**
 * Created by MartinRuiz on 11/14/2017.
 */

public class ImageOCR {

    public static String imageToText(Context context, String filePath){
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        TextRecognizer textRecognizer = new TextRecognizer.Builder(context).build();
        if(!textRecognizer.isOperational()){
            Log.e("ERROR","Detector dependencies are not yet available");
        }else {
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock> items = textRecognizer.detect(frame);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < items.size(); i++) {
                TextBlock item = items.valueAt(i);
                stringBuilder.append(item.getValue());
                stringBuilder.append("\n");
            }
            return stringBuilder.toString();
        }

        return "";
    }

}
