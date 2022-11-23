package com.mirim.hey_dude;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.hey_dude.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

public class RecordActivity extends AppCompatActivity {
        private ImageButton recordBtn, playingBtn;
        private MediaRecorder mediaRecorder;
        private MediaPlayer mediaPlayer;
        private Button btnUpload;
        private String AudioSavaPath = null; // 저장소 위치
        private boolean isRecording = false; // 녹음하고 있는 여부
        private boolean isPlaying = false; // 플레이 여부
        private StorageReference mStorage; //firebase storage 불러오기
        private ProgressDialog mProgress;
        private EditText EditAudioMess;
        private String audioFileNameMess;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_record);
            recordBtn = findViewById(R.id.recordBtn);
            playingBtn = findViewById(R.id.playingBtn);
            playingBtn.setEnabled(false); //enable은 코드로 설정해야 동작.
            mStorage = FirebaseStorage.getInstance().getReference(); // firebase storage
            mProgress = new ProgressDialog(this); //upload dialog
            btnUpload = findViewById(R.id.btnUpload);
            EditAudioMess = findViewById(R.id.EditAudioMess);

//            AudioSavaPath = Environment.getExternalStorageDirectory().getAbsolutePath(); //작동안함
//            AudioSavaPath += "/recorded_audio.3gp";
            AudioSavaPath = getExternalFilesDir("/").getAbsolutePath();
            AudioSavaPath += "/" + "RecordExample_" + "audio.mp3";

            btnUpload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // edittext의 text audio파일 이름으로 설정
                    audioFileNameMess = EditAudioMess.getText().toString();
                    uploadAudio();
                }
            });
            //============================================================
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    100); // 반드시 녹음, 파일 접근 권한 허용.

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
//            AudioSavaPath = getExternalFilesDir("/").getAbsolutePath();
//            AudioSavaPath += "/" + "RecordExample_" + "audio.mp3"

            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4); //레고팅 설정 / firebase storage에서 실행 안됨
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS); //레코딩 설정 수정 / 실행됨.
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
//            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP); //내보낼때 레코딩 설정
            mediaRecorder.setOutputFile(AudioSavaPath);
//            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

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

    private void uploadAudio(){
        AlertDialog.Builder dlg = new AlertDialog.Builder(RecordActivity.this);
        dlg.setTitle("알림");
        dlg.setMessage("OO" + "님에게"+"\n보내시겠습니까?");
        dlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mProgress.setMessage("Uploading Audio ...");
                mProgress.show();

//                StorageReference filepath = mStorage.child("Audio").child(audioFileNameMess + ".mp3");
                StorageReference filepath = mStorage.child("Audio").child(" " + ".mp3");
                Uri uri = Uri.fromFile(new File(AudioSavaPath));
                filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        mProgress.dismiss();
                        Toast.makeText(RecordActivity.this, "오디오 파일 보내기 성공!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });
        dlg.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dlg.show(); //반드시 추가

    }


}
