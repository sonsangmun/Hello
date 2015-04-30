package com.example.smson.hello.multimedia.video_player;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.smson.hello.R;

import java.io.IOException;

public class VideoPlayerActivity extends ActionBarActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_VIDEO = 1;
    private static final int VIDEO_PLAY = 1;
    private static final int VIDEO_PAUSE = 0;
    private TextView mfileName;
    private ImageButton mPlayerButton;
    private ImageButton mFilePickButton;
    private SeekBar mPlayerBar;
    private int mPlayStat = 0;
    private VideoView mVideoPlayer;
    private int mMaxVideoPoint;

    // 재생시 SeekBar 처리 핸들러
    public Handler mProgressHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (mVideoPlayer == null) return;
            try {
                if (mVideoPlayer.isPlaying()){
                    mPlayerBar.setProgress(mVideoPlayer.getCurrentPosition());
                    mProgressHandler.sendEmptyMessageDelayed(0, 100);
                }
            } catch (IllegalStateException e) {

            } catch (Exception e) {

            }
        }
    };

    private void init(){
        mfileName = (TextView) findViewById(R.id.tv_filename);
        mPlayerButton = (ImageButton) findViewById(R.id.btn_player);
        mFilePickButton = (ImageButton) findViewById(R.id.btn_video_file_pick);
        mPlayerBar = (SeekBar) findViewById(R.id.sb_playbar);
        mVideoPlayer = (VideoView) findViewById(R.id.vv_video_view);
        mPlayerBar.setEnabled(false);

        mPlayerButton.setOnClickListener(this);
        mFilePickButton.setOnClickListener(this);
        mPlayerBar.setOnSeekBarChangeListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibtn_audio_filepick:
                videoFilePick();
                break;
            case R.id.ibtn_player:
                if(mVideoPlayer != null) pauseVideo();
                break;
        }
    }

    private void pauseVideo() {
        if(mVideoPlayer.isPlaying()) {
            mVideoPlayer.pause();
            mPlayStat = 0;
            playerButton();
        } else {
            mVideoPlayer.start();
            mPlayStat = 1;
            playerButton();
        }
    }

    private void playerButton() {
        if(mPlayStat == 0) {
            // play 상태에서는 pause 를 할수 있도록 pause 버튼
            mPlayerButton.setImageResource(R.drawable.player_play);
        } else {
            // pause 상태에서는 paly를 할 수 있도록 play 버튼
            mPlayerButton.setImageResource(R.drawable.player_pause);
        }
    }

    private void videoFilePick() {
        // FileChooser 사용
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("video/*");
        startActivityForResult(Intent.createChooser(i, "파일선택..."), REQUEST_CODE_VIDEO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // Audio
            Uri fileUri = data.getData();

            mPlayerBar.setEnabled(true);
            startVideo(fileUri);
        }
    }

    private void startVideo(Uri fileUri) {
//        mAudioPlayer.setOnCompletionListener(this);

        mVideoPlayer.setVideoURI(fileUri);
        mVideoPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mMaxVideoPoint = mp.getDuration();
                mPlayerBar.setMax(mMaxVideoPoint);

                int maxMinPoint = mMaxVideoPoint / 1000 / 60;
                int maxSecPoint = (mMaxVideoPoint / 1000) % 60;

                mPlayerBar.setProgress(0);
                mVideoPlayer.start();
                mPlayStat = 1;
                playerButton();
                mProgressHandler.sendEmptyMessageDelayed(0, 100);
            }
        });
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        // SeekBar가 마지막 지점까지 도달한 경우
        mPlayStat = 1;
        playerButton();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(fromUser) {
            mVideoPlayer.seekTo(mPlayerBar.getProgress());
            mVideoPlayer.start();
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        mPlayStat = 0;
        playerButton();
        mVideoPlayer.pause();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mPlayStat = 1;
        playerButton();
        mVideoPlayer.start();
    }
}
