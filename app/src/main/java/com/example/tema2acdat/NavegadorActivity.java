package com.example.tema2acdat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tema2acdat.operandoficheros.Resultado;
import com.example.tema2acdat.red.Conectar;
import com.example.tema2acdat.red.RestClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;

public class NavegadorActivity extends AppCompatActivity {

    EditText edT_URL;
    WebView webV_Navegador;
    RadioButton rdB_Java;
    RadioButton rdB_AAHC;
    RadioButton rdB_Volley;

    Resultado resultado = null;

    RequestQueue mRequestQueue;

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

        if (isNetworkAvailable()) {
            if (rdB_Java.isChecked())
                conexionJavaNet();
            if (rdB_AAHC.isChecked())
                conexionAAHC();
            if (rdB_Volley.isChecked())
                conexionVolley();
        }
        else
        {
            Toast.makeText(this, "No hay conexion a la red", Toast.LENGTH_SHORT).show();
        }
    }

    private void conexionJavaNet()
    {
        resultado = Conectar.conectarJava(edT_URL.getText().toString());
        if (resultado.getCodigo())
            webV_Navegador.loadDataWithBaseURL(null, resultado.getContenido(), "text/html", "UTF-8", null);
        else
            webV_Navegador.loadDataWithBaseURL(null, resultado.getMensaje(), "text/html", "UTF-8", null);
    }

    private void conexionAAHC() {
        final String texto = edT_URL.getText().toString();
        final ProgressDialog progreso = new ProgressDialog(NavegadorActivity.this);
        RestClient.get(texto, new TextHttpResponseHandler() {
            @Override
            public void onStart() {
                //called before request is started
                progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progreso.setMessage("Conectando...");
                //progreso.setCancelable(false);
                progreso.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                        RestClient.cancelRequests(getApplicationContext(), true);
                    }
                });
                progreso.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String response) {
                // called when response HTTP status is "200 OK"
                progreso.dismiss();
                webV_Navegador.loadDataWithBaseURL(null, response, "text/html", "UTF-8", null);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String response, Throwable t) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                progreso.dismiss();
                webV_Navegador.loadDataWithBaseURL(null, "Error: " + response + " ", "text/html", "UTF-8", null);
            }
        });
    }

    public void conexionVolley() {
        final String enlace = edT_URL.getText().toString();
        // Instantiate the RequestQueue.
        mRequestQueue = Volley.newRequestQueue(this);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Conectando...");
        progressDialog.setCancelable(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                mRequestQueue.cancelAll("tag");
            }
        });

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, enlace, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();
                webV_Navegador.loadDataWithBaseURL(enlace, response, "text/html", "UTF-8", null);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String mensaje = "Error";
                if (error instanceof TimeoutError || error instanceof NoConnectionError)
                    mensaje = "Timeout Error: " + error.getMessage();
                else {
                    NetworkResponse errorResponse = error.networkResponse;
                    if (errorResponse != null && errorResponse.data != null) {
                        try {
                            mensaje = "Error: " + errorResponse.statusCode + " " + "\n " +
                                    new String(errorResponse.data, "UTF-8");

                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
                progressDialog.dismiss();
                webV_Navegador.loadDataWithBaseURL(null, mensaje, "text/html", "UTF-8", null);

            }
        });
        // Set the tag on the request.
        stringRequest.setTag("tag");
        // Set retry policy
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 1, 1));
        // Add the request to the RequestQueue.
        mRequestQueue.add(stringRequest);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll("tag");
        }
    }

}
