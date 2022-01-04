package com.example.pemrogramanmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
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

import java.util.HashMap;
import java.util.Map;

public class Update extends AppCompatActivity {

    protected Cursor cursor;
    Button update, back;
    DataHelper dbHelper;
    EditText text1, text2, text3, text4, text5, text6, text7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        dbHelper = new DataHelper(this);

        text1 = (EditText) findViewById(R.id.IDinput_ubah);
        text2 = (EditText) findViewById(R.id.nama_pengirim_ubah);
        text3 = (EditText) findViewById(R.id.alamat_pengirim_ubah);
        text4 = (EditText) findViewById(R.id.nama_penerima_ubah);
        text5 = (EditText) findViewById(R.id.alamat_penerima_ubah);
        text6 = (EditText) findViewById(R.id.Kategori_ubah);
        text7 = (EditText) findViewById(R.id.Status_ubah);

        update = (Button) findViewById(R.id.Ubah);
        back = (Button) findViewById(R.id.backUP);

        // Mengambil data dari Intent
        text1.setText(getIntent().getStringExtra("ID"));
        text2.setText(getIntent().getStringExtra("nama_pengirim"));
        text3.setText(getIntent().getStringExtra("alamat_pengirim"));
        text4.setText(getIntent().getStringExtra("nama_penerima"));
        text5.setText(getIntent().getStringExtra("alamat_penerima"));
        text6.setText(getIntent().getStringExtra("kategori"));
        text7.setText(getIntent().getStringExtra("status"));

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editData();
            }
        });

    }

    public void editData(){

        String url = "http://192.168.1.14/Android/updateData.php";

        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
//                        finish();
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
                form.put("ID", getIntent().getStringExtra("ID"));
                form.put("nama_pengirim", text2.getText().toString());
                form.put("alamat_pengirim", text3.getText().toString());
                form.put("nama_penerima", text4.getText().toString());
                form.put("alamat_penerima", text5.getText().toString());
                form.put("kategori", text6.getText().toString());
                form.put("status", text7.getText().toString());
                return form;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}