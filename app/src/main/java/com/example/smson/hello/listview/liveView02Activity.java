package com.example.smson.hello.listview;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smson.hello.R;

import java.util.ArrayList;




class proFile {
    private int _photo;
    private String _name;
    private String _address;
    private String _tel;

    public proFile(int photo, String name, String address, String tel) {
        _photo = photo;
        _name = name;
        _address = address;
        _tel = tel;
    }
    public int getPhoto() {
        return _photo;
    }
    public String getName() {
        return _name;
    }
    public String getAddress() {
        return _address;
    }
    public String getTel() {
        return _tel;
    }
}

public class LiveView02Activity extends ActionBarActivity {
    private ListView mListView;
    private ArrayList<proFile> mNameList;
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
        mNameList.add(new proFile(R.drawable.abc_ab_share_pack_holo_dark, "이름1", "주소1", "000-000-001"));
        mNameList.add(new proFile(R.drawable.abc_ab_share_pack_holo_dark, "이름2", "주소2", "000-000-002"));
        mNameList.add(new proFile(R.drawable.abc_ab_share_pack_holo_dark, "이름2", "주소2", "000-000-003"));
        mNameList.add(new proFile(R.drawable.abc_ab_share_pack_holo_dark, "이름2", "주소2", "000-000-004"));
        mNameList.add(new proFile(R.drawable.abc_ab_share_pack_holo_dark, "이름2", "주소2", "000-000-005"));

        // Adapter 준비
        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_2, mNameList);
        CustomerAdapter adapter = new CustomerAdapter(getApplicationContext(), 0, mNameList);

        // View에 붙이기
        mListView.setAdapter(adapter);

        // 이벤트
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(LiveView02Activity.this, "position : " + position, Toast.LENGTH_SHORT).show();
            }
        });
//        // Animation 객체 생성 전체 layout이 좌에서 우로감
//        mTranslationAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
//                R.anim.sample_ani);
//
//        mListView.setAnimation(mTranslationAnimation);

        // 줄 없애기
        mListView.setDivider(null);
    }
}
