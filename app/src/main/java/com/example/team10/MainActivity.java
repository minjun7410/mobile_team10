package com.example.team10;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button plus_id_register;
    Button matchingBtn;
    Button chatBtn;
    Button friendBtn;
    Name_API_Thread apiThread;

    String register_file = "register_file";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chatBtn = (Button) findViewById(R.id.chatBtn);
        matchingBtn = (Button) findViewById(R.id.matchingBtn);
        plus_id_register = (Button) findViewById(R.id.plus_id_register);
        friendBtn = (Button) findViewById(R.id.friendBtn);

        sharedPreferences = getSharedPreferences(register_file, 0);
        if(!sharedPreferences.getString("nickname", "").equals("")){
            register_by_nickname(sharedPreferences.getString("nickname", ""));
        }

        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ChatActivity.class);
                startActivity(intent);
            }
        });

    }

    public void register_by_nickname(String value){
        apiThread = new Name_API_Thread(value);
        try {
            apiThread.start();
            apiThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        TextView user_nickname = (TextView) findViewById(R.id.user_nickname);
        TextView user_level = (TextView) findViewById(R.id.user_level);
        TextView user_tier = (TextView) findViewById(R.id.user_tier);
        TextView user_mbti = (TextView) findViewById(R.id.user_mbti);
        TextView user_manner = (TextView) findViewById(R.id.user_manner);

        LinearLayout user_layout = (LinearLayout) findViewById(R.id.id_register_item);
        plus_id_register.setVisibility(View.GONE);
        user_layout.setVisibility(View.VISIBLE);

        user_nickname.setText(value);
        user_level.setText("Level: " + apiThread.getSummoners_info("level"));
        user_tier.setText("Tier: " + apiThread.getSummoners_info("tier"));
        user_mbti.setText("MBTI: INFT");
        user_manner.setText("Manner: "+"SUCK");

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nickname", value);
        editor.commit();

        ImageView user_icon = (ImageView) findViewById(R.id.user_icon);
        user_icon.setImageBitmap(apiThread.getSummoners_bitmap());
    }
    public void onClickChattingBtn(View view){
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }
    public void onClickFriendBtn(View view){
        Intent intent = new Intent(this, FriendActivity.class);
        startActivity(intent);
    }
    public void onClickMatchingBtn(View view) {
        Toast.makeText(getApplicationContext(), "Click Matching Button", Toast.LENGTH_SHORT).show();
    }
    public void onClickRegisterBtn(View view){
        //Dialog로 닉네임를 받아서 api를 통해 정보를 가져와 ImageView에 입력.
        plus_id_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText et = new EditText(getApplicationContext());
                //닉네임을 받는 dialog
                final AlertDialog.Builder alt_blt = new AlertDialog.Builder(MainActivity.this, R.style.plus_id_register_dialog_style);
                alt_blt.setTitle("아이디 생성")
                        .setMessage("닉네임을 적어주세요")
                        .setCancelable(false)
                        .setView(et)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String value = et.getText().toString();
                                //롤 닉네임으로 사용자의 id, level 티어, 승패를 받아온다.
                                apiThread = new Name_API_Thread(value);
                                try {
                                    apiThread.start();
                                    apiThread.join();

                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                //만약 아이디가 없는 아이디라면 Toast로 메시지 도시하고 중단.
                                if(apiThread.getSummoners_info("is_success") == "false"){
                                    Toast.makeText(getApplicationContext(), "Wrong NickName!!", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                TextView user_nickname = (TextView) findViewById(R.id.user_nickname);
                                TextView user_level = (TextView) findViewById(R.id.user_level);
                                TextView user_tier = (TextView) findViewById(R.id.user_tier);
                                TextView user_mbti = (TextView) findViewById(R.id.user_mbti);
                                TextView user_manner = (TextView) findViewById(R.id.user_manner);

                                LinearLayout user_layout = (LinearLayout) findViewById(R.id.id_register_item);
                                plus_id_register.setVisibility(View.GONE);
                                user_layout.setVisibility(View.VISIBLE);

                                user_nickname.setText(value);
                                user_level.setText("Level: " + apiThread.getSummoners_info("level"));
                                user_tier.setText("Tier: " + apiThread.getSummoners_info("tier"));
                                user_mbti.setText("MBTI: "+ apiThread.getSummoners_info("mbti"));
                                user_manner.setText("Manner: "+ apiThread.getSummoners_info("manner"));

                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("nickname", value);
                                editor.commit();

                                ImageView user_icon = (ImageView) findViewById(R.id.user_icon);
                                user_icon.setImageBitmap(apiThread.getSummoners_bitmap());
                            }
                        });
                AlertDialog dialog = alt_blt.create();
                dialog.show();
            }
        });

    }
}
