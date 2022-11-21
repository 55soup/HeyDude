package com.mirim.hey_dude;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.example.hey_dude.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mirim.hey_dude.friendRecyclerView.FriendItem;
import com.mirim.hey_dude.nodejsLogin.User;
import com.mirim.hey_dude.userRecyclerView.UserAdapter;
import com.mirim.hey_dude.userRecyclerView.UserItem;

import java.util.ArrayList;

public class UserlistActivity extends AppCompatActivity {
    EditText edit_search;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<UserItem> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true); //리사이클러뷰 기존성능 강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); //User객체를 담을 arraylist

        database = FirebaseDatabase.getInstance(); //파이어베이스 데이터베이스 연동
        databaseReference = database.getReference("users");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // 파이어베이스 데이터베이스의 데이터를 받아오는 곳
                arrayList.clear(); //기존 배열리스트가 존재하지 않게 초기화
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){ //반복문으로 데이터 List를 추춘해냄
                    UserItem user = snapshot.getValue(UserItem.class); // 만들어뒀던 User 객체에 데이터를 닫는다
                    arrayList.add(user); // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                }
                adapter.notifyDataSetChanged(); //리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //DB를 가져오던 중 에러 발생 시
                Log.e("UserListActivity", String.valueOf(error.toException())); //에러문 출력
            }
        });

        adapter = new UserAdapter(arrayList, this);
        recyclerView.setAdapter(adapter); // 리사이클러뷰에 어뎀터 넘기기

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