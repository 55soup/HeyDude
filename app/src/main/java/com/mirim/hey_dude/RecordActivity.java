package com.mirim.hey_dude;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hey_dude.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class RecordActivity extends AppCompatActivity {

        private Button StartRecording, StopRecording, StartPlaying, StopPlaying;
        private MediaRecorder mediaRecorder;
        private MediaPlayer mediaPlayer;
        private String AudioSavaPath = null;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_record);

            StartRecording = findViewById(R.id.startRecord);
            StopRecording = findViewById(R.id.stopRecord);
            StartPlaying = findViewById(R.id.startPlaying);
            StopPlaying = findViewById(R.id.stopPlaying);

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    100);

            StartRecording.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (checkPermissions() == true) {

                        AudioSavaPath = getExternalFilesDir("/").getAbsolutePath();
                        AudioSavaPath += "/" + "RecordExample_" + "audio.mp3";
                        
                        mediaRecorder = new MediaRecorder();
                        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4); //레고팅 설정
                        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
                        mediaRecorder.setOutputFile(AudioSavaPath);

                        try {
                            mediaRecorder.prepare();
                            Toast.makeText(RecordActivity.this, "Recording started", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        mediaRecorder.start();

                    } else {
                        ActivityCompat.requestPermissions(RecordActivity.this, new String[]{
                                Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE
                        }, 1);
                    }
                }

            });

            StopRecording.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mediaPlayer) {
                        try {
                            mediaRecorder.stop();
                        }catch(Exception e){

                        } finally {
                            mediaRecorder.release();
                            mediaRecorder = null;
                        }
                    }
                }
            });

            StartPlaying.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mediaPlayer = new MediaPlayer();
                    try {
                        mediaPlayer.setDataSource(AudioSavaPath);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        Toast.makeText(RecordActivity.this, "Start playing", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            StopPlaying.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        Toast.makeText(RecordActivity.this, "Stopped playing", Toast.LENGTH_SHORT).show();
                    }
                }
            });


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
