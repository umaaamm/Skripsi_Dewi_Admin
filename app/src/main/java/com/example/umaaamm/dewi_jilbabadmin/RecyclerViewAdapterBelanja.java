package com.example.umaaamm.dewi_jilbabadmin;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by umaaamm on 30/08/18.
 */

public class RecyclerViewAdapterBelanja extends RecyclerView.Adapter<RecyclerViewAdapterBelanja.ViewHolder> {

    private ArrayList<String> nama_barang;
    private ArrayList<String> nama_user;
    private ArrayList<String> stok;
    private ArrayList<String> jumlah;

    public RecyclerViewAdapterBelanja(ArrayList<String> nama_ba, ArrayList<String> nama_us, ArrayList<String> stok_bb,ArrayList<String> jumlah_bb) {
        nama_barang = nama_ba;
        nama_user = nama_us;
        stok = stok_bb;
        jumlah=jumlah_bb;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // di tutorial ini kita hanya menggunakan data String untuk tiap item
        public TextView tvTitle;
        public TextView tvSubtitle;
        public CardView cvMain;

        public ViewHolder(View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.nama_user);
            tvSubtitle = (TextView) v.findViewById(R.id.nama_barang);
            cvMain = (CardView) v.findViewById(R.id.cv_main_belanja);
        }
    }

    @Override
    public RecyclerViewAdapterBelanja.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // membuat view baru
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_belanja, parent, false);
        // mengeset ukuran view, margin, padding, dan parameter layout lainnya
        RecyclerViewAdapterBelanja.ViewHolder vh = new RecyclerViewAdapterBelanja.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterBelanja.ViewHolder holder, int position) {
        // - mengambil elemen dari dataset (ArrayList) pada posisi tertentu
        // - mengeset isi view dengan elemen dari dataset tersebut
        final String nama_b = nama_barang.get(position);
        final String nama_u = nama_user.get(position);
        final String stok_b = stok.get(position);
        final String jumlah_b = jumlah.get(position);

        holder.tvTitle.setText(nama_user.get(position));
        holder.tvSubtitle.setText(nama_barang.get(position)+", Jumlah Beli : "+jumlah.get(position));
        // Set onclicklistener pada view cvMain (CardView)
        holder.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Clicked element " + name, Snackbar.LENGTH_LONG).show();
//                Intent intent = new Intent(view.getContext(), DetailKategori.class);
//                intent.putExtra("id_kategori", id);
//                view.getContext().startActivity(intent);
//                ((Activity) view.getContext()).finish();

            }
        });
    }

    @Override
    public int getItemCount() {
        // menghitung ukuran dataset / jumlah data yang ditampilkan di RecyclerView
        return nama_barang.size();
    }

}
