package com.example.team10;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserActivity extends Fragment {
    private FirebaseAuth firebaseAuth;
    final FirebaseFirestore db = FirebaseFirestore.getInstance();

    Button BtnLogout;
    Button BtnWithdraw;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_user, container, false);
        TextView textView = (TextView)view.findViewById(R.id.re_test);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),TestActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        BtnLogout = (Button) getView().findViewById(R.id.btnLogout) ;
        BtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLogoutBtn(v);
            }
        });

        BtnWithdraw = (Button) getView().findViewById(R.id.btnWithdraw);
        BtnWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickWithdrawBtn(v);
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        TextView TvEmail = (TextView) getView().findViewById(R.id.email_userInfo);

        if (currentUser != null ){
            String email = currentUser.getEmail();
            TvEmail.setText(email);
        } else{
            Toast.makeText(getActivity(),"not current User", Toast.LENGTH_SHORT).show();
        }

    }

    public void onClickLogoutBtn(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(getView().getContext());
        builder.setTitle("로그아웃").setMessage("로그아웃 하시겠습니까?");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    firebaseAuth.signOut();
                } catch (Exception e){
                    Toast.makeText(getActivity(), "로그아웃에 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = new Intent(getView().getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void onClickWithdrawBtn(View view){

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        String uid = currentUser.getUid();

        AlertDialog.Builder builder = new AlertDialog.Builder(getView().getContext());
        builder.setTitle("회원탈퇴").setMessage("정말 탈퇴 하시겠습니까?");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (currentUser != null)
                {
                    Toast.makeText(getActivity(),currentUser.getEmail(), Toast.LENGTH_SHORT).show();
                    currentUser.delete().addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
                        }
                    });
                    //                currentUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(getActivity(), "탈퇴되었습니다.", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
//                    }
//                });
                }
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}