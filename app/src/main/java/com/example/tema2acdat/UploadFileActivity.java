package com.example.tema2acdat;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tema2acdat.red.RestClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.File;
import java.io.FileNotFoundException;

import cz.msebera.android.httpclient.Header;

public class UploadFileActivity extends AppCompatActivity {

    TextView txtV_InfoFicheroUpload;
    Button btn_subirFichero;
    EditText edt_RutaFicheroSubir;

    String rutaWeb = "http://alumno.mobi/guzman/upload.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_file);

        txtV_InfoFicheroUpload = (TextView) findViewById(R.id.txtV_InfoFicheroUpload);
        btn_subirFichero = (Button) findViewById(R.id.btn_SubirFicheroUpload);
        edt_RutaFicheroSubir = (EditText) findViewById(R.id.edt_RutaFicheroSubir);
    }

    public void onClick_uploadFicheros(View view) {
        switch (view.getId()) {
            case R.id.btn_SubirFicheroUpload:
                subirFichero();
                break;
        }
    }

    void buscarFichero() {
        btn_subirFichero.setEnabled(true);
    }

    void subirFichero() {
        String fichero = edt_RutaFicheroSubir.getText().toString();
        final ProgressDialog progreso = new ProgressDialog(this);

        Boolean existe = true;

        File myFile = new File(Environment.getExternalStorageDirectory(), fichero);
        RequestParams params = new RequestParams();
        try {
            params.put("fileToUpload", myFile);
        } catch (FileNotFoundException e) {
            existe = false;
            txtV_InfoFicheroUpload.setText("Error en el fichero: " + e.getMessage());
        }
        if (existe) {
            RestClient.post(rutaWeb, params, new TextHttpResponseHandler() {
                @Override
                public void onStart() {
                    progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progreso.setMessage("Conectando . . .");
                    progreso.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        public void onCancel(DialogInterface dialog) {
                            RestClient.cancelRequests(getApplicationContext(), true);
                        }
                    });
                    progreso.show();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String response) { // called when response HTTP status is "200 OK"
                    progreso.dismiss();
                    txtV_InfoFicheroUpload.setText(response);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String response, Throwable t) { // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                    progreso.dismiss();
                    txtV_InfoFicheroUpload.setText(response);
                }
            });
        }
    }
}

