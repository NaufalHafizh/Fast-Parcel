package com.example.pemrogramanmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

        text1 = findViewById(R.id.IDinput);
        text2 = findViewById(R.id.nama_pengirim_input);
        text3 = findViewById(R.id.alamat_pengirim_input);
        text4 = findViewById(R.id.nama_penerima_input);
        text5 = findViewById(R.id.alamat_penerima_input);
        text6 = findViewById(R.id.Kategori_input);
        text7 = findViewById(R.id.Status_input);

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


    }

    public void addData(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.execSQL("INSERT INTO pengiriman(ID, nama_pengirim, alamat_pengirim, nama_penerima, alamat_penerima, kategori, status) VALUES('" +
                text1.getText().toString() + "', '" +
                text2.getText().toString() + "', '" +
                text3.getText().toString() + "', '" +
                text4.getText().toString() + "', '" +
                text5.getText().toString() + "', '" +
                text6.getSelectedItem().toString() + "', '" +
                text7.getText().toString() + "') ");
        Toast.makeText(getApplicationContext(), "Berhasil Di Tambah", Toast.LENGTH_SHORT).show();
        finish();
    }
}