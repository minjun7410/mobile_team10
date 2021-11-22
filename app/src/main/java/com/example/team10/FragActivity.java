package com.example.team10;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.auth.User;

public class FragActivity extends AppCompatActivity {
    FriendActivity friendActivity;
    MainActivity mainActivity;
    UserActivity userActivity;

    private FragmentManager fragmentManager;

    int present_fragment = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag);

        fragmentManager = getSupportFragmentManager();

        mainActivity = new MainActivity();
        userActivity = new UserActivity();
        friendActivity = new FriendActivity();
        fragmentManager.beginTransaction().replace(R.id.container, mainActivity).commit();
        fragmentManager.beginTransaction().add(R.id.container, friendActivity).commit();
        fragmentManager.beginTransaction().add(R.id.container, userActivity).commit();

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
        if (index == 0) {
            if(mainActivity == null){
            }
            if(mainActivity != null) fragmentManager.beginTransaction().show(mainActivity).commit();
            if(friendActivity != null) fragmentManager.beginTransaction().hide(friendActivity).commit();
            if(userActivity != null) fragmentManager.beginTransaction().hide(userActivity).commit();
        }
        else if (index == 1) {
            if(friendActivity == null){
            }

            if(friendActivity != null) fragmentManager.beginTransaction().show(friendActivity).commit();
            if(mainActivity != null) fragmentManager.beginTransaction().hide(mainActivity).commit();
            if(userActivity != null) fragmentManager.beginTransaction().hide(userActivity).commit(); }
        else if (index == 2) {
            if(userActivity == null){
            }

            if(userActivity != null) fragmentManager.beginTransaction().show(userActivity).commit();
            if(friendActivity != null) fragmentManager.beginTransaction().hide(friendActivity).commit();
            if(mainActivity != null) fragmentManager.beginTransaction().hide(mainActivity).commit();;
        }
        present_fragment = index;
    }

}
