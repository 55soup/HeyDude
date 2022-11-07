package com.mirim.hey_dude;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.EditText;
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
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {
    EditText edit_search;
    RecyclerView mRecyclerView;
    private ArrayList<FriendItem> mFriendList;
    private MyRecyclerAdapter myRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mRecyclerView = findViewById(R.id.recyclerView);
        edit_search = findViewById(R.id.edit_search);
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

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mFriendList = new ArrayList<>();

        // item 추가
        for (int i = 0; i < 20; i++) {
            mFriendList.add(new FriendItem("선주", "우우웅", "R.drawable.ic_baseline_add_24"));
        }
        mFriendList.add(new FriendItem("하진", "엉", "R.drawable.ic_baseline_add_24"));
        mFriendList.add(new FriendItem("전진", "엉", "R.drawable.ic_baseline_add_24"));

        myRecyclerAdapter = new MyRecyclerAdapter(mFriendList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(myRecyclerAdapter);

    }
    private void filter(String text){
        ArrayList<FriendItem> filteredList = new ArrayList<>();

        for(FriendItem item : mFriendList){
            if(item.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        myRecyclerAdapter.filterList(filteredList);
    }

}