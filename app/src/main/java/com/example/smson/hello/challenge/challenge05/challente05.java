package com.example.smson.hello.challenge.test2.challenge05;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smson.hello.R;

public class challente05 extends ActionBarActivity {
    private Button mDateBtn;
    private Button mSaveBtn;
    private EditText mInputName;
    private EditText mInputAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challente05);

        mDateBtn = (Button) findViewById(R.id.dateBtn);
        mSaveBtn = (Button) findViewById(R.id.saveBtn);
        mInputName = (EditText) findViewById(R.id.inptName);
        mInputAge = (EditText) findViewById(R.id.inputAge);

        mDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(v. listener, 2003, 10, 22);
                dialog.show();
            }
        });
    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener(){

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Toast.makeText(getApplicationContext(), year + "년" + monthOfYear + "월" + dayOfMonth + "일", Toast.LENGTH_SHORT).show();
        }
    };

}
