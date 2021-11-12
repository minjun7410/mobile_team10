package com.example.team10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;

public class LoginActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;

    Button registerBtn;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registerBtn = (Button) findViewById(R.id.registerBtnLogin);
        loginBtn = (Button) findViewById(R.id.loginBtnLogin);


    }

    public void onClickRegisterBtn(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }

    public void onClickLoginBtn(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}