package com.example.jm.jimmy_1202150108_studycase4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;

public class PencariGambar extends AppCompatActivity {
    EditText link;
    ImageView gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencari_gambar);

        link = findViewById(R.id.url);
        gambar = findViewById(R.id.gambar);
    }

    public void find(View view) {
        String URLGambar = link.getText().toString();
        new DownloadImage().execute(URLGambar);
    }
    class DownloadImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Bitmap doInBackground(String... voids) {

            String imageURL = voids[0];

            Bitmap bitmap = null;
            try {
                // download gambar dari url
                InputStream input = new java.net.URL(imageURL).openStream();
                // jadikan input dari url ke bitmap
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            // mengeset bitmap ke dalam imageView yang sudah di sediakan
            gambar.setImageBitmap(result);

        }
    }
}

