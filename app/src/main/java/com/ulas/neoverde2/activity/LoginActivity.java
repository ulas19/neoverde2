package com.ulas.neoverde2.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ulas.neoverde2.R;
import com.ulas.neoverde2.databinding.ActivityLoginBinding;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    ProgressDialog dialog;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth= FirebaseAuth.getInstance();

        loadLocale();

        ActionBar actionBar = getSupportActionBar ();
        SharedPreferences preferences = getSharedPreferences("chechbox",MODE_PRIVATE);
        String chehckbox = preferences.getString("remeber me","");

        if (chehckbox.equals(true)){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else if (chehckbox.equals(false)){
            Toast.makeText(this, "Please sign in...", Toast.LENGTH_SHORT).show();
        }

        binding.edtMail2.setError("Please enter your e-mail");

        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");



        binding.remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkBox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();
                    Toast.makeText(LoginActivity.this, "Checked", Toast.LENGTH_SHORT).show();
                }
                else if (!compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkBox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();
                    Toast.makeText(LoginActivity.this, "Unchecked", Toast.LENGTH_SHORT).show();
                }
                else {

                }
            }
        });

        binding.btnRegister2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        binding.btnLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,pass;
                email = binding.edtMail2.getText().toString().trim();
                pass = binding.edtPassword2.getText().toString().trim();

                auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            dialog.show();
                            Toast.makeText(LoginActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
    private void setLocale(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor  editor = getSharedPreferences("Settigns",MODE_PRIVATE).edit();
        editor.putString("My_lang",lang);
        editor.apply();
    }
    public void loadLocale(){
        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = sharedPreferences.getString("My_lang","");
        setLocale(language);
    }
    /**public void empty(){
     String email,pass;
     email = binding.edtMail2.getText().toString().trim();
     pass = binding.edtPassword2.getText().toString().trim();

     if (email.equals("") && pass.equals(binding.edtMail2)){
     Toast.makeText(this, "Please enter your email address", Toast.LENGTH_SHORT).show();
     }
     else if (pass.equals(binding.edtPassword2)&&email.equals("")){
     Toast.makeText(this, "Please enter you password", Toast.LENGTH_SHORT).show();
     }
     else {
     Toast.makeText(this, "Please enter your email address and password", Toast.LENGTH_SHORT).show();
     }
     }*/
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
                singleBack = false;
            }
        }, 2000);


    }
}
