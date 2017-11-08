package com.example.tema2acdat;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.tema2acdat.operandoficheros.Resultado;
import com.example.tema2acdat.red.Conectar;

public class NavegadorActivity extends AppCompatActivity {

    EditText edT_URL;
    WebView webV_Navegador;
    RadioButton rdB_Java;
    RadioButton rdB_AAHC;
    RadioButton rdB_Volley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegador);

        edT_URL = (EditText) findViewById(R.id.edT_URL);
        webV_Navegador = (WebView) findViewById(R.id.webV_HTTP);
        rdB_Java = (RadioButton) findViewById(R.id.rdB_Java);
        rdB_AAHC = (RadioButton) findViewById(R.id.rdB_AAHC);
        rdB_Volley = (RadioButton) findViewById(R.id.rdB_Volley);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
    }

    public void onClick_Navegar(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_Navegar:
                establecerConexion();
                break;
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }

    private void establecerConexion() {
        String url = edT_URL.getText().toString();
        Resultado resultado = null;

        if (isNetworkAvailable()) {
            if (rdB_Java.isChecked())
                resultado = Conectar.conectarJava(url);
            if (rdB_AAHC.isChecked())
                //resultado = Conectar.conectarAAHC(url);
            if (rdB_Volley.isChecked())
                //resultado = Conectar.conectarVolley(url);
            if (resultado.getCodigo())
                webV_Navegador.loadDataWithBaseURL(null, resultado.getContenido(), "text/html", "UTF-8", null);
            else
                webV_Navegador.loadDataWithBaseURL(null, resultado.getMensaje(), "text/html", "UTF-8", null);
        }
        else
        {
            Toast.makeText(this, "No hay conexion a la red", Toast.LENGTH_SHORT).show();
        }
    }

}
