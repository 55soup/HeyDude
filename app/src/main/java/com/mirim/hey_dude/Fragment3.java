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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.hey_dude.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mirim.hey_dude.friendRecyclerView.FriendItem;
import com.mirim.hey_dude.friendRecyclerView.MyRecyclerAdapter;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


public class Fragment3 extends Fragment {
    EditText edit_search;
    RecyclerView mRecyclerView;
    FloatingActionButton floatBtnModify;
    private ArrayList<FriendItem> mFriendList;
    private MyRecyclerAdapter myRecyclerAdapter;

    public Fragment3() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_fragment3, container, false);
        super.onCreate(savedInstanceState);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        edit_search = (EditText) v.findViewById(R.id.edit_search);
        floatBtnModify = (FloatingActionButton) v.findViewById(R.id.floatBtnModify);

        edit_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        // recyclerView
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mFriendList = new ArrayList<>();

        // item 추가
        for (int i = 0; i < 20; i++) {
            mFriendList.add(new FriendItem("선주", "우우웅", "R.drawable.ic_baseline_add_24"));
        }
        mFriendList.add(new FriendItem("하진", "엉", "R.drawable.ic_baseline_add_24"));
        mFriendList.add(new FriendItem("전진", "엉", "R.drawable.ic_baseline_add_24"));

        myRecyclerAdapter = new MyRecyclerAdapter(mFriendList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mRecyclerView.setAdapter(myRecyclerAdapter);

        // flotingBtn
        floatBtnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
            }
        });

        return v; // 반드시 추가
    }

    private void filter(String text) {
        ArrayList<FriendItem> filteredList = new ArrayList<>();

        for (FriendItem item : mFriendList) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
            // 찾는 유저가 없을 때는 어떻게?
        }
        myRecyclerAdapter.filterList(filteredList);
    }
}
