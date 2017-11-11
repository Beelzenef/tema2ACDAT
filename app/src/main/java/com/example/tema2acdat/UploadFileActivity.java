package com.example.tema2acdat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UploadFileActivity extends AppCompatActivity {

    TextView txtV_InfoFicheroUpload;
    Button btn_BuscarFicheroUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_file);

        txtV_InfoFicheroUpload = (TextView) findViewById(R.id.txtV_InfoFicheroUpload);
        btn_BuscarFicheroUpload = (Button) findViewById(R.id.btn_BuscarFicheroUpload);
    }

    public void onClick_uploadFicheros(View view) {
        switch (view.getId()) {
            case R.id.btn_SubirFicheroUpload:
                break;
            case R.id.btn_BuscarFicheroUpload:
                break;

        }
    }

    void buscarFichero()
    {
        btn_BuscarFicheroUpload.setEnabled(true);
    }

    void SubirFichero()
    {

    }
}
