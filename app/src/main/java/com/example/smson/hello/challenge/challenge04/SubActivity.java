package com.example.smson.hello.challenge.challenge04;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.smson.hello.R;

public class SubActivity extends ActionBarActivity {

    Button mCustomerBtn;
    Button mSalesBtn;
    Button mMerchantBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);

        // 고객, 매출, 상품 버튼
        mCustomerBtn = (Button) findViewById(R.id.customerBtn);
        mSalesBtn = (Button) findViewById(R.id.salesBtn);
        mMerchantBtn = (Button) findViewById(R.id.merchantBtn);

        // 3개의 버튼중 아무거나 클릭이 되면 showDialog(Button) v 실행
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog((Button) v);
            }
        };

        mCustomerBtn.setOnClickListener(onClickListener);
        mSalesBtn.setOnClickListener(onClickListener);
        mMerchantBtn.setOnClickListener(onClickListener);
    }

    private void showDialog(Button v) {
        // 클릭한 버튼의 getText().toString() 으로 text 값 가져오기
        final String title = v.getText().toString();

        // AlertDialog에 title 을 메세지 제목으로 셋팅
        AlertDialog.Builder builder = new AlertDialog.Builder(SubActivity.this);
        // AlertDialog 구성 객체들을 아래에서 테스트 해 볼수 있다.
        builder.setTitle(title);
        builder.setMessage("메세지");
        builder.setIcon(R.drawable.girl);
        // 닫기 버튼
        builder.setPositiveButton("닫기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(SubActivity.this, title + "에서 닫혔음", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        // 셋팅한 AleartDialog 보여주기
        builder.show();
    }
}
