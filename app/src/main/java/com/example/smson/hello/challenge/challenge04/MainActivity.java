package com.example.smson.hello.challenge.challenge04;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smson.hello.R;
/**
 * 1. 아이디 / 비밀번호 입력후 로그인을 클릭하면 서브엑티비티로 이동
 * 2. 각 버튼 클릭시 AlertDialog 메세지가 나타나고 닫으면 Toast로 메세지 출력
 */
public class MainActivity extends ActionBarActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    EditText mId;
    EditText mPassWord;

    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // 아이디, 비밀번호 입력
        mId = (EditText) findViewById(R.id.idEditText);
        mPassWord = (EditText) findViewById(R.id.passwordEditText);

        // 로그인 버튼
        mButton = (Button) findViewById(R.id.loginBtn);

        // 로그인 버튼이 클릭이 되면.
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mId.getText()) || TextUtils.isEmpty(mPassWord.getText())) {
                    // 아이디, 비밀번호 입력이 자료가 없으면 Toast 로 입력 요청 메세지 출력
                    Toast.makeText(getApplicationContext(), "ID, Password 를 입력하세요", Toast.LENGTH_SHORT).show();
                } else {
                    // 아이디, 비밀번호 입력이 되어 있으면 SubActivity.class 실행.

                    Log.d(TAG, "아이디 : " + mId.length() + " 비밀번호 : " + mPassWord.length());
                    if(mId.length() < 4) {
                        Toast.makeText(getApplicationContext(), "아이디는 최소 4자 이상 입니다.", Toast.LENGTH_SHORT).show();
                    } else if(mPassWord.length() < 4) {
                        Toast.makeText(getApplicationContext(), "비밀번호는 최소 4자 이상 입니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        startActivity(new Intent(getApplicationContext(), SubActivity.class));
                    }
                }
            }
        });
    }
}
