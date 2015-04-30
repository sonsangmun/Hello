package com.example.smson.hello.multimedia.music_player;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.smson.hello.R;

import java.io.IOException;

public class MusicPlayerActivity extends AppCompatActivity implements View.OnClickListener, MediaPlayer.OnCompletionListener, SeekBar.OnSeekBarChangeListener {

    private static final int REQUEST_CODE_AUDIO = 0;
    private static final int AUDIO_PLAY = 1;
    private static final int AUDIO_PAUSE = 0;
    private TextView mfileName;
    private ImageButton mPlayerButton;
    private ImageButton mFilePickButton;
    private SeekBar mPlayerBar;
    private int mPlayStat = 0;
    private MediaPlayer mAudioPlayer;
    private int mMaxAudioPoint;

    // 재생시 SeekBar 처리 핸들러
    public Handler mProgressHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (mAudioPlayer == null) return;
            try {
                if (mAudioPlayer.isPlaying()){
                    mPlayerBar.setProgress(mAudioPlayer.getCurrentPosition());
                    mProgressHandler.sendEmptyMessageDelayed(0, 100);
                }
            } catch (IllegalStateException e) {

            } catch (Exception e) {

            }
        }
    };


    private void init(){
        mfileName = (TextView) findViewById(R.id.tv_filename);
        mPlayerButton = (ImageButton) findViewById(R.id.ibtn_player);
        mFilePickButton = (ImageButton) findViewById(R.id.ibtn_audio_filepick);
        mPlayerBar = (SeekBar) findViewById(R.id.sb_playbar);
        mPlayerBar.setEnabled(false);

        mPlayerButton.setOnClickListener(this);
        mFilePickButton.setOnClickListener(this);
        mPlayerBar.setOnSeekBarChangeListener(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibtn_audio_filepick:
                audioFilePick();
                break;
            case R.id.ibtn_player:
                if(mAudioPlayer != null) pauseMusic();
                break;
        }
    }

    private void audioFilePick(){
        // FileChooser 사용
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("audio/*");
        startActivityForResult(Intent.createChooser(i, "파일선택..."), REQUEST_CODE_AUDIO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            // Audio
            Uri fileUri = data.getData();

            mPlayerBar.setEnabled(true);
            startMusic(fileUri);
        }
    }

    private void startMusic(Uri fileUri) {
        if (mAudioPlayer == null) mAudioPlayer = new MediaPlayer();
        else mAudioPlayer.reset();

//        mAudioPlayer.setOnCompletionListener(this);

        try {
            mAudioPlayer.setDataSource(fileUri.toString());
            mAudioPlayer.prepare();
            mMaxAudioPoint = mAudioPlayer.getDuration();
            mPlayerBar.setMax(mMaxAudioPoint);

            int maxMinPoint = mMaxAudioPoint / 1000 / 60;
            int maxSecPoint = (mMaxAudioPoint / 1000) % 60;

            mPlayerBar.setProgress(0);
            mAudioPlayer.start();
            mPlayStat = 1;
            playerButton();
            mProgressHandler.sendEmptyMessageDelayed(0, 100);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void pauseMusic(){
        if(mAudioPlayer.isPlaying()) {
            mAudioPlayer.pause();
            mPlayStat = 0;
            playerButton();
        } else {
            mAudioPlayer.start();
            mPlayStat = 1;
            playerButton();
        }
    }
    private void playerButton(){
        if(mPlayStat == 0) {
            // play 상태에서는 pause 를 할수 있도록 pause 버튼
            mPlayerButton.setImageResource(R.drawable.player_play);
        } else {
            // pause 상태에서는 paly를 할 수 있도록 play 버튼
            mPlayerButton.setImageResource(R.drawable.player_pause);
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        // SeekBar가 마지막 지점까지 도달한 경우
        mPlayStat = 1;
        playerButton();
        mAudioPlayer.stop();;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(fromUser) {
            mAudioPlayer.seekTo(mPlayerBar.getProgress());
            mAudioPlayer.start();
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        mPlayStat = 0;
        playerButton();
        mAudioPlayer.pause();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mPlayStat = 1;
        playerButton();
        mAudioPlayer.start();
    }
}
