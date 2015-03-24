package com.example.smson.hello.listview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smson.hello.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

class DateBook {
    private String dTitle;
    private int dHour;
    private int dMinutes;

    public DateBook(String title, int hour, int minutes) {
        dTitle = title;
        dHour = hour;
        dMinutes = minutes;
    }
    public int getValue(String value_name) {
        if(value_name == "hour") {
            return dHour;
        } else if(value_name == "minutes") {
            return dMinutes;
        }
        return 0;
    }
    public String getValue(){
        return dTitle;
    }
}

public class GridActivity2Activity extends ActionBarActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    private static final String TAG = GridActivity2Activity.class.getSimpleName();

    private Calendar calendar;
    private GridView mGridView;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;
    private int thisYear, thisMonth, thisDay;
    private Button mPreBtn, mNextBtn, mSaveBtn;
    private TextView mDateView;
    public  static HashMap<String, DateBook> sMap;
//    private EditText mInputWord;
    private String thisPosition;


    public DateBook getMap(String position){
        return sMap.get(position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_activity2);

        sMap = new HashMap<>();
        mPreBtn = (Button) findViewById(R.id.xPreBtn);
        mNextBtn = (Button) findViewById(R.id.xNextBtn);
//        mSaveBtn = (Button) findViewById(R.id.xSaveBtn);
        mDateView = (TextView) findViewById(R.id.xDateView);
        mGridView = (GridView) findViewById(R.id.gridview);
//        mInputWord = (EditText) findViewById(R.id.xInputWord);


        // 오늘 날짜
        recalculate();

        // 이전달 / 다음달 버튼
        mPreBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);

        // save 버튼
//        mSaveBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sMap.put(thisPosition, mInputWord.getText().toString());
//                Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();
//            }
//        });

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
        for (int i = 1; i < cWeek; i++) {
            list.add(" ");
        }

        // 월의 마지막날까지 자료 넣기
        for (int i = 1; i <= cLastDay; i++) {
            list.add(String.valueOf(i));
        }

        // 어댑터 준비
        CustomerAdapterEx adapter = new CustomerAdapterEx(getApplicationContext(), 0, list);
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



//        String data = sMap.get(String.valueOf(position));
//        if(data != null) {
//            mInputWord.setText(data);
//        } else {
//            mInputWord.setText("");
//        }
        thisPosition = String.valueOf(position);

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
        final View rootView = inflater.inflate(R.layout.activity_datebook, null);
        ((TextView) rootView.findViewById(R.id.dialogTitle)).setText(thisYear + "년 " + thisMonth + "월 " + thisDay + "일");

        DateBook tempDateBook = sMap.get(thisPosition);
        if(tempDateBook != null) {
            String tempTitle = tempDateBook.getValue();
            int tempHour = tempDateBook.getValue("hour");
            int tempMinutes = tempDateBook.getValue("minutes");
            ((EditText) rootView.findViewById(R.id.xTitle)).setText(tempTitle);
            ((EditText) rootView.findViewById(R.id.xHour)).setText(String.valueOf(tempHour));
            ((EditText) rootView.findViewById(R.id.xMinutes)).setText(String.valueOf(tempMinutes));

            ((TextView) findViewById(R.id.timeLine)).setText(tempHour + ":" + tempMinutes + " | " + tempTitle);
        }

        builder.setView(rootView)
            // Add action buttons
            .setPositiveButton("저장", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    // 저장 처리
                    EditText nullCheck;
                    int filterSw = 1;

                    nullCheck = (EditText) rootView.findViewById(R.id.xTitle);
                    String tempTitle = nullCheck.getText().toString();
                    if (nullCheck.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "일정을 입력해 주세요", Toast.LENGTH_SHORT).show();
                        filterSw = 0;
                    }
                    nullCheck = (EditText) rootView.findViewById(R.id.xHour);
                    int tempHour = Integer.parseInt(nullCheck.getText().toString());
                    if (nullCheck.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "일정 시간(시)을 입력해 주세요", Toast.LENGTH_SHORT).show();
                        filterSw = 0;
                    }
                    nullCheck = (EditText) rootView.findViewById(R.id.xMinutes);
                    int tempMinutes = Integer.parseInt(nullCheck.getText().toString());
                    if(nullCheck.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "일정 시간(분)을 입력해 주세요", Toast.LENGTH_SHORT).show();
                        filterSw = 0;
                    }

//                      Toast.makeText(getApplicationContext(), tempTitle, Toast.LENGTH_SHORT).show();
                    if(filterSw == 1){
                        sMap.put(thisPosition,new DateBook(tempTitle, tempHour, tempMinutes));
                    }

                    ((TextView) findViewById(R.id.timeLine)).setText(tempHour + ":" + tempMinutes + " | " + tempTitle);
                }
            })
            .setNegativeButton("닫기", null);
        builder.show();
    }
}

class CustomerAdapterEx extends ArrayAdapter<String> {

    // ViewHolder 패턴
    static class ViewHolder {
        private TextView mDay;
        private ImageView mIcon;
        private LayoutInflater mLayout;
    }

    // Layout을 가져오기 위한 객체
    private LayoutInflater inflater;

    public CustomerAdapterEx(Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("CustomAdapter", "position : " + position);

        ViewHolder holder;
        View view = convertView;


        if (view == null) {
            // View 를 처음 로딩할 때, Data 를 처음 셋팅할 때
            inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.calendar_body, null);
            TextView viewDay = (TextView) view.findViewById(R.id.xDay);
            ImageView viewIcon = (ImageView) view.findViewById(R.id.xIcon);

            holder = new ViewHolder();
            holder.mIcon = viewIcon;
            holder.mDay = viewDay;


            view.setTag(holder);
        } else {
            // View, Data 재사용
            holder = (ViewHolder) view.getTag();
        }


        String tempDay = getItem(position);
        DateBook tempDateBook = GridActivity2Activity.sMap.get(String.valueOf(position));

        if(tempDateBook != null) {
            view.setBackgroundColor(Color.rgb(242, 150, 97));
        }
        if (!TextUtils.isEmpty(tempDay)) {
            holder.mDay.setText(tempDay);
        }

        // position 위치의 데이터를 취득
//        String name = getItem(position);
//        if (!TextUtils.isEmpty(name.toString())) {
//            holder.labelText.setText(name.toString());
//        }

        // 홀수, 짝수 줄 배경색 변경
//        if (position % 2 == 0) {
//            holder.labelText.setBackgroundColor(Color.parseColor("#FFFFFF"));
//        } else {
//            holder.labelText.setBackgroundColor(Color.parseColor("#CCCCCC"));
//        }

        // 애니메이션 적용
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.sample_ani);
        view.startAnimation(animation);

        // 완성된 View return
        return view;
    }

}