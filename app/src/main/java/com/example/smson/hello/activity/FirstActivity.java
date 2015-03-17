
package com.example.smson.hello.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.smson.hello.R;

public class FirstActivity extends ActionBarActivity {
    private Button testButton;

    @Override   // 재정의시 기입(기입하지 않아도 오류는 발생하지 않으나.. 가능한 반드시 기입)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_first);

        // 버튼 객체 가져오기
        testButton = (Button)findViewById(R.id.textButton);
        // 버튼 객체에 onclick 이벤트를 연결 한다.
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼 클릭시 실행
                onButton1Clicked(v);
            }
        });
    }

    public void onButton1Clicked(View v) {
        Toast.makeText(getApplicationContext(), "button1 클릭", Toast.LENGTH_SHORT).show();
    }

    public void onButton2Clicked(View v) {
        Toast.makeText(getApplicationContext(), "button2 클릭", Toast.LENGTH_SHORT).show();
    }

    public void onButton3Clicked(View v) {
        Toast.makeText(getApplicationContext(), "button3 클릭", Toast.LENGTH_SHORT).show();
    }

    public void onButton4Clicked(View v) {
        Toast.makeText(getApplicationContext(), "button4 클릭", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
