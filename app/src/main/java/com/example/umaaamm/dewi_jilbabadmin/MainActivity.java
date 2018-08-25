package com.example.umaaamm.dewi_jilbabadmin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> idbarang;
    private ArrayList<String> stokbarang;
    private ArrayList<String> namabarang;
    private ArrayList<String> gambarbarang;
    private ArrayList<String> hargabarang;
    public static String id_user_s = "kosong";
    public static final String nama_user_s = "kosong";

    private String JSON_STRING;
    SwipeRefreshLayout swipeRefreshLayout;

    Sesion sesi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        startActivity(getIntent());
                        swipeRefreshLayout.setRefreshing(false);

                    }
                }, 2000);
            }
        });


        idbarang = new ArrayList<>();
        stokbarang = new ArrayList<>();
        namabarang = new ArrayList<>();
        gambarbarang = new ArrayList<>();
        hargabarang = new ArrayList<>();

        rvView = (RecyclerView) findViewById(R.id.rv_main_barang);
        rvView.setHasFixedSize(true);

        rvView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

        getJSON();


        //floating button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                Intent ke_upload_produk = new Intent(MainActivity.this, UploadProdukClass.class);
//                startActivity(ke_upload_produk);
                rvView.smoothScrollToPosition(0);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    private void showEmployee() {

        if (!stokbarang.isEmpty()) {
            stokbarang.clear();
        }

        if (!namabarang.isEmpty()) {
            namabarang.clear();
        }

        if (!gambarbarang.isEmpty()) {
            gambarbarang.clear();
        }
        if (!hargabarang.isEmpty()) {
            hargabarang.clear();
        }

        JSONObject jsonObject = null;
//        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(KonfigurasiBarang.TAG_JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(KonfigurasiBarang.TAG_ID_BARANG);
                String stok = jo.getString(KonfigurasiBarang.TAG_STOK);
                String nama = jo.getString(KonfigurasiBarang.TAG_NAMA);
                String gambar = jo.getString(KonfigurasiBarang.TAG_GAMBAR);
                String harga = jo.getString(KonfigurasiBarang.TAG_HARGA);

                //Toast.makeText(Barang.this,"Get Json : "+nama,Toast.LENGTH_SHORT).show();
                idbarang.add(id);
                stokbarang.add(stok);
                namabarang.add(nama);
                gambarbarang.add(gambar);
                hargabarang.add(harga);

//                HashMap<String,String> employees = new HashMap<>();
//                employees.put(Konfigurasi.TAG_ID,id);
//                employees.put(Konfigurasi.TAG_NAMA,nama_kategor);
//                list.add(employees);
            }

            //Toast.makeText(Barang.this,"Get Json : "+namabarang.size(),Toast.LENGTH_SHORT).show();
            adapter = new RecyclerViewAdapterBarang(idbarang, stokbarang, namabarang, gambarbarang, hargabarang);
            rvView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

//        ListAdapter adapter = new SimpleAdapter(
//                TampilSemuaPgw.this, list, R.layout.list_item,
//                new String[]{konfigurasi.TAG_ID,konfigurasi.TAG_NAMA},
//                new int[]{R.id.id, R.id.name});
//
//        listView.setAdapter(adapter);
    }


    private void getJSON() {

        class GetJSON extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Mengambil Data", "Mohon Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();

            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(KonfigurasiBarang.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.beranda) {
            Intent ke_beranda = new Intent(MainActivity.this, MainActivity.class);
            startActivity(ke_beranda);
        } else if (id == R.id.keluar) {

        } else if (id == R.id.kategori) {
            Intent ke_produk = new Intent(MainActivity.this, Kategori.class);
            startActivity(ke_produk);
        } else if (id == R.id.uploadproduk) {
            Intent ke_upload_produk = new Intent(MainActivity.this, Produk.class);
            startActivity(ke_upload_produk);
        } else if (id == R.id.tentang) {
            Intent ke_tentang = new Intent(MainActivity.this, Tentang.class);
            startActivity(ke_tentang);
        }else if (id == R.id.register) {
            Intent keregister = new Intent(MainActivity.this, Register.class);
            startActivity(keregister);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
