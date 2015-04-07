package com.example.smson.hello.listview.activitylist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.smson.hello.R;

import com.example.smson.hello.activity.ActivityExamActivity;
import com.example.smson.hello.activity.EditTextActivity;
import com.example.smson.hello.activity.FirstActivity;
import com.example.smson.hello.activity.FrameLayoutActivity;
import com.example.smson.hello.activity.RelativeLayoutExamActivity;
import com.example.smson.hello.activity.SecondActivity;
import com.example.smson.hello.activity.TableLayoutActivity;
import com.example.smson.hello.challenge.challenge05.challenge05;
import com.example.smson.hello.challenge.challenge06.challenge06Activity;
import com.example.smson.hello.other.event.TouchEventActivity;
import com.example.smson.hello.listview.LiveView02Activity;
import com.example.smson.hello.listview.liveViewActivity;

public class SubActivity extends ActionBarActivity  implements AdapterView.OnItemClickListener {
    private static final String[] ACTIVITY_ITEMS = {
            "Activity 예제",
            "EditTextActivity",
            "FirstActivity",
            "FrameLayout",
            "RelativeLayout",
            "SecondActivity",
            "TableLayout"
    };
    private static final Class[] ACTIVITY_CLASSES = {
            ActivityExamActivity.class,
            EditTextActivity.class,
            FirstActivity.class,
            FrameLayoutActivity.class,
            RelativeLayoutExamActivity.class,
            SecondActivity.class,
            TableLayoutActivity.class
    };

    private static final String[] CHALLENGE_ITEMS = {
            "Challenge05",
            "Challenge06",
    };

    private static final Class[] CHALLENGE_CLASSES = {
            challenge05.class,
            challenge06Activity.class,
    };

    private static final String[] EVENT_ITEMS = {
            "TouchEvent"
    };

    private static final Class[] EVENT_CLASSES = {
            TouchEventActivity.class
    };

    private static final String[] LISTVIEW_ITEMS = {
            "ArrayAdapter",
            "CustomAdapter"
    };

    private static final Class[] LISTVIEW_CLASSES = {
            liveViewActivity.class,
            LiveView02Activity.class
    };

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_activity);

        mListView = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1, getItems().first);

        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
    }


    private Pair<String[], Class[]> getItems() {
        Pair<String[], Class[]> result = null;

        String menu = getIntent().getStringExtra("menu");
        switch (menu) {
            case "Activity":
                result = new Pair(ACTIVITY_ITEMS, ACTIVITY_CLASSES);
                break;
            case "Challenge":
                result = new Pair(CHALLENGE_ITEMS, CHALLENGE_CLASSES);
                break;
            case "Event":
                result = new Pair(EVENT_ITEMS, EVENT_CLASSES);
                break;
            case "ListView":
                result = new Pair(LISTVIEW_ITEMS, LISTVIEW_CLASSES);
                break;
        }
        return result;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(getApplicationContext(), getItems().second[position]));

        // Class.forName 으로 정보 가져오는 방법
//        Class c = null;
//        try {
//            c = Class.forName("com.suwonsmartapp.hello.activity.ActivityExamActivity");
//            startActivity(new Intent(getApplicationContext(), c));
//        } catch (ClassNotFoundException e) {
//            Toast.makeText(getApplicationContext(), "없다", Toast.LENGTH_SHORT).show();
//        }
    }
}
