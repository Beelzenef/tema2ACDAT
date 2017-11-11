package com.example.tema2acdat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * Inicio, lanzador de ejercicios en botonera
 * @author Elena G (Beelzenef)
 */
public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }

    public void onClik_lanzarExs(View v)
    {
        Intent unIntent = null;

        switch (v.getId())
        {
            case R.id.btn_Ejercicio1:
                unIntent = new Intent(InicioActivity.this, AgendaActivity.class);
                break;
            case R.id.btn_Ejercicio2:
                unIntent = new Intent(InicioActivity.this, AlarmasActivity.class);
                break;
            case R.id.btn_Ejercicio3:
                unIntent = new Intent(InicioActivity.this, DiasLectivosActivity.class);
                break;
            case R.id.btn_Ejercicio4:
                unIntent = new Intent(InicioActivity.this, NavegadorActivity.class);
                break;
            case R.id.btn_Ejercicio5:
                unIntent = new Intent(InicioActivity.this, VisorImgsActivity.class);
                break;
            case R.id.btn_Ejercicio6:
                unIntent = new Intent(InicioActivity.this, DivisasActivity.class);
                break;
            case R.id.btn_Ejercicio7:
                unIntent = new Intent(InicioActivity.this, UploadFileActivity.class);
                break;
        }
        startActivity(unIntent);
    }
}
