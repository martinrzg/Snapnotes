package com.example.martinruiz.snapnotes.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.martinruiz.snapnotes.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.textViewMadeIn)TextView textViewMadeIn;

    public static final String MEXICO = "🇲🇽";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        textViewMadeIn.setText("Made in México "+MEXICO);


    }
}
