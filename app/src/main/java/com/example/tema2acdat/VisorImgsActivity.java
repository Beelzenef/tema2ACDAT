package com.example.tema2acdat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class VisorImgsActivity extends AppCompatActivity {

    ImageView imgV_Descargas;
    EditText edT_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor_imgs);

        imgV_Descargas = (ImageView) findViewById(R.id.imgV_Descargas);
        edT_URL = (EditText) findViewById(R.id.edT_URLImgs);
    }

    public void onClick_DescargarImgs(View view)
    {
        switch (view.getId())
        {
            case R.id.btn_NextImg:
                break;
            case R.id.btn_PrevImg:
                break;
            case R.id.btn_DescargarImgs:
                break;
        }
    }
}
