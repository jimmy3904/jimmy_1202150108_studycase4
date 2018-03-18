package com.example.jm.jimmy_1202150108_studycase4;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class ListNamaMahasiswa extends AppCompatActivity {

    //membuat array nama mahasiswa
    private String[] namamahasiswa = {"Steve", "Ina", "Ratih", "Nindi", "James", "Astuti", "David", "Jay", "Dila", "Layla", "Toni", "Cici"
            , "Bregas", "Wawan", "Dede"};
    ListView nama;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nama_mahasiswa);

        nama = findViewById(R.id.listView);
        nama.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));

    }
    public void mulaii(View view) {
        new task().execute();
    }


    class task extends AsyncTask<Void, String, String> {

        ArrayAdapter<String> adapter;
        ProgressDialog progressbar;
        int count;


        @Override
        protected void onPreExecute() {
            //mengambil adapter dari array tersebut
            adapter = (ArrayAdapter<String>)nama.getAdapter();

            //membuat object progress dialog
            progressbar = new ProgressDialog(ListNamaMahasiswa.this);
            progressbar.setCancelable(true);
            progressbar.setMessage("Loading ...");
            progressbar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressbar.setProgress(0);
            progressbar.setMax(15);
            progressbar.show();
            //hitungan di mulai dari 0
            count = 0;

        }

        @Override
        protected String doInBackground(Void... voids) {
            //membuat perulangan untuk memunculkan nama mahasiswa
            for (String namamhs : namamahasiswa) {
                publishProgress(namamhs);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            //mengembalikan nilai dengan tulisan dalam bentuk toast
            return "semua nama sudah muncul";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            //adapter array dimulai dari 0
            adapter.add(values[0]);
            //hitungan pada saat progress update bertambah
            count++;
            //mengeset hitungan di dalam progress dialog
            progressbar.setProgress(count);
        }

        @Override
        protected void onPostExecute(String result) {
//menampilkan nilai dari return yang ada di method doInBackground
            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            //setelah loading progress sudah full maka otomatis akan hilang progress dialognya
            progressbar.hide();
        }

    }
}