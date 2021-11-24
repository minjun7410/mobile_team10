package com.example.team10;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

public class UserActivity extends Fragment {
    private FirebaseAuth firebaseAuth;
    final FirebaseFirestore db = FirebaseFirestore.getInstance();

    Button BtnLogout;
    Button BtnWithdraw;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        BtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLogoutBtn(view);
            }
        });

        BtnWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickWithdrawBtn(view);
            }
        });
        return inflater.inflate(R.layout.activity_user, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        String email = currentUser.getEmail();
        String uid = currentUser.getUid();

        TextView TvEmail = (TextView) getView().findViewById(R.id.email_userInfo);

        if (currentUser != null ){
            TvEmail.setText(email);
        } else{
            Toast.makeText(getActivity(),"not current User", Toast.LENGTH_SHORT).show();
        }

    }

    public void onClickLogoutBtn(View view){
        Toast.makeText(getActivity(), "logout", Toast.LENGTH_SHORT).show();
    }

    public void onClickWithdrawBtn(View view){
        Toast.makeText(getActivity(), "탈퇴", Toast.LENGTH_SHORT).show();
    }

}
