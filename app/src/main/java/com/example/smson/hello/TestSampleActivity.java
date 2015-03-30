package com.example.smson.hello;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;


public class TestSampleActivity extends ActionBarActivity {
    private ListView screen;
    // alert 창 변수 선언.
    AlertDialog.Builder dialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sample);

        // alert dialog 상수 선언.
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        // alert 창 제목
        dialogBuilder.setTitle("타이틀");
        // alert 창 본문 내용
        dialogBuilder.setMessage("메세지");
        // 확인버튼 2번째에는 OnClickListener()로 클릭시 작동할 코드 삽입이 가능하며 없는 경우 null
        dialogBuilder.setPositiveButton("확인버튼", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                /* User clicked OK so do some stuff */
                Toast.makeText(getApplicationContext(), "확인합니다.", Toast.LENGTH_SHORT).show();
            }
        });
        // 취소버튼 처리
        dialogBuilder.setNegativeButton("취소", null);
        // 설정한 alert 창을 띄운다.
        dialogBuilder.show();
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test_sample, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
