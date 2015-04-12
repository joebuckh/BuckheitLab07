package com.buckheit.joe.buckheitlab07;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Created by joebuckheit on 3/7/15.
 */

public class MyDialogFragment_DatePicker extends DialogFragment {

    private Date mDate;
    public static final String DATE_ARGS = "date";

    public MyDialogFragment_DatePicker() {

    }

    public static MyDialogFragment_DatePicker newInstance(Date date) {
        MyDialogFragment_DatePicker dialog = new MyDialogFragment_DatePicker();
        Bundle args = new Bundle();
        args.putSerializable(DATE_ARGS, date);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        if (getArguments() != null)
            mDate = (Date) getArguments().getSerializable(DATE_ARGS);
        else
            mDate = new Date(System.currentTimeMillis());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_date,null);
        final EditText editText = (EditText) v.findViewById(R.id.editTextName);
        final CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkBoxBirthday);

        DatePicker datePicker = (DatePicker) v.findViewById(R.id.datePicker);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener(){
            public void onDateChanged(DatePicker view, int year, int month, int day) {
                mDate = new GregorianCalendar(year, month, day).getTime();
                getArguments().putSerializable(DATE_ARGS, mDate);
            }
        });

        OnClickListener positiveClick = new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (getTargetFragment() != null) {
                    Intent i = new Intent();
                    i.putExtra(DATE_ARGS, mDate);
                    i.putExtra("name", editText.getText().toString());
                    i.putExtra("bday", checkBox.isChecked());
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
                } else
                    Toast.makeText(getActivity(), "no need to return results", Toast.LENGTH_SHORT).show();
            }
        };



        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(v);
        alertDialogBuilder.setTitle("Date Picker")
                .setMessage("Pick a date:")
                .setPositiveButton("OK", positiveClick);
        return alertDialogBuilder.create();


    }

}