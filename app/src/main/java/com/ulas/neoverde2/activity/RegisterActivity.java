package com.ulas.neoverde2.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ulas.neoverde2.R;
import com.ulas.neoverde2.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {
    FirebaseAuth auth;
    ActivityRegisterBinding binding;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        auth= FirebaseAuth.getInstance();
        user=FirebaseAuth.getInstance().getCurrentUser();

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,pass;
                email = binding.edtMail2.getText().toString();
                pass = binding.edtPassword2.getText().toString();


                auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Account is created", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(RegisterActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

    boolean singleBack = false;

    @Override
    public void onBackPressed() {
        if (singleBack) {
            super.onBackPressed();
            return;
        }

        this.singleBack = true;
        Toast.makeText(this, "Double Back to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                singleBack=false;
            }
        }, 2000);
    }
}