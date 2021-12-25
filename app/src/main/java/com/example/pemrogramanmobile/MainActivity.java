package com.example.pemrogramanmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.login);
        register = findViewById(R.id.daftar);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bukamenulogin();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bukamenuregister();
            }
        });
    }

    public void bukamenulogin(){

        Intent login = new Intent(this, Login.class);
        startActivity(login);
    }

    public void bukamenuregister(){

        Intent register = new Intent(this, Register.class);
        startActivity(register);
    }
}