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

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.hey_dude.R;
import com.mirim.hey_dude.recordRecyclerView.RecordAdapter;
import com.mirim.hey_dude.recordRecyclerView.RecordItem;

import java.io.IOException;
import java.util.ArrayList;
// udpate
public class Fragment2 extends Fragment {
    Button StopRecording;
    RecyclerView recordRecyclerView;
    private ArrayList<RecordItem> mRecordList;
    private RecordAdapter myRecordAdapter;

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
        recordRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        StopRecording = (Button)v.findViewById(R.id.StopRecording);

        //////////////////// recyclerview
        recordRecyclerView.setHasFixedSize(true);
        recordRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mRecordList = new ArrayList<>();

        // recyclerview 데이터 추가
        for (int i = 0; i < 20; i++)
            mRecordList.add(new RecordItem("09:30", "하진", "4분"));
        
        myRecordAdapter = new RecordAdapter(mRecordList);
        recordRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recordRecyclerView.setAdapter(myRecordAdapter);

        // ---------------------recyclerview click event ---------------------
        myRecordAdapter.setOnItemClickListener(new RecordAdapter.OnItemClickListener() {
            @Override
            public void OnItemClicked(int position, String data) {
                Toast.makeText(getActivity(),"Position: " + position + "Data: " + data, Toast.LENGTH_SHORT).show();
                DialogShow();
            }
        });
        // ---------------------recyclerview click event ---------------------
        //====================== recyclerview ======================


        //////////////////// recordView
        StopRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplication(), RecordActivity.class);
                startActivity(intent);
            }
        });

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

    // dialog출력
    void DialogShow(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("알림");
        builder.setMessage("김하진" + "님이"+"\n모닝콜을 원해요!\n수락하시겠습니까?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

}
