package com.mirim.hey_dude;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.view.KeyEventDispatcher;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.hey_dude.R;

import java.sql.Time;
import java.util.List;


public class Fragment1 extends Fragment {
    TimePicker timepicker;
    public Fragment1() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_fragment1, container, false);
        timepicker =(TimePicker) v.findViewById(R.id.timepicker);

        return v; // 반드시 추가
//        return inflater.inflate(R.layout.activity_fragment3, container, false);

    }



}
