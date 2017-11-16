package com.example.tema2acdat;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.tema2acdat.divisas.Conversor;

public class DivisasActivity extends AppCompatActivity {

    // Instancias a usar
    Button btn_ConvertirMoneda;
    EditText edt_aEuros;
    EditText edt_aDolares;
    EditText edt_CambioActual;
    RadioButton radB_aDolares;
    RadioButton radB_aEuros;

    Conversor miConversor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divisas);

        miConversor = new Conversor();

        // Identificando controles
        btn_ConvertirMoneda = (Button) findViewById(R.id.btn_Conversion);
        edt_aDolares = (EditText) findViewById(R.id.edT_ADolares);
        edt_aEuros = (EditText) findViewById(R.id.edT_AEuros);
        edt_CambioActual = (EditText) findViewById(R.id.edT_Cambio);
        radB_aDolares = (RadioButton) findViewById(R.id.radB_ADolares);
        radB_aEuros = (RadioButton) findViewById(R.id.radB_AEuros);
    }

    public void onClick_ConvertirMoneda(View v) {
        switch (v.getId()) {
            case R.id.btn_Conversion:
                hacerCambio();
                break;
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }

    void hacerCambio() {

        if (isNetworkAvailable()) {
            miConversor.setCambioFichero();
        }
        else {
            miConversor.cambioDefault();
        }

        // Seleccionando tipo de cambio
        if (radB_aDolares.isChecked()) {
            if (edt_aEuros.getText().length() != 0 && !edt_aEuros.getText().toString().equals(".")) {
                edt_aDolares.setText(miConversor.cambioADolares(edt_aEuros.getText().toString()));
                Toast.makeText(this, "EUR --> USD", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            if (edt_aDolares.getText().length() != 0 && edt_aDolares.getText().toString().equals(".")) {
                edt_aEuros.setText(miConversor.cambioAEuros(edt_aDolares.getText().toString()));
                Toast.makeText(this, "USD --> EUR", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
