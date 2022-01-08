package com.example.pemrogramanmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private Button register;
    private CheckBox syaratketentuan;
    EditText username, password, email, nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = findViewById(R.id.Register);
        syaratketentuan = findViewById(R.id.syarat_ketentuan);

        username = findViewById(R.id.user_username);
        password = findViewById(R.id.user_password);
        email = findViewById(R.id.user_email);
        nama = findViewById(R.id.user_nama);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                tambah_user();
                Toast.makeText(Register.this, "Anda Berhasil Register, Silahkan Login", Toast.LENGTH_SHORT).show();
                register();
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

    void tambah_user(){

        String url = "http://192.168.1.17/Android/register.php";

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> adduser = new HashMap<>();
                adduser.put("username", username.getText().toString());
                adduser.put("password", password.getText().toString());
                adduser.put("email", email.getText().toString());
                adduser.put("nama", nama.getText().toString());
                return adduser;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}