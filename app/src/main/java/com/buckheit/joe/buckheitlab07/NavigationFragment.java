package com.buckheit.joe.buckheitlab07;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by joebuckheit on 2/15/15.
 */
public class NavigationFragment extends Fragment {

    public static final int REQUEST_DATE = 0;
    public static final int PICK_CONTACT_REQUEST = 1;

    private Button mButtonAboutMe;
    private Button mButton3;
    private Button mButton4;
    private Button mButton5;
    private TextView mTextViewResult;

    private OnNavigationItemSelectedListener mListener;
    public interface OnNavigationItemSelectedListener {
        public void onNavigationItemSelected(int position);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(MyDialogFragment_DatePicker.DATE_ARGS);
            SimpleDateFormat ft = new SimpleDateFormat("EEEE, MMMM d, yyyy");


            Boolean bday = data.getBooleanExtra("bday", false);
            String name = data.getStringExtra("name");
            if (name.isEmpty())
                name = "";
            if (bday)
                if (!name.isEmpty())
                    name = name + " - BIRTHDAY";
                else
                    name = "BIRTHDAY";
            mTextViewResult.setText(ft.format(date) + "\n" + name);

        }

        if (requestCode == PICK_CONTACT_REQUEST) {
            Uri contactUri = data.getData();
            String[] projection = {
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                    ContactsContract.CommonDataKinds.Phone.NUMBER};

            Cursor cursor = getActivity().getContentResolver()
                    .query(contactUri, projection, null, null, null);
            cursor.moveToFirst();

            int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            String number = cursor.getString(column);
            column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            String name = cursor.getString(column);

            mTextViewResult.setText("Name: " + name + "\nTelephone: " + number);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnNavigationItemSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + "must implement OnFragmentInteractionListener");
        }

    }

    public void showDatePickerDialogResults() {
        Date date = new Date(System.currentTimeMillis());
        MyDialogFragment_DatePicker dialog = MyDialogFragment_DatePicker.newInstance(date);
        dialog.setTargetFragment(this, REQUEST_DATE);
        dialog.show(getFragmentManager(),"DatePicker Dialog");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.navigation, parent, false);

        mButtonAboutMe = (Button) v.findViewById(R.id.button_aboutme);
        mButtonAboutMe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mListener.onNavigationItemSelected(1);
            }

        });

        mButton3 = (Button) v.findViewById(R.id.button_task3);
        mButton3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showDatePickerDialogResults();
            }

        });

        mButton4 = (Button) v.findViewById(R.id.button_task4);
        mButton4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Date date = new Date(System.currentTimeMillis());
                Intent intent = new Intent(getActivity(), DatePickerActivity.class);
                intent.putExtra(MyDialogFragment_DatePicker.DATE_ARGS, date);
                startActivityForResult(intent, REQUEST_DATE);

            }

        });

        mButton5 = (Button) v.findViewById(R.id.button_task5);
        mButton5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(intent,NavigationFragment.PICK_CONTACT_REQUEST);
            }

        });

        mTextViewResult = (TextView) v.findViewById(R.id.textView_result);

        return v;
    }
}
