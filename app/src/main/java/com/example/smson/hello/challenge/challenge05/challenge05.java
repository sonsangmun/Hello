package com.example.smson.hello.challenge.challenge05;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.smson.hello.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class challenge05 extends ActionBarActivity {
    private Button mDateBtn;
    private Button mTimeBtn;
    private Button mSaveBtn;
    private EditText mInputName;
    private EditText mInputAge;
    private int year, month, day, hour, minute;
    private View.OnClickListener onClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge05);

        mDateBtn = (Button) findViewById(R.id.dateBtn);
        mTimeBtn = (Button) findViewById((R.id.timeBtn));
        mSaveBtn = (Button) findViewById(R.id.saveBtn);
        mInputName = (EditText) findViewById(R.id.inptName);
        mInputAge = (EditText) findViewById(R.id.inputAge);

        GregorianCalendar calendar = new GregorianCalendar();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        mDateBtn.setText(year + "년 " + (month + 1) + "월 " + day + "일");
        mTimeBtn.setText(hour + " 시 " + minute + " 분");

        // OnClickListener 에 대한 처리
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == mDateBtn) {
                    new DatePickerDialog(challenge05.this, dateSetListener, year, month, day).show();
                } else if(v == mTimeBtn) {
                    // onCreate에서 처음 세팅된 시간으로 계속 들어감... click 할때마다 들어가게 하려면
                    // 이 안에서 계속 변수에 새로 값을 넣어야겠네

                    // false 하니깐 오전, 오후 안뜨네
                    new TimePickerDialog(challenge05.this, timeSetListener, hour, minute, false).show();
                } else if(v == mSaveBtn) {
                    if (mInputName.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "이름을 입력해주세요!", Toast.LENGTH_SHORT).show();
                    } else if(mInputAge.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "나이를 입력해주세요!", Toast.LENGTH_SHORT).show();
                    } else {
                        String msg = "이름 : " + mInputName.getText().toString() + ", 나이 : " + mInputAge.getText().toString();
                        msg += ", " + mDateBtn.getText().toString();
                        msg += ", " + mTimeBtn.getText().toString();
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    alert("버튼 클릭 장애!");
                }

            }
        };

        // 생년월일 버튼 클릭시
        mDateBtn.setOnClickListener(onClickListener);

        // 시간 버튼 클릭시
        mTimeBtn.setOnClickListener(onClickListener);

        // 저장버튼 클릭시
        mSaveBtn.setOnClickListener(onClickListener);
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener(){

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            // 날짜 형식 처리 type 1 : String.format
//            String msg = String.format("%d 년 %d 월 %d 일", year, monthOfYear + 1, dayOfMonth);

            // 날짜 형식 처리 type 2 : SimpleDataFormat
            SimpleDateFormat sf = new SimpleDateFormat("yyyy년 MM월 dd일");


            GregorianCalendar tmpCalendar = new GregorianCalendar();
            tmpCalendar.set(year, monthOfYear, dayOfMonth);

            mDateBtn.setText(sf.format(tmpCalendar.getTime()));
            Toast.makeText(challenge05.this, sf.format(tmpCalendar.getTime()), Toast.LENGTH_SHORT).show();
        }
    };

    private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // TODO Auto-generated method stub
            String am_pm = "";

            Calendar datetime = Calendar.getInstance();
            datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            datetime.set(Calendar.MINUTE, minute);

            if (datetime.get(Calendar.AM_PM) == Calendar.AM)
                am_pm = "오전";
            else if (datetime.get(Calendar.AM_PM) == Calendar.PM)
                am_pm = "오후";

            String strHrsToShow = (datetime.get(Calendar.HOUR) == 0) ? "12" : datetime.get(Calendar.HOUR) + "";
            strHrsToShow = am_pm + strHrsToShow + " 시 " + datetime.get(Calendar.MINUTE) + " 분 ";

            mTimeBtn.setText(strHrsToShow);
            //Toast.makeText(DatePickerActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    };

    public void alert(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
