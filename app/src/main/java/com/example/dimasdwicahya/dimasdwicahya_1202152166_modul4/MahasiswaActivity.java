package com.example.dimasdwicahya.dimasdwicahya_1202152166_modul4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class MahasiswaActivity extends AppCompatActivity {
    //deklarasi variabel
    private ListView mListView;
    private ProgressBar mProgressBar;
    private ItemListView itemListView;
    private Button mbtMulai;

    //membuat arraylist
    private String[] mMahasiswa = {
            "Dimas Dwi Cahya",
            "Dicky Yuda Handika",
            "Dhea Khairani Zahra",
            "Editha Purnamasari",
            "M.Naufal Cahyo Baskoro",
            "Wildan Faudzan M.H",
            "M.Ilham Fauzi",
            "Khania Dewi Putri",
            "Zahra Maida Fathi"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa);

        //referensi objek
        mProgressBar = (ProgressBar) findViewById(R.id.pbMahasiswa);
        mListView = (ListView) findViewById(R.id.lvMahasiswa);
        mbtMulai = (Button) findViewById(R.id.btMulai);

        mListView.setVisibility(View.GONE);//setting listview menjadi tidak nampak

        //set adapter untuk array
        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));

        //aksi untuk tombol btMulai
        mbtMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemListView = new ItemListView();
                itemListView.execute();
            }
        });
    }

    //class untuk itemListView
    public class ItemListView extends AsyncTask<Void, String, Void> {

        //deklarasi dan inisiasi variabel
        private ArrayAdapter<String> mAdapter;
        private int counter = 1;
        ProgressDialog mProgressDialog = new ProgressDialog(MahasiswaActivity.this);

        @Override
        protected void onPreExecute() {
            mAdapter = (ArrayAdapter<String>) mListView.getAdapter(); //casting suggestion

            //untuk settingan progress dialog
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setTitle("Loading Data");
            mProgressDialog.setMessage("Please wait....");
            mProgressDialog.setCancelable(false);
            mProgressDialog.setProgress(0);

            //aksi untuk tombol cancel ketika di klik
            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    itemListView.cancel(true);
                    mProgressBar.setVisibility(View.VISIBLE);
                    dialog.dismiss();
                }
            });
            mProgressDialog.show();
        }

        //untuk load data dengan durasi selama 100milisecond
        @Override
        protected Void doInBackground(Void... params) {
            for (String item : mMahasiswa) {
                publishProgress(item);
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (isCancelled()) { //jika di klik tombol cancel maka
                    itemListView.cancel(true);
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            mAdapter.add(values[0]);

            Integer current_status = (int) ((counter / (float) mMahasiswa.length) * 100);
            mProgressBar.setProgress(current_status);

            //seting progress hanya berjalan untuk loading mode horizontal
            mProgressDialog.setProgress(current_status);

            //seting message tidak akan berjalan ketika menggunakan loading mode horizontal
            mProgressDialog.setMessage(String.valueOf(current_status + "%"));
            counter++;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //menyembunyikan progressbar
            mProgressBar.setVisibility(View.GONE);

            //menghapus progress dialog
            mProgressDialog.dismiss();
            mListView.setVisibility(View.VISIBLE);
        }
    }


}