package com.buckheit.joe.buckheitlab07;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by joebuckheit on 3/8/15.
 */
public class DatePickerFragment extends Fragment {

    private Date mDate;
    private int mHour, mMin;
    private String mTwo, mThree;


    public static DatePickerFragment newInstance(Date date) {
        DatePickerFragment fragment = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putSerializable(MyDialogFragment_DatePicker.DATE_ARGS, date);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_datepicker, parent, false);


        if (getArguments() != null)
            mDate = (Date) getArguments().getSerializable(MyDialogFragment_DatePicker.DATE_ARGS);
        else
            mDate = new Date(System.currentTimeMillis());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePicker datePicker = (DatePicker) v.findViewById(R.id.datePicker);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener(){
            public void onDateChanged(DatePicker view, int year, int month, int day) {
                mDate = new GregorianCalendar(year, month, day).getTime();
                getArguments().putSerializable(MyDialogFragment_DatePicker.DATE_ARGS, mDate);
            }
        });

//        TimePicker timePicker = (TimePicker) v.findViewById(R.id.timePicker);

        final EditText editText = (EditText) v.findViewById(R.id.editTextName);
        final CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkBoxBirthday);


        Button done = (Button) v.findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.putExtra(MyDialogFragment_DatePicker.DATE_ARGS, mDate);
                i.putExtra("name", editText.getText().toString());
                i.putExtra("bday", checkBox.isChecked());
                getActivity().setResult(Activity.RESULT_OK, i);
                getActivity().finish();
            }
        });

        Button cancel = (Button) v.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                getActivity().setResult(Activity.RESULT_CANCELED, i);
                getActivity().finish();
            }
        });

        return v;
    }
}

