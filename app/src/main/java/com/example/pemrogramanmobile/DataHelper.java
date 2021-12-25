package com.example.pemrogramanmobile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "FastParcel.db";
    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table pengiriman(ID integer primary key, nama_pengirim text null, alamat_pengirim text null, nama_penerima text null, alamat_penerima text null, kategori text null, status text null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);

        sql = "insert into pengiriman (ID, nama_pengirim, alamat_pengirim, nama_penerima, alamat_penerima, kategori, status) values ('202101', 'Naufal', 'jalanin aja dulu', 'alfy', 'jalan yang lurus', 'dokumen', 'Packing');";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
