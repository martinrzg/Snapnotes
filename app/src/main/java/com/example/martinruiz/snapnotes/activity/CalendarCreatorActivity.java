package com.example.martinruiz.snapnotes.activity;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentManagerNonConfig;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Layout;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.martinruiz.snapnotes.R;
import com.example.martinruiz.snapnotes.fragments.CourseDialogFragment;
import com.example.martinruiz.snapnotes.util.DisplayTool;
import com.example.martinruiz.snapnotes.views.calendar.Course;
import com.example.martinruiz.snapnotes.views.calendar.Day;

import java.util.ArrayList;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalendarCreatorActivity extends FragmentActivity implements CourseDialogFragment.DialogListener{

    @BindView(R.id.monday_column) ConstraintLayout mondayCol;
    @BindView(R.id.tuesday_column) ConstraintLayout tuesdayCol;
    @BindView(R.id.wednesday_column) ConstraintLayout wednesdayCol;
    @BindView(R.id.thursday_column) ConstraintLayout thursdayCol;
    @BindView(R.id.friday_column) ConstraintLayout fridayCol;
    @BindView(R.id.saturday_column) ConstraintLayout saturdayCol;
    @BindView(R.id.sunday_column) ConstraintLayout sundayCol;

    @BindView(R.id.col_hours) ConstraintLayout hoursColumn;

    private View selectedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_creator);
        ButterKnife.bind(this);

        // Create row titles
        constructHoursColumn();


        Course course = new Course("Nueva materia para esta semana hola", Day.MONDAY, "8:30", "10:15");
        Course course1 = new Course("Historia de México", Day.MONDAY, "15:15", "16:00");
        Course course2 = new Course("Otra materia carnal", Day.MONDAY, "01:00", "02:00");

        addNewCourse(course);
        addNewCourse(course1);
        addNewCourse(course2);

    }

    public void showDialogWithDay(Day day){

        Course course = new Course(
                "",
                day,
                "00:00",
                "00:00"
                );
        CourseDialogFragment dialog = CourseDialogFragment.newInstance(course, true);
        dialog.show(getFragmentManager(), "1");
    }

    @OnClick(R.id.mon_col_add)
    public void addInMonday(){
        showDialogWithDay(Day.MONDAY);
    }

    @OnClick(R.id.tue_col_add)
    public void addInTuesday(){
        showDialogWithDay(Day.TUESDAY);
    }

    @OnClick(R.id.wed_col_add)
    public void addInWednesday(){
        showDialogWithDay(Day.WEDNESDAY);
    }

    @OnClick(R.id.thu_col_add)
    public void addInThursday(){
        showDialogWithDay(Day.THURSDAY);
    }

    @OnClick(R.id.fri_col_add)
    public void addInFriday(){
        showDialogWithDay(Day.FRIDAY);
    }

    @OnClick(R.id.sat_col_add)
    public void addInSaturday(){
        showDialogWithDay(Day.SATURDAY);
    }

    @OnClick(R.id.sun_col_add)
    public void addInSunday(){
        showDialogWithDay(Day.SUNDAY);
    }


    private ConstraintLayout getColumn(Course course){
        ConstraintLayout weekDay;

        switch (course.getDay()){
            case MONDAY: weekDay = mondayCol; break;
            case TUESDAY: weekDay = tuesdayCol; break;
            case WEDNESDAY: weekDay = wednesdayCol; break;
            case THURSDAY: weekDay = thursdayCol; break;
            case FRIDAY: weekDay = fridayCol; break;
            case SATURDAY: weekDay = saturdayCol; break;
            case SUNDAY: weekDay = sundayCol; break;
            default: weekDay = mondayCol; break;
        }

        return weekDay;
    }

    private void addNewCourse(Course course){

        ConstraintLayout weekCol = getColumn(course);
        int gap = DisplayTool.convertDpToPixel(12,this);

        int width = DisplayTool.convertDpToPixel(100, this);
        int height = DisplayTool.convertDpToPixel(course.getCellHeight(), this);
        int margin =  DisplayTool.convertDpToPixel(course.getMarginInDp(),this);

        View courseCell = createCell(width-gap, height, R.layout.calendar_cell);

        courseCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Show Dialog to be edited
                CourseDialogFragment dialog = CourseDialogFragment.newInstance(course, false);
                dialog.show(getFragmentManager(),"1");

                selectedView = view;
            }
        });

        // Set content in cell
        TextView content = (TextView) courseCell.findViewById(R.id.cell_content);
        content.setText(course.getName());

        int color = course.getDay().isOdd() ? R.color.odd_day : R.color.even_day;

        courseCell.setBackgroundColor(getResources().getColor(color));

        weekCol.addView(courseCell);
        courseCell.setTranslationY(margin);
        courseCell.setTranslationX(gap/2);
    }


    private View createCell(int width, int height, int layoutId){
        // Cell width - 100
        // Cell height - 50


        // Create Layout from xml file
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayoutCompat cell = (LinearLayoutCompat) inflater.inflate(layoutId, null);

        // Set cell dimensions
        cell.setLayoutParams(new LinearLayout.LayoutParams(width,height));


        return cell;
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {

        CourseDialogFragment d = (CourseDialogFragment) dialog;

        if (d.getCourse() != null && selectedView != null && !d.isNew()){
            ConstraintLayout root = (ConstraintLayout) selectedView.getParent();
            root.removeView(selectedView);
            selectedView = null;
        }

        Course course = getCourseFromDialog(dialog);
        addNewCourse(course);
    }

    private Course getCourseFromDialog(DialogFragment dialog){
        View v = dialog.getView();

        TextView title = v.findViewById(R.id.dialog_course_title);
        TextView start = v.findViewById(R.id.dialog_start_time);
        TextView end = v.findViewById(R.id.dialog_end_time);
        Spinner weekDay = v.findViewById(R.id.dialog_weekday);

        String sDay = (String) weekDay.getSelectedItem();
        Day day = Day.stringToDay(sDay);
        String cTitle = title.getText()
                .toString().equals("") ?
                title.getHint().toString() :
                title.getText().toString();


        Course course = new Course(
                cTitle,
                day,
                start.getText().toString(),
                end.getText().toString()
        );

        return course;
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

        CourseDialogFragment d = (CourseDialogFragment) dialog;

        if (d.getCourse() != null && selectedView != null && !d.isNew()){
            ConstraintLayout root = (ConstraintLayout) selectedView.getParent();
            root.removeView(selectedView);
            selectedView = null;

            addNewCourse(d.getCourse());
        }
        dialog.getDialog().cancel();
    }

    @Override
    public void onDeleteClick(DialogFragment dialog){
        ConstraintLayout root = (ConstraintLayout) selectedView.getParent();

        System.out.println("Root: "+root);

        root.removeView(selectedView);
        dialog.dismiss();
    }

    public void constructHoursColumn(){
        int starHour = 0;
        int width = hoursColumn.getMaxWidth();
        int height = DisplayTool.convertDpToPixel(50, this);
        int base = DisplayTool.convertDpToPixel(50, this);
        int startMargin = DisplayTool.convertDpToPixel(32, this);

        for (int i=0; i< 24; i++){
            int hour = (starHour + i) % 24 ;
            int transT = startMargin + base * i;
            int color = hour % 2 == 1 ? R.color.odd_day : R.color.even_day;
            String h = hour/10 == 0 ? "0" : "";
            String hourTitle = h+""+hour+":00";

            View hourCell = createCell(width, height, R.layout.calendar_hour_cell);
            hourCell.setBackgroundColor(getResources().getColor(color));
            TextView title = (TextView) hourCell.findViewById(R.id.hour_title);
            title.setText(hourTitle);

            hoursColumn.addView(hourCell);
            hourCell.setTranslationY(transT);
        }
    }
}
