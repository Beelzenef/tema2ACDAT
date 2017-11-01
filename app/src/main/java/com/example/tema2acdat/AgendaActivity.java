package com.example.tema2acdat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tema2acdat.operandoficheros.OperandoMemoria;
import com.example.tema2acdat.operandoficheros.Resultado;

public class AgendaActivity extends AppCompatActivity {

    private EditText edT_NumeroContacto;
    private EditText edT_NombreContacto;
    private EditText edT_CorreoContacto;
    private TextView txtV_ListaContactos;

    private OperandoMemoria operandoMemoria;

    Resultado resultado;

    private String contactoAGuardar;
    private static final String RUTAFICHERO = "agenda.txt";
    private static final String CODIFICACION = "UTF-8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        identificandoVistas();

        operandoMemoria = new OperandoMemoria(this);

    }

    void identificandoVistas() {
        edT_CorreoContacto = (EditText) findViewById(R.id.edT_CorreoContacto);
        edT_NombreContacto = (EditText) findViewById(R.id.edT_NombreContacto);
        edT_NumeroContacto = (EditText) findViewById(R.id.edT_NumeroContacto);
        txtV_ListaContactos = (TextView) findViewById(R.id.txtV_ListaContactos);
    }

    private void guardarDatos() {

        // Check de campos vacios
        if (edT_CorreoContacto.getText().length() == 0 ||
                edT_NumeroContacto.getText().length() == 0 ||
                edT_NombreContacto.getText().length() == 0) {
            Toast.makeText(this, "Faltan datos que guardar", Toast.LENGTH_SHORT).show();
        }
        else {
            // Armando contacto
            contactoAGuardar = edT_NombreContacto.getText().toString() + "; "
                    + edT_NumeroContacto.getText().toString() + "; "
                    + edT_CorreoContacto.getText().toString() + "\n";
            // Guardando en memoria interna
            operandoMemoria.escribirInterna(RUTAFICHERO, contactoAGuardar, true, CODIFICACION);

            Toast.makeText(this, "Guardando contacto", Toast.LENGTH_SHORT).show();
        }
    }

    private void consultarDatos() {
        resultado = operandoMemoria.leerInterna(RUTAFICHERO, CODIFICACION);

        if (resultado.getCodigo()) {
            txtV_ListaContactos.setText(resultado.getContenido());
            Toast.makeText(this, "Mostrando contactos", Toast.LENGTH_SHORT).show();
        }
        else {
            txtV_ListaContactos.setText("");
            Toast.makeText(getApplicationContext(), "Error al leer lista de contactos", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClick_Contactos(View v) {
        switch (v.getId()) {
            case R.id.btn_ConsultarDatos:
                consultarDatos();
                break;
            case R.id.btn_GuardarDatos:
                guardarDatos();
                break;
        }
    }
}
