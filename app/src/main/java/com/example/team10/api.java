package com.example.team10;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class api {
    String BASE_URL = "https://kr.api.riotgames.com";
    String TOKEN = "RGAPI-76b06206-2170-4896-bd05-1483d91d06e2";
    public void get(String strUrl){
        try{
            URL url = new URL(strUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            //con.addRequestProperty(TOKEN, );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
