package com.example.team10;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Name_API_Thread apiThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button plus_id_register = (Button) findViewById(R.id.plus_id_register);
        plus_id_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText et = new EditText(getApplicationContext());
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
                                Log.d("Summoners_id :", apiThread.getSummoners_info("id"));
                                Log.d("Summoners_name :", apiThread.getSummoners_info("name"));
                                Log.d("Summoners_tier :", apiThread.getSummoners_info("tier"));
                                LinearLayout id_register_item = new LinearLayout(getApplicationContext());
                                id_register_item.getView
                                TextView user_nickname = (TextView) findViewById(R.id.user_nickname);
                                TextView user_level = (TextView) findViewById(R.id.user_level);
                                TextView user_tier = (TextView) findViewById(R.id.user_tier);
                                TextView user_mbti = (TextView) findViewById(R.id.user_mbti);
                                TextView user_manner = (TextView) findViewById(R.id.user_manner);

                                user_nickname.setText(value);
                                user_level.setText(apiThread.getSummoners_info("level"));
                                user_tier.setText(apiThread.getSummoners_info("tier"));
                                user_mbti.setText("INFT");
                                user_manner.setText("SUCK");
                            }
                        });
                AlertDialog dialog = alt_blt.create();
                dialog.show();
            }
        });

    }
    private void excute_api(String value){
        //롤 닉네임으로 사용자의 id, level 티어, 승패를 받아온다.
        apiThread = new Name_API_Thread(value);
        try {
            apiThread.start();
            apiThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("Summoners_id :", apiThread.getSummoners_info("id"));
        Log.d("Summoners_name :", apiThread.getSummoners_info("name"));
        Log.d("Summoners_tier :", apiThread.getSummoners_info("tier"));
    }
}
