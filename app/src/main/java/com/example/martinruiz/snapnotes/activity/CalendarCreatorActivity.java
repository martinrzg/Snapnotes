package com.example.martinruiz.snapnotes.activity;

import android.app.Dialog;
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
import com.example.martinruiz.snapnotes.views.calendar.Day;

import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalendarCreatorActivity extends AppCompatActivity {

    @BindView(R.id.monday_column) ConstraintLayout mondayCol;
    @BindView(R.id.tuesday_column) ConstraintLayout tuesdayCol;
    @BindView(R.id.wednesday_column) ConstraintLayout wednesdayCol;
    @BindView(R.id.thursday_column) ConstraintLayout thurdayCol;
    @BindView(R.id.friday_column) ConstraintLayout fridayCol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_creator);

        ButterKnife.bind(this);

        Course course = new Course("Nueva materia en esta semana carnal", Day.MONDAY,8, 30,10,15);
        Course course1 = new Course("Historia de México", Day.TUESDAY, 15,15,14,0);
        Course course2 = new Course("Otra materia carnal", Day.WEDNESDAY,12, 05,15,45);
        Course course3 = new Course("Valer verga", Day.FRIDAY,12, 05,15,45);

        addNewCourse(course);
        addNewCourse(course1);
        addNewCourse(course2);
        addNewCourse(course3);

    }

    @OnClick(R.id.monday_column)
    public void addInMonday(){

    }

    private void addNewCourse(Course course){

        ConstraintLayout weekDay;

        switch (course.getDay()){
            case MONDAY: weekDay = mondayCol; break;
            case TUESDAY: weekDay = tuesdayCol; break;
            case WEDNESDAY: weekDay = wednesdayCol; break;
            case THURSDAY: weekDay = thurdayCol; break;
            case FRIDAY: weekDay = fridayCol; break;
            default: weekDay = mondayCol; break;
        }

        int width = DisplayTool.convertDpToPixel(100, this);
        int height = DisplayTool.convertDpToPixel(course.getCellHeight(), this);
        int margin =  DisplayTool.convertDpToPixel(course.getMarginInDp(),this);

        View courseCell = createCell(width, height,course.getName());
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
