package com.example.team10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    //api key 매번 갱신
    String TOKEN = "API_KEY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        get_summoners_id("hide on bush");
    }

    //롤 닉네임으로 사용자의 id, level 을 받아오는 메서드 (티어, 승패는 따로 구현해야함
    public void get_summoners_id(String summoners_name){
        new Thread() {
            public void run() {
                String name = summoners_name;
                String SummonerName = name.replaceAll(" ", "%20");
                String requestURL = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + SummonerName + "?api_key=" + TOKEN;
                api lol_api = new api();
                JSONObject jsonObj = lol_api.get(requestURL);
                try {
                    int level = (int) jsonObj.get("summonerLevel");
                    // 현재 레벨만 로그에 띄움
                    Log.d("Summoners Level : ", String.valueOf(level));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}