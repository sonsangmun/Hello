package com.example.smson.hello.parsing.json;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.smson.hello.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class ParsingExamActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = ParsingExamActivity.class.getSimpleName();
    private RecipeAdapter mAdapter;
    private ArrayList<RecipeInfo> mRecipeInfoList;
    private ListView mListView;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mListView.setAdapter(mAdapter);
        }
    };

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parsing_exam);
        mProgressBar = (ProgressBar) findViewById(R.id.progress);

        mListView = (ListView) findViewById(R.id.lv_recipe);
        mListView.setOnItemClickListener(this);

        mRecipeInfoList = new ArrayList<>();

        // 0. ProgressDialog 띄우기
        mProgressBar.setVisibility(View.VISIBLE);

        // 1. web에 데이터 요청. 무조건 Thread사용해야 함
        Thread thread = new Thread() {
            @Override
            public void run() {
                HttpClient httpClient = new DefaultHttpClient();

                String urlString = "https://app.rakuten.co.jp/services/api/Recipe/CategoryList/20121121?format=json&applicationId=1003578488238363358";
                try {
                    URI url = new URI(urlString);

                    HttpGet httpGet = new HttpGet();
                    httpGet.setURI(url);

                    // 응답을 받는 객체
                    HttpResponse response = httpClient.execute(httpGet);
                    String responseString = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);

                    // 2. 요청 받은 내용을 파싱
                    jsonParsing(responseString);

                    // final. ProgressDialog 를 dismiss()
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mProgressBar.setVisibility(View.GONE);
                        }
                    });

                } catch (URISyntaxException e) {
                    Log.e(TAG, e.getLocalizedMessage());
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    Log.e(TAG, e.getLocalizedMessage());
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.e(TAG, e.getLocalizedMessage());
                    e.printStackTrace();
                }

            }
        };

        thread.start();

    }

    private void jsonParsing(String responseString) {
        try {
            // JSON 구문을 파싱해서 JSONArray 객체를 생성
            JSONObject jsonObject = new JSONObject(responseString);
            JSONObject jsonResult = jsonObject.getJSONObject("result");

            // 역슬래쉬 빠진 것으로
            String jsonLarge = jsonResult.getString("large");
            JSONArray jsonArray = new JSONArray(jsonLarge);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                String categoryId = object.getString("categoryId");
                String categoryName = object.getString("categoryName");
                String categoryUrl = object.getString("categoryUrl");

                RecipeInfo recipeInfo = new RecipeInfo(categoryId, categoryName,
                        categoryUrl);
                mRecipeInfoList.add(recipeInfo);
            }

            // 3. 파싱한 데이터를 어댑터에 설정
            mAdapter = new RecipeAdapter(ParsingExamActivity.this, mRecipeInfoList);

            // 4. 어댑터를 리스트 뷰에 설정 (UI 갱신)
            mHandler.sendEmptyMessage(0);

            Log.d(TAG, mRecipeInfoList.toString());

        } catch (JSONException e) {
            Log.d("tag", "Parse Error");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Toast.makeText(getApplicationContext(), "url : " +
        // mRecipeInfoList.get(position).getUrl(),
        // Toast.LENGTH_SHORT).show();

        // url
        String url = mRecipeInfoList.get(position).getUrl();

        Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
        intent.putExtra("url", url); // url 을 intent 싫어서 activity를 호출
        startActivity(intent);
    }
}
