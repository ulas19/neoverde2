package com.ulas.neoverde2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ulas.neoverde2.R;
import com.ulas.neoverde2.databinding.ActivitySikayetMailBinding;

public class SikayetMailActivity extends AppCompatActivity {
    ActivitySikayetMailBinding binding;
    EditText edt_message,edt_subject,edt_to;
    Button btn_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sikayet_mail);

        edt_message=findViewById(R.id.edt_message);
        edt_subject=findViewById(R.id.edt_subject);
        edt_to=findViewById(R.id.edt_to);
        btn_send=findViewById(R.id.btnSend);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });

    }
    protected void sendEmail() {
        Log.i("Send email", "");

        String[] TO = {"sikayet@icdteknoloji.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending ", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(SikayetMailActivity.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }

    }

}