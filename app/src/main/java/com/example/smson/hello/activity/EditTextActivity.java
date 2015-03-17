package com.example.smson.hello.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;

import com.example.smson.hello.R;


public class EditTextActivity extends ActionBarActivity {
    private static final String TAG = EditTextActivity.class.getSimpleName();
    EditText mInputEditText;
    EditText mOutputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        mInputEditText = (EditText) findViewById(R.id.inputText);
        mOutputEditText = (EditText) findViewById(R.id.outputText);

        textChange();
//        code block A
//        mInputEditText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                Log.d(TAG, "beforeTextChanged");
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                Log.d(TAG, "onTextChanged");
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                Log.d(TAG, "afterTextChanged");
//
                  // type 1
//                // String inputString = mInputEditText.getText().toString();
//                // mOutputEditText.setText(inputString);
//
                  // type 2
//                // String inputString = s.toString();
//                // mOutputEditText.setText(inputString);
//
                  // type 3
//                mOutputEditText.setText(s);
//            }
//        });
    }

    private void textChange() {
        mOutputEditText.setText(mInputEditText.getText());
    }
}
