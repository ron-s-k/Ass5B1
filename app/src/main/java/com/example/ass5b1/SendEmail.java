package com.example.ass5b1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SendEmail extends AppCompatActivity {

    EditText sendermail,emessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);

        Button button = findViewById(R.id.btn1);
        emessage = findViewById(R.id.message);
        sendermail = findViewById(R.id.et1);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = sendermail.getText().toString();
                String[] strings = {"ronitk8484@gmail.com"};
                String[] strings1 = {mail};
                String s = emessage.getText().toString();

                sendEmail(strings, strings1, s);
            }
        });
    }

    public void sendEmail(String[] email , String[] carbon ,
                         String message)
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("Mail to "));
        String[] to = email;
        String[] cc = carbon;
        intent.putExtra(Intent.EXTRA_EMAIL , to);
        intent.putExtra(Intent.EXTRA_CC,cc);
        intent.putExtra(Intent.EXTRA_TEXT,message);
        intent.setType("message/rfc822");
        startActivity(intent.createChooser(intent,"Email"));
        Toast.makeText(this, "Mail Sent", Toast.LENGTH_SHORT).show();
        sendermail.setText("");
        emessage.setText("");


    }
}