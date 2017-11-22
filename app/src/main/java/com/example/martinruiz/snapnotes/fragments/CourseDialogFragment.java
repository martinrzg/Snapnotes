package com.example.martinruiz.snapnotes.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.martinruiz.snapnotes.R;
import com.example.martinruiz.snapnotes.activity.CalendarCreatorActivity;
import com.example.martinruiz.snapnotes.util.DisplayTool;
import com.example.martinruiz.snapnotes.views.calendar.Course;

import butterknife.OnClick;

/**
 * Created by isaac on 11/15/17.
 */

public class CourseDialogFragment extends DialogFragment{

    public interface DialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
        public void onDeleteClick(DialogFragment dialog);
    }

    private DialogListener mListener;

    private View dialogView;

    private TextView startTime;
    private TextView endTime;

    private TextView courseTitle;
    private Spinner courseDay;
    private Button delete;

    private boolean isNew;
    private Course course;

    public static CourseDialogFragment newInstance(Course course, boolean isNew){
        CourseDialogFragment dialog = new CourseDialogFragment();
        Bundle args = new Bundle();

        args.putSerializable("course", course);
        args.putBoolean("isnew", isNew);

        dialog.setArguments(args);

        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        dialogView = inflater.inflate(R.layout.add_course_dialog, null);
        setViewConfiguration(dialogView);

        // Inflate and set the layout for the dialog
        builder.setView(dialogView)
                // Add action buttons
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogPositiveClick(CourseDialogFragment.this);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogNegativeClick(CourseDialogFragment.this);
                    }
                });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onDeleteClick(CourseDialogFragment.this);
            }
        });

        return builder.create();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            course = (Course) getArguments().getSerializable("course");
            isNew = getArguments().getBoolean("isnew");
        } catch (NullPointerException npe) {
            course = null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout to use as dialog or embedded fragment
        return dialogView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (DialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    public void setViewConfiguration(View v){

        startTime = v.findViewById(R.id.dialog_start_time);
        endTime = v.findViewById(R.id.dialog_end_time);
        courseTitle = v.findViewById(R.id.dialog_course_title);
        courseDay = v.findViewById(R.id.dialog_weekday);
        delete = v.findViewById(R.id.dialog_delete);

        if(course != null) {

            courseTitle.setText(course.getName());
            courseDay.setSelection(course.getDay().getPosition());
            startTime.setText(course.get24hStartTime());
            endTime.setText(course.get24hEndTime());
        }


        startTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int h, int m) {
                        course.setStartHour(h);
                        course.setStartMinute(m);
                        startTime.setText(course.get24hStartTime());
                    }
                };

               TimePickerDialog time = new TimePickerDialog(
                   CourseDialogFragment.this.getActivity(),
                   listener,
                   course.getStartHour(),
                   course.getStartMinute(),
                   true
               );

               time.show();
            }
        });

        endTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int h, int m) {
                        course.setEndHour(h);
                        course.setEndMinute(m);
                        endTime.setText(course.get24hEndTime());
                    }
                };

                TimePickerDialog time = new TimePickerDialog(
                        CourseDialogFragment.this.getActivity(),
                        listener,
                        course.getEndHour(),
                        course.getEndMinute(),
                        true
                );

                time.show();
            }
        });

        if (isNew){
            ConstraintLayout root = (ConstraintLayout) delete.getParent();
            root.removeView(delete);
        }
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }


}
