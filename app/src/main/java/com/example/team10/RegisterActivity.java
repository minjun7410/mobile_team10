package com.example.team10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    Button registerBtn;
    Button emailCheckBtn;
    Button nameCheckBtn;

    EditText emailEditText;
    EditText pwEditText;
    EditText pwCheckEditText;
    EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerBtn = (Button) findViewById(R.id.registerBtnRegister);
        emailCheckBtn = (Button) findViewById(R.id.emailCheckBtnRegister);
        nameCheckBtn = (Button) findViewById(R.id.nameCheckBtnRegister);

        emailEditText = (EditText) findViewById(R.id.emailEtRegister);
        pwEditText = (EditText) findViewById(R.id.pwEtRegister);
        pwCheckEditText = (EditText) findViewById(R.id.pwCheckEtRegister);
        nameEditText = (EditText) findViewById(R.id.nameEtRegister);
    }

    public void onClickEmailCheckBtn(View view){
        return;
    }

    public void onClickNameCheckBtn(View view){
        return;
    }

    public void onClickRegisterBtn(View view){
        String email = emailEditText.getText().toString();
        String password = pwEditText.getText().toString();
        String checkPassword = pwCheckEditText.getText().toString();
        String username = nameEditText.getText().toString();

        firebaseAuth =  FirebaseAuth.getInstance();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    Toast.makeText(RegisterActivity.this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(RegisterActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}