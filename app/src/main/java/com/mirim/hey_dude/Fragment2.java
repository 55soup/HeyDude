package com.mirim.hey_dude;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hey_dude.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mirim.hey_dude.recordRecyclerView.RecordAdapter;
import com.mirim.hey_dude.recordRecyclerView.RecordItem;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
// udpate
public class Fragment2 extends Fragment {
    Button StopRecording;
    RecyclerView recordRecyclerView;
    private ArrayList<RecordItem> mRecordList;
    private RecordAdapter myRecordAdapter;
    Button btnDownload;

    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private String AudioSavaPath = null;

    private StorageReference mStorage; //firebase storage 불러오기
    File localFile = null;

    public Fragment2() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_fragment2, container, false);
        recordRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        btnDownload = (Button) v.findViewById(R.id.btnDownload);
        //////////////////// recyclerview
        recordRecyclerView.setHasFixedSize(true);
        recordRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mRecordList = new ArrayList<>();

        // recyclerview 데이터 추가
        mRecordList.add(new RecordItem("10:30", "킴.", "1분"));
        mRecordList.add(new RecordItem("10:30", "쭈쭈", "1분"));
        for (int i = 0; i < 20; i++)
            mRecordList.add(new RecordItem("09:30", "하진", "4분"));
        
        myRecordAdapter = new RecordAdapter(mRecordList);
        recordRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recordRecyclerView.setAdapter(myRecordAdapter);

        // ------------------------ 다운로드 ------------------------
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startdownload();
            }
        });
        // ------------------------ 다운로드 ------------------------
        // ---------------------recyclerview click event ---------------------
        myRecordAdapter.setOnItemClickListener(new RecordAdapter.OnItemClickListener() {
            @Override
            public void OnItemClicked(int position, String data) {
//                Toast.makeText(getActivity(),"Position: " + position + "Data: " + data, Toast.LENGTH_SHORT).show();
                DialogShow(position);
            }
        });
        // ---------------------recyclerview click event ---------------------
        //====================== recyclerview ======================
        return v; // 반드시 추가

    }


    // 녹음, 파일 접근허락
    private boolean checkPermissions(){
        int first = ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.RECORD_AUDIO);
        int second = ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        return first == PackageManager.PERMISSION_GRANTED &&
                second == PackageManager.PERMISSION_GRANTED;

    }

    public void removeItem(int position){
        // recyclerview item을 삭제하는 코드
        mRecordList.remove(position);
        recordRecyclerView.invalidate();
        recordRecyclerView.getAdapter().notifyDataSetChanged();
    }

    // dialog출력
    void DialogShow(int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setIcon(R.drawable.alarm_icon);
        builder.setTitle(" ");
        builder.setMessage("김하진" + "님이"+"\n모닝콜을 원해요!\n수락하시겠습니까?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity().getApplication(), RecordActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_up_enter, R.anim.slide_down_exit);
                // 클릭 후 item항목 삭제하기
                removeItem(position);
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                MyFirebaseMessaging();
//                FcmNotificationsSender notificationsSender = new FcmNotificationsSender(
//                    "ffwJXRKXRXC-pxoD7OxEDs:APA91bFmu8-XZ-wPSZ0S1gTB9r2YuKZLm7qOs3myk5_0CaN6sxc217Md635oIOVJXsnhrpaqbow9qhPnrIe_VpFKliKWJ5eMc0GRcx4quTSxtqXO13xpbKOn-b9nBmSiu5DcwHDh1mC_",
//                    "ㅎㅇ",
//                    "ㅎㅇ",
//                    getActivity().getApplicationContext(),
//                    getActivity());
                //download
            }
        });
        builder.show();
    }
    public void MyFirebaseMessaging() {
        Task<String> token = FirebaseMessaging.getInstance().getToken();
        token.addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if(task.isSuccessful()){
                    Log.d("FCM Token", task.getResult());
                }
            }
        });
    }
    private void startdownload() {
        // firebase storage에 있는 audio 파일 다운받기.
        mStorage = FirebaseStorage.getInstance().getReference(); // firebase storage
        StorageReference down =  mStorage.child("Audio").child("하이 너를 위한 모닝콜.mp3");

        try {
            localFile = File.createTempFile("Audio", "mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }

        down.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getContext(),"Downloded",Toast.LENGTH_SHORT).show();
                //재생
                mediaPlayer = new MediaPlayer();
                try {
                    Toast.makeText(getContext(),"mp3 재생",Toast.LENGTH_SHORT).show();
                    mediaPlayer.setDataSource(localFile.getPath());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });

    }

}
