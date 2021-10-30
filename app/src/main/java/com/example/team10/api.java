package com.example.team10;


import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class api {
    public JSONObject get(String strUrl){
        try{
            URL url = new URL(strUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setRequestMethod("GET");
            con.setDoOutput(false);

            Log.d("URL :", con.getURL().toString());

            StringBuilder sb = new StringBuilder();

            if(con.getResponseCode() == HttpURLConnection.HTTP_OK){
                //url에서 버퍼 형태로 데이터를 받음
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
                String line = br.readLine();
                while((line != null)){
                    sb.append(line).append("\n");
                    line = br.readLine();
                }
                br.close();
                Log.d("sb string: ", sb.toString());
                //JSON 형태로 변환 후 리턴
                JSONObject jsonObj = new JSONObject(sb.toString());

                return jsonObj;
            }else{
                Log.d("wrong response: ", con.getResponseMessage());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
