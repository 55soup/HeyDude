package com.mirim.hey_dude;

 import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hey_dude.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class NavBar extends AppCompatActivity {
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    Fragment4 fragment4;
    SetAlarmFragment setAlarmFragment;
    BottomNavigationView bottomNavigationView;
    FloatingActionButton fab_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_bar);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();
        setAlarmFragment = new SetAlarmFragment();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fab_btn = findViewById(R.id.FloatBtn);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.alarm:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
                        return true;

                    case R.id.micro:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment2).commit();
                        return true;

                    case R.id.addDude:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment3).commit();
                        return true;

                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment4).commit();
                        return true;
                }
                return false;
            }
        });

        fab_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                setAlarmFragment = new SetAlarmFragment();
//                getSupportFragmentManager().beginTransaction().replace(R.id.container, setAlarmFragment).commit();
//                overridePendingTransition(R.anim.slide_up_enter, R.anim.slide_down_exit);

                //액티비티 이동 시 화면 전환 애니메이션
                Intent intent = new Intent(getApplicationContext(), ActivityAlarm.class);
                finishAffinity(); //쌓인 화면스택 제거
                startActivity(intent);
            }
        });
    }

}