package com.example.tema2acdat;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DiasLectivosActivity extends AppCompatActivity {

    EditText edT_FechaInicio;
    EditText edT_FechaFin;
    TextView txtV_DiasLectivos;

    Date fInicio;
    Date fFin;

    SimpleDateFormat formateador;

    Calendar calendario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dias_lectivos);

        formateador = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
calendario = Calendar.getInstance();

        inicializarVistas();
    }

    private void inicializarVistas()
    {
        edT_FechaFin = (EditText) findViewById(R.id.edT_FechaFin);
        edT_FechaInicio = (EditText) findViewById(R.id.edT_FechaInicio);
        txtV_DiasLectivos = (TextView) findViewById(R.id.txtV_DiasLectivos);

    }

    // Para operar con fechas

    private void tomarFechas()
    {

        if (edT_FechaInicio.getText().length() != 0 && edT_FechaFin.getText().length() != 0)
        {
            try {
                fInicio = formateador.parse(edT_FechaInicio.getText().toString());
                fFin = formateador.parse(edT_FechaFin.getText().toString());

                if (fechasCorrectas(fInicio, fFin))
                {
                    calcularDiasLectivos();
                }
                else {
                    Toast.makeText(this, "Fecha de inicio anterior a la de fin", Toast.LENGTH_SHORT).show();
                }
            }
            catch (ParseException e)
            {
                Toast.makeText(this, "Error al convertir fechas", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Sin fechas para operar", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcularDiasLectivos()
    {

    }

    private boolean fechasCorrectas(Date fInicio, Date fFin)
    {
        return fFin.after(fInicio);
    }

    // Tomando fechas

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            calendario.set(Calendar.YEAR, year);
            calendario.set(Calendar.MONTH, monthOfYear);
            calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        }

    };

    private void ElegirFecha()
    {
        new DatePickerDialog(this, date, calendario.get(Calendar.YEAR),
                calendario.get(Calendar.MONTH),
                calendario.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void actualizarEdTs(EditText edT)
    {
        edT.setText(formateador.format(calendario.getTime()));
    }

    public void onClick_DiasLectivos(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_CalcularDias:
                tomarFechas();
                break;
            case R.id.btn_FechaFinal:
                ElegirFecha();
                actualizarEdTs(edT_FechaFin);
                break;
            case R.id.btn_FechaInicio:
                ElegirFecha();
                actualizarEdTs(edT_FechaInicio);
                break;
        }
    }
}
