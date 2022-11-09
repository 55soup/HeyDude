package com.mirim.hey_dude;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.view.KeyEventDispatcher;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.hey_dude.R;

import java.io.IOException;
import java.sql.Time;
import java.util.List;


public class Fragment2 extends Fragment {
    Button StartRecording, StopRecording, StartPlaying, StopPlaying;
    RecyclerView recyclerView;
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private String AudioSavaPath = null;

    public Fragment2() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_fragment2, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        StartRecording = (Button)v.findViewById(R.id.StartRecording);
        StopRecording = (Button)v.findViewById(R.id.StopRecording);
        StartPlaying = (Button)v.findViewById(R.id.StartPlaying);
        StopPlaying = (Button)v.findViewById(R.id.StopPlaying);


        //////////////////// recordView
        StartRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermissions() == true) {

                    AudioSavaPath = getContext().getExternalFilesDir("/").getAbsolutePath();
                    AudioSavaPath += "/" + "RecordExample_" + "audio.mp3";

                    mediaRecorder = new MediaRecorder();
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4); //레고팅 설정
                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
                    mediaRecorder.setOutputFile(AudioSavaPath);

                    try {
                        mediaRecorder.prepare();
                        mediaRecorder.start();
                        Toast.makeText(getActivity(), "녹음을 시작합니다", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }else{
                    ActivityCompat.requestPermissions(getActivity(), new String[]{
                            Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, 1);
                }
            }
        });

        StopRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        mediaRecorder.stop();
                        mediaRecorder.release();
                        mediaRecorder = null;
                Toast.makeText(getActivity(), "녹음을 중지합니다.", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(getContext(), "Start playing", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        StopPlaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null){
                   try{
                       mediaPlayer.stop();
                       mediaPlayer.release();
                       Toast.makeText(getContext(), "재생을 멈춥니다.", Toast.LENGTH_SHORT).show();
                   }catch(Exception e){

                   }
                }
            }
        });

        return v; // 반드시 추가

    }
    private boolean checkPermissions(){
        int first = ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.RECORD_AUDIO);
        int second = ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        return first == PackageManager.PERMISSION_GRANTED &&
                second == PackageManager.PERMISSION_GRANTED;

    }

}
