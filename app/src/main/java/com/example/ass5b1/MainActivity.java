package com.example.ass5b1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextUser, editTextPassword;
    Button buttonLogin;
    myDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editTextUser = findViewById(R.id.editTextUser);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        db = new myDatabase(this);
        db.insertData();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = editTextUser.getText().toString();
                String password = editTextPassword.getText().toString();

                Cursor cursor = db.validateData(user, password);
                if (cursor.getCount() == 0) {
                    showMessage("Error", "No User Found");
                } else {
                    // Only process the first row
                    cursor.moveToFirst();
                    StringBuffer buffer = new StringBuffer();
                    buffer.append("\nUser: " + cursor.getString(0) + " ");
                    buffer.append("\nPassword: " + cursor.getString(1) + " ");

                    showMessage("User Found", buffer.toString());

                    // Proceed to the SendEmail activity
                    Intent intent = new Intent(MainActivity.this, SendEmail.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void showMessage(String title, String info) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(info);
        builder.show();
    }
}
