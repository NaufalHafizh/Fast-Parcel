package com.example.pemrogramanmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = (Button) findViewById(R.id.loginbtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginpage();
                Toast.makeText(Login.this, "Anda Berhasil Login", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loginpage(){

        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

}