package com.example.martinruiz.snapnotes.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.martinruiz.snapnotes.R;

import butterknife.ButterKnife;

public class CalendarCreatorActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_creator);
        ButterKnife.bind(this);
    }
}
