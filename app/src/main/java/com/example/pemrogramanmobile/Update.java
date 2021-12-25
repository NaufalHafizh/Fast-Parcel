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

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM pengiriman WHERE ID = '"+getIntent().getStringExtra("ID") +"'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0){

            cursor.moveToPosition(0);
            text1.setText(cursor.getString(0).toString());
            text2.setText(cursor.getString(1).toString());
            text3.setText(cursor.getString(2).toString());
            text4.setText(cursor.getString(3).toString());
            text5.setText(cursor.getString(4).toString());
            text6.setText(cursor.getString(5).toString());
            text7.setText(cursor.getString(6).toString());

        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = dbHelper.getWritableDatabase();
//                nama_pengirim, alamat_pengirim, nama_penerima, alamat_penerima, kategori, status
                db.execSQL("update pengiriman set nama_pengirim='"+
                        text2.getText().toString() +"', " + "alamat_pengirim='" +
                        text3.getText().toString()+"', " + "nama_penerima='"+
                        text4.getText().toString() +"', " + "alamat_penerima='" +
                        text5.getText().toString() +"', " + "kategori='" +
                        text6.getText().toString() +"', " + "status='" +
                        text7.getText().toString() + "' where ID='" + text1.getText().toString()+"'");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
//                Home.ma.RefreshGrid();
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu); return true;

    }
}