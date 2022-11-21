package com.mirim.hey_dude;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hey_dude.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mirim.hey_dude.friendRecyclerView.FriendItem;
import com.mirim.hey_dude.friendRecyclerView.MyRecyclerAdapter;

import java.util.ArrayList;


public class Fragment3 extends Fragment {
    RecyclerView mRecyclerView;
    FloatingActionButton floatBtnAdd;
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
        floatBtnAdd = (FloatingActionButton) v.findViewById(R.id.floatBtnAdd);


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

        // ---------------------recyclerview click event ---------------------
        myRecyclerAdapter.setOnItemClickListener(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClicked(int position, String data) {
                Toast.makeText(getActivity(),"Position: " + position + "Data: " + data, Toast.LENGTH_SHORT).show();
            }
        });
        // ---------------------recyclerview click event ---------------------

        // flotingBtn
        floatBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplication(), UserlistActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_up_enter, R.anim.slide_down_exit);
            }
        });

        return v; // 반드시 추가
    }

}
