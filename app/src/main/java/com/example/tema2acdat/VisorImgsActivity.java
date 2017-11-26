package com.example.tema2acdat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class VisorImgsActivity extends AppCompatActivity {

    private ImageView imgV_Descargas;
    private EditText edT_URL;

    private String imageURL;
    private int flag = 0;

    private List<String> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor_imgs);

        imgV_Descargas = (ImageView) findViewById(R.id.imgV_Descargas);
        edT_URL = (EditText) findViewById(R.id.edT_URLImgs);

        inicializarImgs();
    }

    void inicializarImgs() {
        images = new ArrayList<>();
        images.add("https://i.imgur.com/yHxsqmp.jpg");
        images.add("https://i.imgur.com/kCQ5ZLD.jpg");
    }

    public void onClick_DescargarImgs(View view) {
        switch (view.getId()) {
            case R.id.btn_NextImg:
                nextImg();
                break;
            case R.id.btn_PrevImg:
                prevImg();
                break;
            case R.id.btn_DescargarImgs:
                addImg();
                break;
        }
    }

    private void picasso(String link) {
        Picasso.with(getApplicationContext()).load(link).placeholder(R.drawable.placeholder).error(R.drawable.placeholder_error)
                .resize(300, 300).into(imgV_Descargas);
    }

    public void addImg() {
        imageURL = edT_URL.getText().toString();

        if (!TextUtils.isEmpty(imageURL)) {
            picasso(imageURL);
            images.add(imageURL);
            Toast.makeText(this, "¡Imagen descargada!", Toast.LENGTH_SHORT).show();
        }
    }

    public void nextImg() {
        String nextImage = images.get(flag++ % images.size());
        picasso(nextImage);
    }

    public void prevImg() {
        String nextImage = images.get(flag++ % images.size());
        picasso(nextImage);
    }
}
