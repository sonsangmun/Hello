package com.example.smson.hello.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smson.hello.R;

/**
 * Activity 생성에서 삭제 과정을 확인하는 샘플
 */
public class ActivityExamActivity extends ActionBarActivity {
    private static final String TAG = ActivityExamActivity.class.getSimpleName();

    private static final int REQUEST_CODE_A = 0;
    private static final int REQUEST_CODE_B = 1;

    private Button mMoveBtn;
    private Button mDataMoveBtn;
    private Button mDialogBtn;

    private EditText mDataEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_exam);

        mMoveBtn = (Button) findViewById(R.id.dataBtn);
        mDataMoveBtn = (Button) findViewById(R.id.dataMoveBtn);
        mDialogBtn = (Button) findViewById(R.id.dialogBtn);
        mDataEditText =(EditText) findViewById(R.id.dataEditText);

        // 화면 전환
        mMoveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TargetActivity.class);
                startActivity(intent);
            }
        });

        // Data와 함께 화면 전환
        mDataMoveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TargetActivity.class);

                // type 1
//                CharSequence value = mDataEditText.getText();
                // type 2
                String value = mDataEditText.getText().toString();
//
//                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                intent.putExtra("key", value);
//                startActivity(intent);
                intent.putExtra("key", value);
                intent.putExtra("code", REQUEST_CODE_B);
                startActivityForResult(intent, REQUEST_CODE_B);
            }
        });

        // Result 값을 받도록 화면 전환
        findViewById(R.id.resultBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TargetActivity.class);
                startActivityForResult(intent, REQUEST_CODE_A);
            }
        });

        // AlertDialog 창 구성
        mDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ActivityExamActivity.this);
                dialog.setMessage("이것은 dialog");
                dialog.setTitle("타이틀");
                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "확인", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "취소", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNeutralButton("중간버튼", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "중간버튼", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setPositiveButton("확인", null);
                dialog.setNegativeButton("취소", null);
                dialog.setNeutralButton("중간버튼", null);
                dialog.create();
                dialog.show();
            }
        });
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_A && resultCode == RESULT_OK) {
            Toast.makeText(getApplicationContext(), data.getStringExtra("data"), Toast.LENGTH_SHORT)
                    .show();
        } else if (requestCode == REQUEST_CODE_B) {
            Toast.makeText(getApplicationContext(), data.getStringExtra("data"), Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}
