package com.mirim.hey_dude;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hey_dude.R;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.navigation.NavigationBarView;
import com.mirim.hey_dude.friendRecyclerView.FriendItem;
import com.mirim.hey_dude.friendRecyclerView.MyRecyclerAdapter;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    SearchView search_view;
    RecyclerView mRecyclerView;
    private ArrayList<FriendItem> mFriendList;
    private MyRecyclerAdapter myRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mRecyclerView = findViewById(R.id.recyclerView);
        search_view = findViewById(R.id.search_view);
        search_view.clearFocus();
        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mFriendList = new ArrayList<>();
        
        // item 추가
        for (int i = 0; i < 20; i++) {
            mFriendList.add(new FriendItem("우우우", "우우웅", "R.drawable.ic_baseline_add_24"));
        }
        mFriendList.add(new FriendItem("에엥", "엉", "R.drawable.ic_baseline_add_24"));

        myRecyclerAdapter = new MyRecyclerAdapter(mFriendList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(myRecyclerAdapter);

    }
   

}