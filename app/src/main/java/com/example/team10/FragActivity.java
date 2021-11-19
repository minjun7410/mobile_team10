package com.example.team10;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

public class FragActivity extends AppCompatActivity {
    FriendActivity friendActivity;
    MainActivity mainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag);

        mainActivity = (MainActivity) getSupportFragmentManager().findFragmentById(R.id.fragment);
        friendActivity = new FriendActivity();
    }
    public void onFragmentChanged(int index) {
        if (index == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, mainActivity).commit();
        }
        else if (index == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, friendActivity).commit(); }
    }

}
