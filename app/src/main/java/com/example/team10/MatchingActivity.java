package com.example.team10;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

                View inflatedView = getLayoutInflater().inflate(R.layout.friend_item, null);
                ImageView iUserIcon = (ImageView) inflatedView.findViewById(R.id.user_icon);
                TextView iUserNickname = (TextView) inflatedView.findViewById(R.id.user_nickname);
                TextView iUserTier = (TextView) inflatedView.findViewById(R.id.user_tier);
                TextView iUserLevel = (TextView) inflatedView.findViewById(R.id.user_level);
                TextView iUserManner = (TextView) inflatedView.findViewById(R.id.user_manner);
                TextView iUserMbti = (TextView) inflatedView.findViewById(R.id.user_mbti);
                iUserIcon.setImageBitmap(friendlist.get(i).user_icon);
                iUserNickname.setText(friendlist.get(i).user_nickname);
                iUserTier.setText("Tier: "+friendlist.get(i).user_tier);
                iUserLevel.setText("Level: "+friendlist.get(i).user_level);
                iUserManner.setText("Manner: "+friendlist.get(i).user_manner);
                iUserMbti.setText("MBTI: "+friendlist.get(i).user_mbti);

                final AlertDialog.Builder alt_blt = new AlertDialog.Builder(MatchingActivity.this, R.style.plus_id_register_dialog_style);
                alt_blt.setCancelable(false)
                        .setView(inflatedView)
                        .setPositiveButton("대화 하기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int t) {
                                String text = friendlist.get(i).getNickName();
                                Intent intent = new Intent(MatchingActivity.this,ChatRoomActivity.class);
                                intent.putExtra("username",text);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("친구 추가", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int t) {
                            }
                        });
                AlertDialog dialog = alt_blt.create();
                dialog.show();


                //Intent intent = new Intent(MatchingActivity.this ,ChatRoomActivity.class);
                //intent.putExtra("username",text);
                //startActivity(intent);
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();


    }
}