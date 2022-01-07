package com.example.pemrogramanmobile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;

import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Home extends AppCompatActivity{

    GridView gridView;
    public static Home ma;
    private Button logout, viewAll;
    private ImageButton newparcel,onconf,delivered,arived;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        logout = (Button) findViewById(R.id.logout);
        newparcel = (ImageButton) findViewById(R.id.newparcel);
        onconf = (ImageButton) findViewById(R.id.onconfirmation);
        delivered = findViewById(R.id.deliverd);
        arived = (ImageButton) findViewById(R.id.arived);
        viewAll = findViewById(R.id.viewAll);
        builder = new AlertDialog.Builder(this);

        ambil_data();

        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setViewAll();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Warning")
                        .setMessage("Apakah anda yakin ingin keluar")
                        .setCancelable(true)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                logout();
                                Toast.makeText(Home.this, "Anda Telah Logout", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();


            }
        });

        newparcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home.this, "New Parcel", Toast.LENGTH_SHORT).show();
            }
        });

        onconf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnconf();
                Toast.makeText(Home.this, "On Confirmation", Toast.LENGTH_SHORT).show();
            }
        });

        delivered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDelivered();
                Toast.makeText(Home.this, "Delivered", Toast.LENGTH_SHORT).show();
            }
        });

        arived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setArived();
                Toast.makeText(Home.this, "Arrived", Toast.LENGTH_SHORT).show();
            }
        });

        newparcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewparcel();
            }
        });

    }

    @Override
    protected void onResume() {

        ambil_data();
        super.onResume();
    }

    public void logout(){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void setViewAll(){

        Intent viewlAll_intent = new Intent(this, All_Status.class);
        startActivity(viewlAll_intent);
    }

    public void setOnconf(){
        int page = 0;
        Intent setoncof = new Intent(this, All_Status.class);
        setoncof.putExtra("Packing", page);
        startActivity(setoncof);
    }

    public void setDelivered(){

        int page = 1;
        Intent setdelivered = new Intent(this, All_Status.class);
        setdelivered.putExtra("Delivered", page);
        startActivity(setdelivered);
    }

    public void setArived(){

        int page = 2;
        Intent setarived = new Intent(this, All_Status.class);
        setarived.putExtra("Arrived", page);
        startActivity(setarived);
    }

    public void setNewparcel() {

        Intent setNew = new Intent(getApplicationContext(), Create.class);
        startActivity(setNew);
    }

    public void ambil_data(){

        String link = "http://192.168.1.14/Android/readData.php";
        StringRequest repon = new StringRequest(

                Request.Method.POST,
                link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("Data");
                            ArrayList<get_data> list_data;
                            list_data = new ArrayList<>();

                            for(int i = 0; i < jsonArray.length(); i++){

                                JSONObject hasil = jsonArray.getJSONObject(i);
                                String ID = hasil.getString("ID");
                                String nama_pengirim = hasil.getString("nama_pengirim");
                                String alamat_pengirim = hasil.getString("alamat_pengirim");
                                String nama_penerima = hasil.getString("nama_penerima");
                                String alamat_penerima = hasil.getString("alamat_penerima");
                                String kategori = hasil.getString("kategori");
                                String status = hasil.getString("status");

                                list_data.add(new get_data(
                                        ID,
                                        nama_pengirim,
                                        alamat_pengirim,
                                        nama_penerima,
                                        alamat_penerima,
                                        kategori,
                                        status
                                ));

                            }

                            gridView = findViewById(R.id.gridView);
                            custom_adapter adapter = new custom_adapter(Home.this, list_data);
                            gridView.setAdapter(adapter);

                            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    final get_data section = list_data.get(position);
                                    final CharSequence[] dialogitem = {"Detail", "Update", "Delete"};
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(Home.this);
                                    builder1.setTitle("Menu");
                                    builder1.setItems(dialogitem, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            switch(which){

                                                case 0 :
                                                    Intent menampilkanData = new Intent(getApplicationContext(), Detail.class);

                                                    menampilkanData.putExtra("ID", list_data.get(position).getID());
                                                    menampilkanData.putExtra("nama_pengirim", list_data.get(position).getNama_pengirim());
                                                    menampilkanData.putExtra("alamat_pengirim", list_data.get(position).getAlamat_pengirim());
                                                    menampilkanData.putExtra("nama_penerima", list_data.get(position).getNama_penerima());
                                                    menampilkanData.putExtra("alamat_penerima", list_data.get(position).getAlamat_penerima());
                                                    menampilkanData.putExtra("kategori", list_data.get(position).getKategori());
                                                    menampilkanData.putExtra("status", list_data.get(position).getStatus());
                                                    startActivity(menampilkanData);
                                                    break;

                                                case 1 :

                                                    Intent editdata = new Intent(getApplicationContext(), Update.class);

                                                    editdata.putExtra("ID", list_data.get(position).getID());
                                                    editdata.putExtra("nama_pengirim", list_data.get(position).getNama_pengirim());
                                                    editdata.putExtra("alamat_pengirim", list_data.get(position).getNama_pengirim());
                                                    editdata.putExtra("nama_penerima", list_data.get(position).getNama_penerima());
                                                    editdata.putExtra("alamat_penerima", list_data.get(position).getAlamat_penerima());
                                                    editdata.putExtra("kategori", list_data.get(position).getKategori());
                                                    editdata.putExtra("status", list_data.get(position).getStatus());
                                                    startActivity(editdata);

                                                    break;

                                                case 2 :
                                                    break;

                                            }
                                        }
                                    });
                                    builder1.create().show();
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

//                        Toast.makeText(Home.this, response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(Home.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(repon);
    }

//    public void RefreshGrid(){
//
//        SQLiteDatabase db = dbcenter.getReadableDatabase();
//        cursor = db.rawQuery("SELECT * FROM pengiriman", null);
//        list = new String[cursor.getCount()];
//        cursor.moveToFirst();
//        for (int i = 0; i < cursor.getCount(); i++){
//            cursor.moveToPosition(i);
//            list[i] = cursor.getString(0).toString();
//        }
//
//        gridView = (GridView) findViewById(R.id.gridView);
//        gridView.setAdapter(new ArrayAdapter<>(this, R.layout.gridview_item, list));
//        gridView.setSelected(true);
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                final String section = list[position];
//                final CharSequence[] dialogitem = {"Detail", "Update", "Delete"};
//                AlertDialog.Builder builder1 = new AlertDialog.Builder(Home.this);
//                builder1.setTitle("Menu");
//                builder1.setItems(dialogitem, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        switch(which){
//
//                            case 0 :
//                                Intent det = new Intent(getApplicationContext(), Detail.class);
//                                det.putExtra("ID", section);
//                                startActivity(det);
//                                break;
//
//                            case 1 :
//                                Intent up = new Intent(getApplicationContext(), Update.class);
//                                up.putExtra("ID", section);
//                                startActivity(up);
//                                break;
//
//                            case 2 :
//                                SQLiteDatabase db = dbcenter.getReadableDatabase();
//                                db.execSQL("DELETE FROM pengiriman WHERE ID = '"+section+"'");
//                                RefreshGrid();
//                                break;
//
//                        }
//                    }
//                });
//                builder1.create().show();
//            }
//        });
//        ((ArrayAdapter)gridView.getAdapter()).notifyDataSetInvalidated();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu); return true;
    }
}

class get_data{

    String ID = "", nama_pengirim = "", alamat_pengirim = "", nama_penerima = "", alamat_penerima = "", kategori = "", status = "";

    public get_data(String ID, String nama_pengirim, String alamat_pengirim, String nama_penerima, String alamat_penerima, String kategori, String status) {
        this.ID = ID;
        this.nama_pengirim = nama_pengirim;
        this.alamat_pengirim = alamat_pengirim;
        this.nama_penerima = nama_penerima;
        this.alamat_penerima = alamat_penerima;
        this.kategori = kategori;
        this.status = status;
    }

    public String getID() {
        return ID;
    }

    public String getNama_pengirim() {
        return nama_pengirim;
    }

    public String getAlamat_pengirim() {
        return alamat_pengirim;
    }

    public String getNama_penerima() {
        return nama_penerima;
    }

    public String getAlamat_penerima() {
        return alamat_penerima;
    }

    public String getKategori() {
        return kategori;
    }

    public String getStatus() {
        return status;
    }
}

class custom_adapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<get_data> model;

    public custom_adapter(Context context, ArrayList<get_data> model) {

        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.model = model;
    }

    @Override
    public int getCount() {
        return model.size();
    }

    @Override
    public Object getItem(int position) {
        return model.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = layoutInflater.inflate(R.layout.grid_item, parent, false);
        TextView tampilakan_ID;

        tampilakan_ID = view.findViewById(R.id.text_view_item_name);

        tampilakan_ID.setText(model.get(position).getID());

        return view;
    }
}