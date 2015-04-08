package com.example.smson.hello.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smson.hello.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * 1. EditText 테스트 샘플 소스
 * TODO 2. EditText 입력정보를 파일로 저장
 */
public class EditTextActivity extends ActionBarActivity {
    private static final String TAG = EditTextActivity.class.getSimpleName();
    private EditText mInputEditText;
    private EditText mOutputEditText;
    private Button mSaveButton;
    private Button mLoadButton;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        mInputEditText = (EditText) findViewById(R.id.inputText);
        mOutputEditText = (EditText) findViewById(R.id.outputText);
        mSaveButton = (Button) findViewById(R.id.save_button);
        mLoadButton = (Button) findViewById(R.id.load_button);
        mTextView = (TextView) findViewById(R.id.textview);

        textChange();
        // code block A
        // mInputEditText에서 글을 입력하면 아래의 3개 메서드 befor.., onText.., after.. 가 차례대로 실행된다.
        mInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG, "beforeTextChanged");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "onTextChanged");
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "afterTextChanged");

                // type 1
                // String inputString = mInputEditText.getText().toString();
                // mOutputEditText.setText(inputString);

                // type 2
                // String inputString = s.toString();
                // mOutputEditText.setText(inputString);

                // type 3
                mOutputEditText.setText(s);
            }
        });

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // id 값을 가져와서 R.id.save_button 인 경우 실행
                switch (v.getId()) {
                    case R.id.save_button:
                        try {
                            // 입력 받은 값을 String 형으로 저장
                            String txt = mInputEditText.getText().toString();

                            // text.txt 파일을 쓰기 권한을 준다.
                            FileOutputStream outputStream = openFileOutput("test.txt", Activity.MODE_WORLD_WRITEABLE);

                            // byte 단위로 입력함.
                            outputStream.write(txt.getBytes());
                            outputStream.close();

                            // Exception 이 발생하지 않았으므로 저장완료 Toast를 띄움.
                            Toast.makeText(getApplicationContext(), "저장완료", Toast.LENGTH_SHORT).show();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();

                            // Exception이 발생하였으므로 저장실패 Toast를 띄움.
                            Toast.makeText(getApplicationContext(), "저장실패", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                }
            }
        });

        mLoadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileInputStream fis = null;
                try {
                    fis = openFileInput("test.txt");
                    byte[] data = new byte[fis.available()];
                    while (fis.read(data) != -1){
                        Log.d(TAG, fis.read(data)) ;
                    }
                    fis.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public String ReadTextAssets(String strFileName) {
        String text = null;
        try {
            InputStream is = getAssets().open(strFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            text = new String(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return text;
    }

    public boolean WriteTextFile(String strFileName, String strBuf) {
        try {
            File file = getFileStreamPath(strFileName);
            FileOutputStream fos = new FileOutputStream(file);
            Writer out = new OutputStreamWriter(fos, "UTF-8");
            out.write(strBuf);
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    private void textChange() {
        mOutputEditText.setText(mInputEditText.getText());
    }
}
