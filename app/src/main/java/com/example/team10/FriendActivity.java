package com.example.team10;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FriendActivity extends Fragment {

    ListView FriendListView;
    ArrayList<Friend> friendlist;
    FriendAdapter friendAdapter;
    Button chatBtn;
    public static Context mContext;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_friend, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        chatBtn = (Button) getActivity().findViewById(R.id.chatBtn);
        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickChattingBtn(v);
            }
        });

        FriendListView = getView().findViewById(R.id.FriendListView);
        friendlist = new ArrayList<Friend>();
        friendlist.add(new Friend("hide on bush"));
        friendlist.add(new Friend("송민준"));
        friendlist.add(new Friend("CloudTemplar KR"));
        friendAdapter = new FriendAdapter(friendlist, getActivity().getApplicationContext());
        FriendListView.setAdapter(friendAdapter);

        FriendListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = friendlist.get(i).getNickName();
                Intent intent = new Intent(getView().getContext(),ChatRoomActivity.class);
                intent.putExtra("username",text);
                startActivity(intent);
            }
        });
    }
    public void addFriendView(String name){
        friendlist.add(new Friend(name));
    }
    public void onClickChattingBtn(View view){
        Intent intent = new Intent(getView().getContext(), ChatActivity.class);
        startActivity(intent);
    }
}
