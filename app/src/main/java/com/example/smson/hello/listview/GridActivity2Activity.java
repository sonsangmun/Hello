package com.example.smson.hello.listview;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.example.smson.hello.R;
import com.example.smson.hello.activity.ActivityExamActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class GridActivity2Activity extends ActionBarActivity implements AdapterView.OnItemClickListener {
    private  static final String TAG = ActivityExamActivity.class.getSimpleName();

    private int debugSw = 1;
    private Calendar calendar;
    private GridView mGridView;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;
    private int year, month, day, hour, minute, lastDay, week;
    private int thisYear, thisMonth, thisDay, thisWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_activity2);

        // 표시하는 달력의 년,월,일 정보가 없으면 금일을 기분
        Calendar calendar = Calendar.getInstance();
        if(thisYear == 0 || thisMonth == 0) {
            thisYear = calendar.get(Calendar.YEAR);
            thisMonth = calendar.get(Calendar.MONTH) + 1;
            thisDay = calendar.get(Calendar.DAY_OF_MONTH);
        } else {
            thisDay = 1;
        }
        toastEcho(thisYear + "년" + thisMonth + "월 " + thisDay + "일");

        makeCalendar(thisYear, thisMonth - 1);


        // 이벤트
        mGridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),  "position : " + position + ", data : " + list.get(position), Toast.LENGTH_SHORT).show();

        // 데이터를 변경 후, 어댑터에 알려주고 갱신
        list.set(position, list.get(position) + "0");
        adapter.notifyDataSetChanged();
    }

    public void toastEcho(String msg) {
        if (debugSw == 1) {
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    public void makeCalendar(int year, int month) {
        // 달력으로 보여줄 년, 월, 1일 셋팅
        calendar.set(thisYear, thisMonth - 1, 1);

        // 금일 무슨요일인지 확인(1 ~ 7) 일요일부터
        thisWeek = calendar.get(Calendar.DAY_OF_WEEK);
        toastEcho("금일요일 : " + thisWeek);

        // 이달의 마지막날
        lastDay = calendar.getActualMaximum(Calendar.DATE);
        toastEcho("이달의 마지막날 : " + lastDay);

        mGridView = (GridView) findViewById(R.id.gridview);

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
        for (int i = 0; i < week; i++) {
            list.add("#");
        }

        // 월의 마지막날까지 자료 넣기
        for (int i = 1; i <= lastDay; i++) {
            list.add(String.valueOf(i));
        }

        // 어댑터 준비
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, list);

        mGridView.setAdapter(adapter);
    }
}
