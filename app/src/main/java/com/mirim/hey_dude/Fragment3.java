package com.mirim.hey_dude;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hey_dude.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mirim.hey_dude.userRecyclerView.UserAdapter;
import com.mirim.hey_dude.userRecyclerView.UserItem;

import java.util.ArrayList;


public class Fragment3 extends Fragment{
//    FloatingActionButton floatBtnAdd;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private UserAdapter userAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<UserItem> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

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
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
//        floatBtnAdd = (FloatingActionButton) v.findViewById(R.id.floatBtnAdd);

        recyclerView.setHasFixedSize(true); //리사이클러뷰 기존성능 강화
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); //User객체를 담을 arraylist
        userAdapter = new UserAdapter(arrayList, getContext());
        adapter = new UserAdapter(arrayList, getContext());
        recyclerView.setAdapter(adapter); // 리사이클러뷰에 어뎀터 넘기기

        // 파이어베이스 데이터베이스 연동
        database = FirebaseDatabase.getInstance();
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

        // ============================================================

        // ============================================================
//        // flotingBtn
//        floatBtnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity().getApplication(), UserlistActivity.class);
//                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.slide_up_enter, R.anim.slide_down_exit);
//            }
//        });

        return v;

    }

}
