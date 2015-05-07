package com.example.smson.hello;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.smson.hello.Thread.AsyncTaskActivity;
import com.example.smson.hello.Thread.LoginActivity;
import com.example.smson.hello.Thread.ThreadActivity;
import com.example.smson.hello.Thread.TimerActivity;
import com.example.smson.hello.activity.ActivityExamActivity;
import com.example.smson.hello.activity.EditTextActivity;
import com.example.smson.hello.activity.FirstActivity;
import com.example.smson.hello.activity.FrameLayoutActivity;
import com.example.smson.hello.activity.RelativeLayoutExamActivity;
import com.example.smson.hello.activity.SecondActivity;
import com.example.smson.hello.activity.TableLayoutActivity;
import com.example.smson.hello.camera.builtin.CameraBuiltinActivity;
import com.example.smson.hello.camera.intent.CameraIntentActivity;
import com.example.smson.hello.challenge.challenge01.ImageExamActivity;
import com.example.smson.hello.challenge.challenge02.SMSActivity;
import com.example.smson.hello.challenge.challenge04.MainActivity;
import com.example.smson.hello.challenge.challenge05.challenge05;
import com.example.smson.hello.challenge.challenge06.challenge06Activity;
import com.example.smson.hello.challenge.challenge07_08.CalendarActivity;
import com.example.smson.hello.chat.client.ChatActivity;
import com.example.smson.hello.contentprovider.ContentActivity;
import com.example.smson.hello.database.dbtest1.TestDataBaseActivity;
import com.example.smson.hello.database.dbtest2.DbManagerActivity;
import com.example.smson.hello.file_manager.FileManagerActivity;
import com.example.smson.hello.intent.ActionPickActivity;
import com.example.smson.hello.listview.GridActivity2Activity;
import com.example.smson.hello.listview.LiveView02Activity;
import com.example.smson.hello.multimedia.MediaPlayerActivity;
import com.example.smson.hello.multimedia.media_file.MediaFileListActivity;
import com.example.smson.hello.multimedia.music_player.MusicPlayerActivity;
import com.example.smson.hello.multimedia.video_player.VideoPlayerActivity;
import com.example.smson.hello.other.TestSampleActivity;
import com.example.smson.hello.other.bitmap.BitmapMainActivity;
import com.example.smson.hello.other.event.TouchEventActivity;
import com.example.smson.hello.other.file_list.FileList;
import com.example.smson.hello.other.gallery.GalleryActivity;
import com.example.smson.hello.other.google_map.MapActivity;
import com.example.smson.hello.other.graphic.GraphicActivity;
import com.example.smson.hello.other.imagegallery.ImageGallery2Activity;
import com.example.smson.hello.other.service.KitchenTimerActivity;
import com.example.smson.hello.other.spinner.SpinnerExamActivity;
import com.example.smson.hello.parsing.json.ParsingExamActivity;
import com.example.smson.hello.parsing.xml.XmlParsing;
import com.example.smson.hello.surfaceview.GameActivity;
import com.example.smson.hello.tour_list.TourList;


public class Sub2Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final String[] ACTIVITY_ITEMS = {
            "Activity 예제",
            "EditTextActivity",
            "FirstActivity",
            "FrameLayout",
            "RelativeLayout",
            "SecondActivity",
            "TableLayout"
    };
    private static final Class[] ACTIVITY_CLASSES = {
            ActivityExamActivity.class,
            EditTextActivity.class,
            FirstActivity.class,
            FrameLayoutActivity.class,
            RelativeLayoutExamActivity.class,
            SecondActivity.class,
            TableLayoutActivity.class
    };
    private static final String[] CHALLENGE_ITEMS = {
            "Challenge01 이미지 위/아래 나타내기",
            "Challenge02 SMS 문자발송창 구성",
            "Challenge04 AlertDialog창 띄우기",
            "Challenge05 시간, 날짜 선택 입력",
            "Challenge06 웹브라우저",
            "Challenge07~08 달력"
    };
    private static final Class[] CHALLENGE_CLASSES = {
            ImageExamActivity.class,
            SMSActivity.class,
            MainActivity.class,
            challenge05.class,
            challenge06Activity.class,
            CalendarActivity.class
    };
    private static final String[] LISTVIEW_ITEMS = {
            "Grid",
            "LiveView",
    };
    private static final Class[] LISTVIEW_CLASSES = {
            GridActivity2Activity.class,
            LiveView02Activity.class
    };
    private static final String[] THREAD_ITEMS = {
            "Login",
            "AsyncTask",
            "ThreadActivity",
            "TimerActivity"
    };
    private static final Class[] THREAD_CLASSES = {
            LoginActivity.class,
            AsyncTaskActivity.class,
            ThreadActivity.class,
            TimerActivity.class
    };
    private static final String[] PARSIGN_ITEMS = {
            "JSON parsing",
            "XML parsign"
    };
    private static final Class[] PARSING_CLASSES = {
            ParsingExamActivity.class,
            XmlParsing.class
    };
    private static final String[] OTHER_ITEMS = {
            "TestSampleActivity",
            "GoogleMap",
            "Bitmap",
            "Graphic",
            "ImageGallery",
            "Event",
            "spinner",
            "Service",
            "gallery",
            "SurfaceView",
            "File List"
    };
    private static final Class[] OTHER_CLASSES = {
            TestSampleActivity.class,
            MapActivity.class,
            BitmapMainActivity.class,
            GraphicActivity.class,
            ImageGallery2Activity.class,
            TouchEventActivity.class,
            SpinnerExamActivity.class,
            KitchenTimerActivity.class,
            GalleryActivity.class,
            GameActivity.class,
            FileList.class
    };
    private static final String[] TOURLIST_ITEMS = {
            "Tour List"
    };
    private static final Class[] TOURLIST_CLASSES = {
            TourList.class
    };
    private static final String[] INTENT_ITEMS = {
            "Intent"
    };
    private static final Class[] INTENT_CLASSES = {
            ActionPickActivity.class
    };
    private static final String[] CHAT_ITEMS = {
            "Chat Client"
    };
    private static final Class[] CHAT_CLASSES = {
            ChatActivity.class
    };
    private static final String[] CAMERA_ITEMS = {
            "Intent Camera",
            "Builtin Camera"
    };
    private static final Class[] CAMERA_CLASSES = {
            CameraIntentActivity.class,
            CameraBuiltinActivity.class
    };
    private static final String[] DATABASE_ITEMS = {
            "TestDataBaseActivity",
            "DbManager"
    };
    private static final Class[] DATABASE_CLASSES = {
            TestDataBaseActivity.class,
            DbManagerActivity.class
    };
    private static final String[] MEDIA_ITEMS = {
            "플레이어",
            "음악 플레이어",
            "비디오 플레이어",
            "미디어 파일목록"
    };
    private static final Class[] MEDIA_CLASSES = {
            MediaPlayerActivity.class,
            MusicPlayerActivity.class,
            VideoPlayerActivity.class,
            MediaFileListActivity.class
    };
    private static final String[] CONTENT_ITEMS = {
            "주소록"
    };
    private static final Class[] CONTENT_CLASSES = {
            ContentActivity.class
    };
    private static final String[] FILE_ITEMS = {
            "파일 관리자"
    };
    private static final Class[] FILE_CLASSES = {
            FileManagerActivity.class
    };
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        mListView = (ListView) findViewById(R.id.listView);

        if (getItems() != null) {

            ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                    android.R.layout.simple_list_item_1, getItems().first);

            mListView.setAdapter(adapter);
            mListView.setOnItemClickListener(this);
        }
    }

    private Pair<String[], Class[]> getItems() {
        Pair<String[], Class[]> result = null;

        String menu = getIntent().getStringExtra("menu");
        switch (menu) {
            case "Activity":
                result = new Pair(ACTIVITY_ITEMS, ACTIVITY_CLASSES);
                break;
            case "Challenge":
                result = new Pair(CHALLENGE_ITEMS, CHALLENGE_CLASSES);
                break;
            case "Other":
                result = new Pair(OTHER_ITEMS, OTHER_CLASSES);
                break;
            case "Parsing":
                result = new Pair(PARSIGN_ITEMS, PARSING_CLASSES);
                break;
            case "TourList":
                result = new Pair(TOURLIST_ITEMS, TOURLIST_CLASSES);
                break;
            case "ListView":
                result = new Pair(LISTVIEW_ITEMS, LISTVIEW_CLASSES);
                break;
            case "Thread":
                result = new Pair(THREAD_ITEMS, THREAD_CLASSES);
                break;
            case "Intent":
                result = new Pair(INTENT_ITEMS, INTENT_CLASSES);
                break;
            case "Database":
                result = new Pair(DATABASE_ITEMS, DATABASE_CLASSES);
                break;
            case "Chat":
                result = new Pair(CHAT_ITEMS, CHAT_CLASSES);
                break;
            case "Camera":
                result = new Pair(CAMERA_ITEMS, CAMERA_CLASSES);
                break;
            case "Media":
                result = new Pair(MEDIA_ITEMS, MEDIA_CLASSES);
                break;
            case "Content Provider":
                result = new Pair(CONTENT_ITEMS, CONTENT_CLASSES);
                break;
            case "File Manager":
                result = new Pair(FILE_ITEMS, FILE_CLASSES);
                break;
        }
        return result;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(getApplicationContext(), getItems().second[position]));

        // Class.forName 으로 정보 가져오는 방법
        // Class c = null;
        // try {
        // c =
        // Class.forName("com.suwonsmartapp.hello.activity.ActivityExamActivity");
        // startActivity(new Intent(getApplicationContext(), c));
        // } catch (ClassNotFoundException e) {
        // Toast.makeText(getApplicationContext(), "없다",
        // Toast.LENGTH_SHORT).show();
        // }
    }
}
