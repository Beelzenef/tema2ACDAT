package com.example.tema2acdat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AlarmasActivity extends AppCompatActivity {

    EditText edT_textoAlarma1;
    EditText edT_tiempoAlarma1;
    EditText edT_textoAlarma2;
    EditText edT_tiempoAlarma2;
    EditText edT_textoAlarma3;
    EditText edT_tiempoAlarma3;
    EditText edT_textoAlarma4;
    EditText edT_tiempoAlarma4;
    EditText edT_textoAlarma5;
    EditText edT_tiempoAlarma5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmas);

        edT_textoAlarma1 = (EditText) findViewById(R.id.edT_TextoAlarma1);
        edT_tiempoAlarma1 = (EditText) findViewById(R.id.edT_Alarma1);
        edT_textoAlarma2 = (EditText) findViewById(R.id.edT_TextoAlarma2);
        edT_tiempoAlarma2 = (EditText) findViewById(R.id.edT_Alarma2);
        edT_textoAlarma3 = (EditText) findViewById(R.id.edT_TextoAlarma3);
        edT_tiempoAlarma3 = (EditText) findViewById(R.id.edT_Alarma3);
        edT_textoAlarma4 = (EditText) findViewById(R.id.edT_TextoAlarma4);
        edT_tiempoAlarma4 = (EditText) findViewById(R.id.edT_Alarma4);
        edT_textoAlarma5 = (EditText) findViewById(R.id.edT_TextoAlarma5);
        edT_tiempoAlarma5 = (EditText) findViewById(R.id.edT_Alarma5);
    }

    public void onClick_ComenzarAlarmas(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_ComenzarAlarmas:
                break;
        }
    }
}
