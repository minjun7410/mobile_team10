package com.example.team10;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.auth.User;

public class FragActivity extends AppCompatActivity {
    FriendActivity friendActivity;
    MainActivity mainActivity;
    UserActivity userActivity;

    int present_fragment = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag);

        mainActivity = (MainActivity) getSupportFragmentManager().findFragmentById(R.id.fragment);
        friendActivity = new FriendActivity();
        userActivity = new UserActivity();
        //하단 네비게이션 메뉴의 아이템을 누를 때마다 해당 동작을 실행한다.
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.item_friend:
                    onFragmentChanged(1);
                    break;
                case R.id.item_main:
                    onFragmentChanged(0);
                    break;
                case R.id.item_info:
                    onFragmentChanged(2);
                    break;
            }
            return true;
        });
    }
    public void onFragmentChanged(int index) {
        if (index == present_fragment){}
        else if (index == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, mainActivity).commit();
        }
        else if (index == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, friendActivity).commit(); }
        else if (index == 2) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, userActivity).commit();
        }
        present_fragment = index;
    }

}
