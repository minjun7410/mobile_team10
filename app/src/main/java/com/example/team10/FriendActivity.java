package com.example.team10;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FriendActivity extends AppCompatActivity {

    ListView FriendListView;
    ArrayList<Friend> friendlist;
    FriendAdapter friendAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);

        Log.d("FriendActivity", "Successful");

        FriendListView = findViewById(R.id.FriendListView);
        friendlist = new ArrayList<Friend>();
        friendlist.add(new Friend("hide on bush"));
        friendlist.add(new Friend("Thal"));
        friendlist.add(new Friend("추남신명철"));
        friendAdapter = new FriendAdapter(friendlist, getApplicationContext());
        FriendListView.setAdapter(friendAdapter);
    }
}
