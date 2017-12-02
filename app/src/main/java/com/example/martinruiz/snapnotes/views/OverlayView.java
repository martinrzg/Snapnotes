package com.example.martinruiz.snapnotes.views;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.martinruiz.snapnotes.DatabaseModel.Notes;
import com.example.martinruiz.snapnotes.R;
import com.example.martinruiz.snapnotes.utils.DatabaseCRUD;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MartinRuiz on 11/20/2017.
 */

public class OverlayView extends RelativeLayout {

    @BindView(R.id.imageViewShareButton) ImageView imageViewShareButton;
    @BindView(R.id.imageViewInfoButton) ImageView imageViewInfoButton;
    @BindView(R.id.imageViewDeleteButton) ImageView imageViewDeleteButton;
    private String sharingText;
    private String recognizeText;
    private Notes currentNote;
    private String boardID;
    private DatabaseReference databaseReference;


    public OverlayView(Context context ) {
        super(context);
        init();
    }

    public OverlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OverlayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        View view = inflate(getContext(), R.layout.image_overlay, this);

        ButterKnife.bind(this,view);
        databaseReference = FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        //Toast.makeText(getContext(), "POS: "+position, Toast.LENGTH_SHORT).show();

        imageViewInfoButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
                builder.setTitle("Text recognized");
                builder.setMessage(currentNote.getText());
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

        imageViewShareButton.setOnClickListener(view1 -> {
            sendShareIntent();
        });
        imageViewDeleteButton.setOnClickListener(view2 -> {
            DatabaseCRUD.deleteNote(databaseReference, boardID, currentNote.getId() );
        });

    }
    public void setShareText(String text) {
        this.sharingText = text;
    }

    public void setRecognizeText(String recognizeText) {
        this.recognizeText = recognizeText;
    }

    public void setCurrentNote(Notes currentNote) {
        this.currentNote = currentNote;
    }

    public void setBoardID(String boardID) {
        this.boardID = boardID;
    }

    private void sendShareIntent() {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/html");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, sharingText);
        getContext().startActivity(Intent.createChooser(sharingIntent, "Share using"));
    }

}
