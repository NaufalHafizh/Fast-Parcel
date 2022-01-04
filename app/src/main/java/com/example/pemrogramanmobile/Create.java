package com.example.pemrogramanmobile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.Map;

public class Create extends AppCompatActivity {

    Button kirim, close;
    DataHelper dbHelper;
    Spinner text6;
    EditText text1, text2, text3, text4, text5, text7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        dbHelper = new DataHelper(this);

        text2 = findViewById(R.id.nama_pengirim_input);
        text3 = findViewById(R.id.alamat_pengirim_input);
        text4 = findViewById(R.id.nama_penerima_input);
        text5 = findViewById(R.id.alamat_penerima_input);
        text6 = findViewById(R.id.Kategori_input);

        kirim = findViewById(R.id.Simpan);
        close = findViewById(R.id.back);

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(
                Create.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.kategori_list)
        );
        stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        text6.setAdapter(stringArrayAdapter);

        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });

//        close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });


    }

    public void addData(){

        String url = "http://192.168.1.14/Android/createData.php";

        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                        finish();
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

                Map<String, String> form = new HashMap<>();
                form.put("nama_pengirim", text2.getText().toString());
                form.put("alamat_pengirim", text3.getText().toString());
                form.put("nama_penerima", text4.getText().toString());
                form.put("alamat_penerima", text5.getText().toString());
                form.put("kategori", text6.getSelectedItem().toString());
                return form;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}