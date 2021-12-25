package com.example.pemrogramanmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Detail extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button back2;
    TextView text1, text2, text3, text4, text5, text6, text7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        dbHelper = new DataHelper(this);

        text1 = findViewById(R.id.IDlihat);
        text2 = findViewById(R.id.nama_pengirimLiat);
        text3 = findViewById(R.id.alamat_pengirimLiat);
        text4 = findViewById(R.id.nama_penerimaLiat);
        text5 = findViewById(R.id.alamat_penerimaLiat);
        text6 = findViewById(R.id.KategoriLiat);
        text7 = findViewById(R.id.statusLiat);

        back2 = findViewById(R.id.back2);

        // Mengambil data dari Intent
        text1.setText(getIntent().getStringExtra("ID"));
        text2.setText(getIntent().getStringExtra("nama_pengirim"));
        text3.setText(getIntent().getStringExtra("alamat_pengirim"));
        text4.setText(getIntent().getStringExtra("nama_penerima"));
        text5.setText(getIntent().getStringExtra("alamat_penerima"));
        text6.setText(getIntent().getStringExtra("kategori"));
        text7.setText(getIntent().getStringExtra("status"));

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });


    }
}