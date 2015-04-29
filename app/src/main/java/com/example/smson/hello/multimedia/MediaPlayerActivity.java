package com.example.smson.hello.multimedia;

import java.io.IOException;
import java.util.logging.Handler;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.smson.hello.R;

public class MediaPlayerActivity extends AppCompatActivity implements View.OnClickListener, MediaPlayer.OnCompletionListener {

    private static final int REQUEST_CODE_AUDIO = 0;
    private static final int REQUEST_CODE_VIDEO = 1;
    private static final String TAG = MediaPlayerActivity.class.getSimpleName();

    private ImageButton mBtnAudioFilePick;    // 파일 선택
    private ImageButton mBtnVideoFilePick;    // 파일 선택
    private ImageButton mBtnPlayer;       // 시작 / 정지
    private int mStat = 0;
    private int mAudioStat = 0;
    private int mVideoStat = 0;
    private TextView mFileName;
    private SeekBar mPlayProgressBar;

    // 플레이어
    private MediaPlayer mMediaPlayer;
    private VideoView mVideoView;

    // 재생시 SeekBar 처리
    // http://androiddeveloper.tistory.com/91
    public Handler mProgressHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (mMediaPlayer == null) return;
            try {
                if (mMediaPlayer.isPlaying()){
                    mPlayProgressBar.setProgress(mMediaPlayer.getCurrentPosition());
                    mProgressHandler.sendEmptyMessageDelayed(0, 100);
                }
            } catch (IllegalStateException e) {

            } catch (Exception e) {

            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);

        // 레이아웃 초기화, 이벤트 연결
        init();

        // FileChooser 로 선택되어진 경우
        if (getIntent() != null) {
            Uri uri = getIntent().getData();
            if (uri != null) {
                startMusic(uri);
            }
        }
    }

    private void init() {
        mBtnAudioFilePick = (ImageButton) findViewById(R.id.btn_audioFilePick);
        mBtnVideoFilePick = (ImageButton) findViewById(R.id.btn_videoFilePick);
        mBtnPlayer = (ImageButton) findViewById(R.id.btn_player);
        mVideoView = (VideoView) findViewById(R.id.videoView);
        mFileName = (TextView) findViewById(R.id.file_name);
        mPlayProgressBar = (SeekBar) findViewById(R.id.play_seekbard);

        mBtnAudioFilePick.setOnClickListener(this);
        mBtnVideoFilePick.setOnClickListener(this);
        mBtnPlayer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = null;
        switch (v.getId()) {
            case R.id.btn_audioFilePick:
                // FileChooser 사용
                i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("audio/*");
                startActivityForResult(Intent.createChooser(i, "파일선택..."), REQUEST_CODE_AUDIO);

                // 파일 선택창이 열린후 처리
                // 파일을 선택하면 onActivityResult 가 호출됨.
                mStat = 0;  // 현재 플레이는 음악 플레이
                // 음악 아이콘은 on으로 활성화
                mBtnAudioFilePick.setImageResource(R.drawable.audio_on);
                // 동영상 아이콘은 off로 비활성화
                mBtnVideoFilePick.setImageResource(R.drawable.video);
                // 음악 관련 플레이/멈춤 버튼 처리
                if(mMediaPlayer != null) viewSwitchSet(mAudioStat);
                break;
            case R.id.btn_videoFilePick:
                // FileChooser 사용
                i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("video/*");
                startActivityForResult(Intent.createChooser(i, "파일선택..."), REQUEST_CODE_VIDEO);

                // 파일 선택창이 열린후 처리
                // 파일을 선택하면 onActivityResult 가 호출됨.
                mStat = 1;  // 현재 플레이는 동영상 플레이
                // 동영상 아이콘은 on으로 활성화
                mBtnVideoFilePick.setImageResource(R.drawable.video_on);
                // 음악 아이콘은 off로 비활성화
                mBtnAudioFilePick.setImageResource(R.drawable.audio);
                // 동영상 관련 플레이/멈춤 버튼 처리
                if(mVideoView != null) viewSwitchSet(mVideoStat);
                break;
            case R.id.btn_player:
                if(mStat == 0) {
                    // 상태가 음악인 경우 음악관련 처리
                    playerAudio();
                } else {
                    // 상태가 동영상인 경우 동영상관련 처리
                    playerVideo();
                }
                break;
        }
    }

    private void playerVideo() {
        if (mVideoView != null) {
            if (mVideoStat == 0) {
                // 현 상태가 멈춤이면 플레이를 하고 상태값 플레이로 변경
                mVideoStat = 1;
                statVideoSet(mVideoStat);

                mAudioStat = 0;
                mMediaPlayer.pause();
            } else {
                // 현 상태가 플레이면 멈춤을 하고 상태값 멈춤으로 변경
                mVideoStat = 0;
                statVideoSet(mVideoStat);
            }
            // 화면상 현 상태의 스위치 버튼 표시
            Log.d(TAG, "mVideoStat : " + mVideoStat);
        }
    }

    private void playerAudio() {
        if (mMediaPlayer != null) {
            if (mAudioStat == 0) {
                // 현 상태가 멈춤이면 상태값을 플레이로
                mAudioStat = 1;
                statAudioSet(mAudioStat);

                mVideoStat = 0;
                mVideoView.pause();
            } else {
                // 현 상태가 플레이면 멈춤을 하고 상태값 멈춤으로 변경
                mAudioStat = 0;
                statAudioSet(mAudioStat);
            }
            Log.d(TAG, "mAudioStat : " + mAudioStat);
        }
    }

    // 스위치 상태 변경
    private void viewSwitchSet(int sw){
        if(sw == 0) {
            mBtnPlayer.setImageResource(R.drawable.player_play);
        } else {
            mBtnPlayer.setImageResource(R.drawable.player_pause);
        }
    }

    // video 상태 변경
    private void statVideoSet(int stat) {
        if(stat == 0) {
            mVideoView.pause();
            viewSwitchSet(0);
        } else {
            mVideoView.start();
            viewSwitchSet(1);
        }
    }

    // audio 상태 변경
    private void statAudioSet(int stat) {
        if(stat == 0) {
            mMediaPlayer.pause();
            viewSwitchSet(0);
        } else {
            mMediaPlayer.start();
            viewSwitchSet(1);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_AUDIO && resultCode == RESULT_OK) {
            // Audio
            Uri fileUri = data.getData();

            startMusic(fileUri);
            mBtnPlayer.setImageResource(R.drawable.player_pause);
            mAudioStat = 1;
            if(mVideoView != null) {
                mVideoView.pause();
                mVideoStat = 0;
            }
//            Intent intent = new Intent(getApplicationContext(), MusicService.class);
//            intent.putExtra("uri", fileUri);
//            startService(intent);

//            mBtnAudioFilePick.setText(fileUri.getPath());
            mFileName.setText(fileUri.getPath());
        } else if (requestCode == REQUEST_CODE_VIDEO && resultCode == RESULT_OK) {
            // Video
            Uri fileUri = data.getData();

            startVideo(fileUri);
            mBtnPlayer.setImageResource(R.drawable.player_pause);
            mVideoStat = 1;
            if(mMediaPlayer != null) {
                mMediaPlayer.pause();
                mAudioStat = 0;
            }

//            mVideoView.setVideoURI(fileUri);
//            mVideoView.start();

//            mBtnVideoFilePick.setText(fileUri.getPath());
            mFileName.setText(fileUri.getPath());
        }

    }

    private void startMusic(Uri fileUri) {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }

        try {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(getApplicationContext(), fileUri);
            mMediaPlayer.prepare();
            mMediaPlayer.start();

            mProgressHandler.sendEmptyMessageDelayed(0, 100);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mFileName.setText(fileUri.getPath());
    }

    private void startVideo(Uri fileUri) {
        mVideoView.setVideoURI(fileUri);
        mVideoView.start();

        mFileName.setText(fileUri.getPath());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            Toast.makeText(getApplicationContext(), "볼륨 다운", Toast.LENGTH_SHORT).show();
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            Toast.makeText(getApplicationContext(), "볼륨 업", Toast.LENGTH_SHORT).show();
        }

        return super.onKeyDown(keyCode, event);
    }

    public String explode(String word, String cutChar) {
        return "";
    }
}
