package com.example.team10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    final FirebaseFirestore db = FirebaseFirestore.getInstance();

    Button BtnRegister;
    Button BtnEmailCheck;
    Button BtnNameCheck;

    EditText EtEmail;
    EditText EtPassword;
    EditText EtPasswordCheck;
    EditText EtUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        BtnRegister = (Button) findViewById(R.id.btnRegister_register);
        BtnEmailCheck = (Button) findViewById(R.id.btnEmailCheck_register);
        BtnNameCheck = (Button) findViewById(R.id.btnUsernameCheck_register);

        EtEmail = (EditText) findViewById(R.id.etEmail_register);
        EtPassword = (EditText) findViewById(R.id.etPassword_register);
        EtPasswordCheck = (EditText) findViewById(R.id.etPasswordCheck_register);
        EtUsername = (EditText) findViewById(R.id.etUsername_register);
    }

    public void onClickBtnEmailCheck(View view){
        Toast.makeText(RegisterActivity.this, "Click Email check button", Toast.LENGTH_SHORT).show();
    }

    public void onClickBtnNameCheck(View view){
        Toast.makeText(RegisterActivity.this, "Click username check button", Toast.LENGTH_SHORT).show();
    }

    public void onClickBtnRegister(View view){
        String email = EtEmail.getText().toString();
        String username = EtUsername.getText().toString();
        String password = EtPassword.getText().toString();
        String checkPassword = EtPasswordCheck.getText().toString();

        if (email.equals("") || username.equals("") || password.equals("") || checkPassword.equals("")){
            Toast.makeText(RegisterActivity.this, "항목을 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(checkPassword)){
            Toast.makeText(RegisterActivity.this, "비밀번호가 같지 않습니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        RegisterUser(email, password, username);
    }

    public void RegisterUser(String email, String password, String username){
        firebaseAuth =  FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = firebaseAuth.getCurrentUser();

                            HashMap<Object,String> userInfo = new HashMap<>();
                            userInfo.put("uid", user.getUid());
                            userInfo.put("email", user.getEmail());
                            userInfo.put("name", username);

                            // firestore cloud database
                            db.collection("users").add(userInfo)
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(RegisterActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                                        }
                                    });

                            Toast.makeText(RegisterActivity.this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                            finish();
                        } else{
                            Toast.makeText(RegisterActivity.this, "사용중인 이메일입니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}