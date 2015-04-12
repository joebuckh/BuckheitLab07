package com.buckheit.joe.buckheitlab07;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.util.Date;


public class DatePickerActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_activity);

        Intent intent = getIntent();
        Date date = (Date) intent.getSerializableExtra(MyDialogFragment_DatePicker.DATE_ARGS);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, DatePickerFragment.newInstance(date))
                    .commit();
        }
    }
}
