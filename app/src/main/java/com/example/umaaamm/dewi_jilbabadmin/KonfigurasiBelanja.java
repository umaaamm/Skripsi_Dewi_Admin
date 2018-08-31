package com.example.umaaamm.dewi_jilbabadmin;

/**
 * Created by umaaamm on 30/08/18.
 */

public class KonfigurasiBelanja {
    public static final String URL_ADD="http://umam-developer.com/API/hijab/tambahTransaksi.php";
    public static final String URL_GET_ALL_B = "http://umam-developer.com/API/hijab/tampilBelanja.php";
    public static final String URL_GET_EMP = "http://umam-developer.com/API/hijab/tampilTransaksi.php?Transaksi_id=";
    public static final String URL_UPDATE_EMP = "http://umam-developer.com/API/hijab/updateTransaksi.php";
    public static final String URL_DELETE_EMP = "http://umam-developer.com/API/hijab/hapusTransaksi.php";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_EMP_ID_Transaksi = "transaksi_id";
    public static final String KEY_EMP_ID_FAKTUR = "faktur_id";
    public static final String KEY_EMP_ID_BARANG = "id_barang";
    public static final String KEY_EMP_ID_USER = "id_user";


    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_STOK = "stok";
    public static final String TAG_JUMLAH = "jumlah";
    public static final String TAG_NAMA_BARANG = "nama_barang";
    public static final String TAG_NAMA_USER = "nama_user";

    //ID karyawan
    //emp itu singkatan dari Employee
    public static final String EMP_ID = "emp_id";
}