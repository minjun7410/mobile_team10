package com.example.team10;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Fragment implements View.OnClickListener{
    Button plus_id_register;
    Button matchingBtn;
    Button chatBtn;
    Button friendBtn;
    Button userInfo;

    Name_API_Thread apiThread;

    String register_file = "register_file";
    SharedPreferences sharedPreferences;
    View root;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.activity_main, container, false);

        chatBtn = (Button) root.findViewById(R.id.chatBtn);
        matchingBtn = (Button) root.findViewById(R.id.matchingBtn);
        plus_id_register = (Button) root.findViewById(R.id.plus_id_register);
        friendBtn = (Button) root.findViewById(R.id.friendBtn);
        userInfo = (Button) root.findViewById(R.id.BtnUserInfo);

        sharedPreferences = root.getContext().getSharedPreferences(register_file, 0);
        if(!sharedPreferences.getString("nickname", "").equals("")){
            register_by_nickname(sharedPreferences.getString("nickname", ""));
        }

        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickChattingBtn(v);
            }
        });
        matchingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickMatchingBtn(view);
            }
        });
        plus_id_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRegisterBtn(view);
            }
        });
        friendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickFriendBtn(view);
            }
        });
        userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickUserInfoBtn(view);
            }
        });
        return root;
    }


    public void register_by_nickname(String value){
        apiThread = new Name_API_Thread(value);
        try {
            apiThread.start();
            apiThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        TextView user_nickname = (TextView) root.findViewById(R.id.user_nickname);
        TextView user_level = (TextView) root.findViewById(R.id.user_level);
        TextView user_tier = (TextView) root.findViewById(R.id.user_tier);
        TextView user_mbti = (TextView) root.findViewById(R.id.user_mbti);
        TextView user_manner = (TextView) root.findViewById(R.id.user_manner);

        LinearLayout user_layout = (LinearLayout) root.findViewById(R.id.id_register_item);
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

        ImageView user_icon = (ImageView) root.findViewById(R.id.user_icon);
        user_icon.setImageBitmap(apiThread.getSummoners_bitmap());
    }


    public void onClickChattingBtn(View view){
        Intent intent = new Intent(root.getContext(), ChatActivity.class);
        startActivity(intent);
    }
    public void onClickFriendBtn(View view){
        FragActivity activity = (FragActivity) root.getContext();
        activity.onFragmentChanged(1);
    }
    public void onClickMatchingBtn(View view) {
        Toast.makeText(root.getContext().getApplicationContext(), "Click Matching Button", Toast.LENGTH_SHORT).show();
    }

    public void onClickUserInfoBtn(View view){
        Intent intent = new Intent(root.getContext(), UserActivity.class);
        startActivity(intent);
    }
    public void onClickRegisterBtn(View view){
        //Dialog로 닉네임를 받아서 api를 통해 정보를 가져와 ImageView에 입력.
        plus_id_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText et = new EditText(root.getContext().getApplicationContext());
                //닉네임을 받는 dialog
                final AlertDialog.Builder alt_blt = new AlertDialog.Builder(root.getContext(), R.style.plus_id_register_dialog_style);
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
                                    Toast.makeText(root.getContext().getApplicationContext(), "Wrong NickName!!", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                TextView user_nickname = (TextView) root.findViewById(R.id.user_nickname);
                                TextView user_level = (TextView) root.findViewById(R.id.user_level);
                                TextView user_tier = (TextView) root.findViewById(R.id.user_tier);
                                TextView user_mbti = (TextView) root.findViewById(R.id.user_mbti);
                                TextView user_manner = (TextView) root.findViewById(R.id.user_manner);

                                LinearLayout user_layout = (LinearLayout) root.findViewById(R.id.id_register_item);
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

                                ImageView user_icon = (ImageView) root.findViewById(R.id.user_icon);
                                user_icon.setImageBitmap(apiThread.getSummoners_bitmap());
                            }
                        });
                AlertDialog dialog = alt_blt.create();
                dialog.show();
            }
        });

    }

    @Override
    public void onClick(View view) {

    }
}
