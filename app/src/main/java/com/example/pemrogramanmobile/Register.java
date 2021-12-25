package com.example.pemrogramanmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private Button register;
    private CheckBox syaratketentuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = findViewById(R.id.Register);
        syaratketentuan = findViewById(R.id.syarat_ketentuan);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                register();
                Toast.makeText(Register.this, "Anda Berhasil Register, Silahkan Login", Toast.LENGTH_SHORT).show();
            }
        });

        syaratketentuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setSyaratketentuan();
                syaratketentuan.setChecked(true);
            }
        });
    }

    public void register(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void setSyaratketentuan(){

        Intent openbrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://naufalhafizh.github.io/Portfolio/"));
        startActivity(openbrowser);
    }

}