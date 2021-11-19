package com.example.team10;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    final FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        String email = currentUser.getEmail();
        String uid = currentUser.getUid();

        if (currentUser != null ){
            Toast.makeText(UserActivity.this, uid, Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(UserActivity.this,"not current User", Toast.LENGTH_SHORT).show();
        }
    }

}
