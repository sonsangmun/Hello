package com.example.smson.hello.listview;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smson.hello.R;

import java.util.ArrayList;

public class liveViewActivity extends ActionBarActivity {
    private ListView mListView;
    private ArrayList<String> mNameList;
    private TextView mAniLine;
    private Animation mTranslationAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_view);

        mListView = (ListView) findViewById(R.id.listView);
        mAniLine = (TextView) findViewById(R.id.aniLine);

        // Data 준비
        mNameList = new ArrayList<>();
        mNameList.add("이름1");
        mNameList.add("이름2");
        mNameList.add("이름3");
        mNameList.add("이름4");
        mNameList.add("이름5");
        mNameList.add("이름6");
        mNameList.add("이름7");
        mNameList.add("이름8");
        mNameList.add("이름8");
        mNameList.add("이름9");
        mNameList.add("이름10");
        mNameList.add("이름11");
        mNameList.add("이름12");
        mNameList.add("이름13");
        mNameList.add("이름14");
        mNameList.add("이름15");

        // Adapter 준비
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, mNameList);

        // View에 붙이기
        mListView.setAdapter(adapter);

        // 이벤트
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(liveViewActivity.this, "position : " + position, Toast.LENGTH_SHORT).show();
            }
        });
//        // Animation 객체 생성 전체 layout이 좌에서 우로감
//        mTranslationAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
//                R.anim.sample_ani);
//
//        mListView.setAnimation(mTranslationAnimation);
    }
}
