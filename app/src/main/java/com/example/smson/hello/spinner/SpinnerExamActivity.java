package com.example.smson.hello.spinner;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smson.hello.R;
/*
    교재 356P
 */
public class SpinnerExamActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener{
    private String[] items = {"mike", "angle", "crow", "john", "ginnie", "sally", "cohen", "rice"};
    private Spinner spinner;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_exam);

        spinner = (Spinner) findViewById(R.id.spinner);
        textView = (TextView) findViewById(R.id.textView);

        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), "position : " + position + ", id : " + id + ", value : " + items[position], Toast.LENGTH_SHORT).show();

        textView.setText(items[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getApplicationContext(), "언제 불려지나?", Toast.LENGTH_SHORT).show();
        textView.setText("");
    }
}
