package com.example.martinruiz.snapnotes.activity;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.martinruiz.snapnotes.R;
import com.example.martinruiz.snapnotes.util.DisplayTool;
import com.example.martinruiz.snapnotes.views.calendar.Course;

import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalendarCreatorActivity extends AppCompatActivity {

    @BindView(R.id.monday_container) ConstraintLayout mondayCol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_creator);

        ButterKnife.bind(this);

        addNewCourse(mondayCol);
    }

    private void addNewCourse(ConstraintLayout weekDay){

        // Retrive Course Data
        //Course course = new Course("Nueva materia en esta semana carnal", )

        int width = DisplayTool.convertDpToPixel(100, this);
        int height = DisplayTool.convertDpToPixel(50, this);
        int margin =  DisplayTool.convertDpToPixel(42,this);

        View courseCell = createCell(width, height, "Nueva materia en esta semana carnal");
        ConstraintSet constraints = new ConstraintSet();
        weekDay.addView(courseCell);

        constraints.clone(weekDay);
        constraints.connect(courseCell.getId(), ConstraintSet.TOP, weekDay.getId(), ConstraintSet.TOP, margin);
        constraints.applyTo(weekDay);
    }

    private View createCell(int width, int height, String course){
        // Cell width - 100
        // Cell height - 50

        // Create Layout from xml file
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayoutCompat cell = (LinearLayoutCompat) inflater.inflate(R.layout.calendar_cell, null);

        // Set content in cell
        TextView content = (TextView) cell.findViewById(R.id.cell_content);
        content.setText(course);

        // Set cell dimensions
        cell.setLayoutParams(new LinearLayout.LayoutParams(width,height));

        return cell;
    }

}
