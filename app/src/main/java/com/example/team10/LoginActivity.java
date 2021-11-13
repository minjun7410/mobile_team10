package com.example.team10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;

    Button BtnRegister;
    Button BtnLogin;

    EditText EtEmail;
    EditText EtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        BtnRegister = (Button) findViewById(R.id.btnRegister_login);
        BtnLogin = (Button) findViewById(R.id.btnLogin_login);

        EtEmail = (EditText) findViewById(R.id.etEmail_login);
        EtPassword = (EditText) findViewById(R.id.etPassword_login);


    }

    public void onClickRegisterBtn(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }

    public void onClickLoginBtn(View view){
        firebaseAuth =  FirebaseAuth.getInstance();

        String email = EtEmail.getText().toString();
        String password = EtPassword.getText().toString();

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "아이디 또는 비밀번호가 잘못되었습니다..", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}