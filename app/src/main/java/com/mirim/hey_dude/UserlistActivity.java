package com.mirim.hey_dude;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.hey_dude.R;
import com.mirim.hey_dude.friendRecyclerView.FriendItem;

import java.util.ArrayList;

public class UserlistActivity extends AppCompatActivity {
    EditText edit_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
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
//                filter(s.toString());
            }
        });
    }
//        private void filter(String text) {
//        ArrayList<FriendItem> filteredList = new ArrayList<>();
//
//        for (FriendItem item : mFriendList) {
//            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
//                filteredList.add(item);
//            }
//            // 찾는 유저가 없을 때는 어떻게?
//        }
//        myRecyclerAdapter.filterList(filteredList);
//    }
}