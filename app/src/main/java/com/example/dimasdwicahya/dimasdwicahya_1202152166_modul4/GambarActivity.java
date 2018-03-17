package com.example.dimasdwicahya.dimasdwicahya_1202152166_modul4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;

public class GambarActivity extends AppCompatActivity {
    //deklarasi variabel
    ImageView mivGambar;
    EditText metUrl;
    Button mbtCari;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gambar);

        //referensi
        mivGambar = (ImageView) findViewById(R.id.ivGambar);
        metUrl = (EditText) findViewById(R.id.etUrl);
        mbtCari = (Button) findViewById(R.id.btCari);

        //aksi untuk button Cari Gambar
        mbtCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link = metUrl.getText().toString(); //Konversi url ke String
                new DownloadImage().execute(link);
            }
        });
    }

    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Membuat Progress Dialog
            mProgressDialog = new ProgressDialog(GambarActivity.this);

            // Judul Progress Dialog
            mProgressDialog.setTitle("Downloading image");

            // Seting message Progress Dialog
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);

            // menampilkan Progress Dialog
            mProgressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... URL) {

            String imageURL = URL[0];

            Bitmap bitmap = null;
            try {
                // mendownload gambar dari url
                InputStream input = new java.net.URL(imageURL).openStream();
                // mengkonversikan gambar ke bitmat (decode to bitmap)
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            // menampung gambar ke imageView dan menampilkannya
            mivGambar.setImageBitmap(result);

            // menghilangkan Progress Dialog
            mProgressDialog.dismiss();
        }
    }
}
