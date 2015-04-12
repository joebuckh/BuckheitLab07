package com.buckheit.joe.buckheitlab07;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.widget.Toast;


/**
 * Created by joebuckheit on 3/7/15.
 */
public class MyDialogFragment_SimpleAlert extends DialogFragment {

    public MyDialogFragment_SimpleAlert() {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        OnClickListener positiveClick = new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity().getBaseContext(), "OK Selected", Toast.LENGTH_SHORT).show();
            }
        };

        OnClickListener negativeClick = new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity().getBaseContext(), "Cancel Selected", Toast.LENGTH_SHORT).show();
            }
        };

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle("Really")
                .setMessage("Are you sure?")
                .setPositiveButton("OK", positiveClick)
                .setNegativeButton("Cancel", negativeClick);
        return alertDialogBuilder.create();


    }

}
