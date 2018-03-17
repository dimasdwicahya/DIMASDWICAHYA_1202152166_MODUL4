package com.example.dimasdwicahya.dimasdwicahya_1202152166_modul4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity{
    //deklarasi variable
    Button btMahasiswa, btGambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //referensi
        btMahasiswa = (Button) findViewById(R.id.btListMahasiswa);
        btGambar = (Button) findViewById(R.id.btPencarianGambar);

        //aksi untuk button list mahasiswa
        btMahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // pindah ke activity target
                Intent intent = new Intent(MainActivity.this,MahasiswaActivity.class);
                startActivity(intent);
            }
        });

        //aksi untuk button cari gambar
        btGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // pindah ke activity target
                Intent intent = new Intent(MainActivity.this,GambarActivity.class);
                startActivity(intent);
            }
        });


    }
}