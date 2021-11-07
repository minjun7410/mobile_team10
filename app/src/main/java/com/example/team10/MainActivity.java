package com.example.team10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button registerBtn;
    Button matchingBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerBtn = (Button) findViewById(R.id.registerBtnMain);
        matchingBtn = (Button) findViewById(R.id.matchingBtn);
    }

    public void onClickRegisterBtn(View view){
        Toast.makeText(getApplicationContext(), "Click Id-register Button", Toast.LENGTH_SHORT).show();
    }

    public void onClickMatchingBtn(View view){
        Toast.makeText(getApplicationContext(), "Click Matching Button", Toast.LENGTH_SHORT).show();
    }
}