package com.example.smson.hello;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * ListView 로 메뉴 구성
 */
public class Main2Activity extends ActionBarActivity implements AdapterView.OnItemClickListener{
    public static final String[] ITEMS = {
            "Activity",
            "Challenge",
            "Event",
            "ListView",
            "Thread",
            "Parsing",
            "TourList",
            "Intent",
            "Database",
            "Other"
    };
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mListView = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1, ITEMS);

        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getApplicationContext(), Sub2Activity.class);
        intent.putExtra("menu", ITEMS[position]);
        startActivity(intent);
    }
}
