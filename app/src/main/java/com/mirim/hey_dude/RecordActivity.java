package com.mirim.hey_dude;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.hey_dude.R;

import java.io.IOException;

public class RecordActivity extends AppCompatActivity {
        private ImageButton recordBtn, playingBtn;
        private MediaRecorder mediaRecorder;
        private MediaPlayer mediaPlayer;
        private String AudioSavaPath = null;
        private boolean isRecording = false;
        private boolean isPlaying = false;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_record);
            recordBtn = findViewById(R.id.recordBtn);
            playingBtn = findViewById(R.id.playingBtn);
            playingBtn.setEnabled(false); //enable은 코드로 설정해야 동작.

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    100);

            recordBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isRecording){
                        // 현재 녹음o
                        isRecording=false;
                        StopRecording();
                        playingBtn.setEnabled(true);
                    }else{
                        //현재 녹음x
                        if(checkPermissions()){
                            isRecording=true;
                            startRecording();
                        }
                    }
                }
            });

            playingBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isPlaying){
                        //정지했을 때
                        StopAudio();
                    }else{
                        // 재생했을 때
                        PlayingAudio();
                    }
                }
            });
        }

    void startRecording(){
            //녹음시작
            recordBtn.setImageResource(R.drawable.record_ing);
            //파일 위치
            AudioSavaPath = getExternalFilesDir("/").getAbsolutePath();
            AudioSavaPath += "/" + "RecordExample_" + "audio.mp3";

            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4); //레고팅 설정
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            mediaRecorder.setOutputFile(AudioSavaPath);

            try {
                mediaRecorder.prepare();
                mediaRecorder.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    void StopRecording(){
        //녹음 중지
        try {
            recordBtn.setImageResource(R.drawable.record);
            mediaRecorder.stop();
        }catch(Exception e){

        } finally {
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }

    void PlayingAudio(){
        //재생했을 때
        isPlaying=true;
        playingBtn.setImageResource(R.drawable.btn_stop);
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(AudioSavaPath);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 오디오 길이 끝에 도달했을 경우.
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
              StopAudio();
            }
        });
    }

    void StopAudio(){
        //정지했을 때
        isPlaying=false;
        playingBtn.setImageResource(R.drawable.playbutton);
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    private boolean checkPermissions() { //permissions check
        int first = ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.RECORD_AUDIO);
        int second = ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        return first == PackageManager.PERMISSION_GRANTED &&
                second == PackageManager.PERMISSION_GRANTED;
    }
}
