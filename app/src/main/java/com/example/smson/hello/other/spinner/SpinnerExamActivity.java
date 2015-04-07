package com.example.smson.hello.other.spinner;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
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
    private static final String TAG = SpinnerExamActivity.class.getSimpleName();
    private String[] items = {"mike", "angle", "crow", "john", "ginnie", "sally", "cohen", "rice"};
    private Spinner spinner;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_exam);

        // XML 의 spinner, textView 초기화
        spinner = (Spinner) findViewById(R.id.spinner);
        textView = (TextView) findViewById(R.id.textView);

        spinner.setOnItemSelectedListener(this);
        // ArrayAdapter 에 items에 셋팅한 배열을 적용.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        // 드롭다운 형식으로
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 화면상에 나타냄
        spinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "첫번째");
        Toast.makeText(getApplicationContext(), "position : " + position + ", id : " + id + ", value : " + items[position], Toast.LENGTH_SHORT).show();

        textView.setText(items[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Log.d(TAG, "두번재");
        Toast.makeText(getApplicationContext(), "언제 불려지나?", Toast.LENGTH_SHORT).show();
        textView.setText("");
    }
}
