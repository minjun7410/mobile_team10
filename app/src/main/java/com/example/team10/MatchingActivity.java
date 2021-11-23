package com.example.team10;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MatchingActivity extends AppCompatActivity {
    ListView FriendListView;
    ArrayList<Friend> friendlist;
    FriendAdapter friendAdapter;

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching);
    }

    @Override
    public void onStart() {
        super.onStart();

        FriendListView = findViewById(R.id.FriendListView);
        friendlist = new ArrayList<Friend>();
        friendlist.add(new Friend("감자"));
        friendlist.add(new Friend("고구마"));
        friendAdapter = new FriendAdapter(friendlist, getApplicationContext());
        FriendListView.setAdapter(friendAdapter);

        FriendListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = friendlist.get(i).getNickName();
                Intent intent = new Intent(MatchingActivity.this ,ChatRoomActivity.class);
                intent.putExtra("username",text);
                startActivity(intent);
            }
        });
    }
}