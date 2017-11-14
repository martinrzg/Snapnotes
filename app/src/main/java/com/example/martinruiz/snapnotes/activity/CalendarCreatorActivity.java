package com.example.martinruiz.snapnotes.activity;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.martinruiz.snapnotes.R;
import com.example.martinruiz.snapnotes.util.DisplayTool;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalendarCreatorActivity extends AppCompatActivity {

    @BindView(R.id.monday_container) ConstraintLayout mondayCol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_creator);

        ButterKnife.bind(this);

        TextView test = new TextView(this);
        test.setText("Holi");
        ConstraintSet constraints = new ConstraintSet();

        mondayCol.addView(test);

        constraints.clone(mondayCol);
        constraints.connect(test.getId(), ConstraintSet.TOP, mondayCol.getId(), ConstraintSet.TOP, DisplayTool.convertDpToPixel(60,this));
        constraints.applyTo(mondayCol);
    }
}
