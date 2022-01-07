package com.example.pemrogramanmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private Button button;
    private EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = (Button) findViewById(R.id.loginbtn);

        username = findViewById(R.id.username);
        password = findViewById(R.id.passsword);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
//                Toast.makeText(Login.this, "Anda Berhasil Login", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void login(){

        if (username.getText().length()<1){

            Toast.makeText(getApplicationContext(), "Username Kosong", Toast.LENGTH_SHORT).show();
        }

        if (password.getText().length()<1){

            Toast.makeText(getApplicationContext(), "Password Kosong", Toast.LENGTH_SHORT).show();
        }else {

            kirim_dat();
        }
    }

    void kirim_dat(){

        String url = "http://192.168.1.17/Android/login.php";
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

//                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
//                        loginpage();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("success");
                            if (status.equals("1")){
                                loginpage();
                                Toast.makeText(getApplicationContext(), "Login Berhasil", Toast.LENGTH_SHORT).show();

                            }else if (status.equals("0")){
                                Toast.makeText(getApplicationContext(), "Username atau Password Salah", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

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

                Map<String, String> login = new HashMap<>();
                login.put("username", username.getText().toString());
                login.put("password", password.getText().toString());
                return login;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
        requestQueue.add(stringRequest);
    }

    public void loginpage(){

        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

}