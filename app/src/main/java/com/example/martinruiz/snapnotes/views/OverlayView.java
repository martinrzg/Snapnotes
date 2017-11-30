package com.example.martinruiz.snapnotes.views;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.martinruiz.snapnotes.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MartinRuiz on 11/20/2017.
 */

public class OverlayView extends RelativeLayout {

    @BindView(R.id.imageViewShareButton) ImageView imageViewShareButton;
    private String sharingText;

    public OverlayView(Context context) {
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

        imageViewShareButton.setOnClickListener(view1 -> {
            sendShareIntent();
        });

    }
    public void setShareText(String text) {
        this.sharingText = text;
    }


    private void sendShareIntent() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_HTML_TEXT, sharingText);
        sendIntent.setType("text/plain");
        getContext().startActivity(sendIntent);
    }

}
