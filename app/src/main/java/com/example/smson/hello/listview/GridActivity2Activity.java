package com.example.smson.hello.listview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smson.hello.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class GridActivity2Activity extends ActionBarActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    private static final String TAG = GridActivity2Activity.class.getSimpleName();

    private int debugSw = 0;
    private Calendar calendar;
    private GridView mGridView;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;
    private int thisYear, thisMonth, thisDay;
    private Button mPreBtn, mNextBtn, mSaveBtn;
    private TextView mDateView;
    private HashMap<String, String> mMap;
    private EditText mInputWord;
    private String thisPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_activity2);

        mMap = new HashMap<>();
        mPreBtn = (Button) findViewById(R.id.xPreBtn);
        mNextBtn = (Button) findViewById(R.id.xNextBtn);
        mSaveBtn = (Button) findViewById(R.id.xSaveBtn);
        mDateView = (TextView) findViewById(R.id.xDateView);
        mGridView = (GridView) findViewById(R.id.gridview);
        mInputWord = (EditText) findViewById(R.id.xInputWord);

        // 오늘 날짜
        recalculate();

        // 이전달 / 다음달 버튼
        mPreBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);

        // save 버튼
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.put(thisPosition, mInputWord.getText().toString());
                Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        makeCalendar();


    }

    private void recalculate() {
        if (calendar == null) {
            calendar = GregorianCalendar.getInstance();
        }

        thisYear = calendar.get(Calendar.YEAR);
        thisMonth = calendar.get(Calendar.MONTH) + 1;
        thisDay = calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.xPreBtn:
                calendar.add(Calendar.MONTH, -1);
                break;
            case R.id.xNextBtn:
                calendar.add(Calendar.MONTH, 1);
                break;
        }
        recalculate();
        makeCalendar(thisYear, thisMonth);
    }

    public void makeCalendar(){
        makeCalendar(0, 0);
    }

    public void makeCalendar(int cYear, int cMonth){
        // 표시하는 달력의 년,월,일 정보가 없으면 금일을 기분
        Calendar calendar = Calendar.getInstance();
        int cDay;
        if(cYear == 0 || cMonth == 0) {
            cYear = calendar.get(Calendar.YEAR);
            cMonth = calendar.get(Calendar.MONTH) + 1;
            cDay = calendar.get(Calendar.DAY_OF_MONTH);
        } else {
            cDay = 1;
        }

        // 달력으로 보여줄 년, 월, 1일 셋팅
        calendar.set(cYear, cMonth - 1, 1);
        // 금일 무슨요일인지 확인(1 ~ 7) 일요일부터
        int cWeek = calendar.get(Calendar.DAY_OF_WEEK);
        // 이달의 마지막날
        int cLastDay = calendar.getActualMaximum(Calendar.DATE);

        // 데이터 준비
        list = new ArrayList<>();
        list.add("일");
        list.add("월");
        list.add("화");
        list.add("수");
        list.add("목");
        list.add("금");
        list.add("토");

        // 1일날 앞의 공백 자료 넣기
        for (int i = 0; i < cWeek; i++) {
            list.add(" ");
        }

        // 월의 마지막날까지 자료 넣기
        for (int i = 1; i <= cLastDay; i++) {
            list.add(String.valueOf(i));
        }

        // 어댑터 준비
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        mGridView.setAdapter(adapter);

        // 달력의 제목 표시
        mDateView.setText(cYear + "'년 " + cMonth + "월");

        // 이벤트
        mGridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),  "position : " + position + ", data : " + list.get(position), Toast.LENGTH_SHORT).show();

        String data = list.get(position);
        if(data != null) {
            // 클릭한 날짜(일) 정보를 적용함.
            calendar.set(calendar.DAY_OF_MONTH, Integer.valueOf(data));
            recalculate();
        }

//        참고소스
//        mAdapter.setSelectedPosition(position);
//        String schedule = mScheduleMap.get(mAdapter.getItem(position));
//        mScheduleEditText.setText(schedule);



//        String data = mMap.get(String.valueOf(position));
//        if(data != null) {
//            mInputWord.setText(data);
//        } else {
//            mInputWord.setText("");
//        }
//        thisPosition = String.valueOf(position);

        // 데이터를 변경 후, 어댑터에 알려주고 갱신
        // list.set(position, list.get(position) + "0");
        // adapter.notifyDataSetChanged();

        // 일정 입력 다이얼로그 보이기
        showScheduleInputDialog();
    }

    private void showScheduleInputDialog() {
        // Custom Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(GridActivity2Activity.this);
        // Get the layout inflater
        LayoutInflater inflater = getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View rootView = inflater.inflate(R.layout.activity_datebook, null);
        ((TextView) rootView.findViewById(R.id.dialogTitle)).setText(thisYear + "년 " + thisMonth + "월 " + thisDay + "일");

        builder.setView(rootView)
                // Add action buttons
                .setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // 저장 처리
                    }
                })
                .setNegativeButton("닫기", null);
        builder.show();
    }
}