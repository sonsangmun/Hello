package com.example.smson.hello.listview;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smson.hello.R;

import java.util.ArrayList;
import java.util.List;

public class mainActivity extends ActionBarActivity {
    private ArrayList<String> activityList;
    private ListView mMainLayout;
    private TextView mRowLayout;
    private List<PackageInfo> packageInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        packageInfo = getPackageManager().getInstalledPackages(0);
        activityList = new ArrayList<>();

        // packageInfo.size()
        for (int i = 0; i < 10; i++) {
            activityList.add(packageInfo.get(i).packageName);
        }

        mMainLayout = (ListView) findViewById(R.id.mainLayout);
        mRowLayout = (TextView) findViewById(R.id.text1);

        // Adapter 준비
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.activity_main, activityList);

        mMainLayout.setAdapter(adapter);
    }
}