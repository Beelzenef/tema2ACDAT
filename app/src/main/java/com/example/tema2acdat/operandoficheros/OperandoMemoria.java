package com.example.tema2acdat.operandoficheros;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


/**
 * Escribiendo en memoria interna y en memoria externa
 *
 * @author Elena G (Beelzenef)
 */

public class OperandoMemoria {

    private Context contexto;

    public OperandoMemoria(Context c) {
        this.contexto = c;
    }

    // Metodos para escribir

    // Para operar con memoria interna:


    public boolean escribirInterna(String fichero, String cadena, Boolean anadir, String codigo) {
        File miFichero;
        miFichero = new File(contexto.getFilesDir(), fichero);
        return escribir(miFichero, cadena, anadir, codigo);
    }

    private boolean escribir(File fichero, String cadena, Boolean anadir, String codigo) {
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter out = null;
        boolean correcto = false;
        try {
            fos = new FileOutputStream(fichero, anadir);
            osw = new OutputStreamWriter(fos, codigo);
            out = new BufferedWriter(osw);
            out.write(cadena);
        } catch (IOException e) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                    correcto = true;
                }
            } catch (IOException e) {
            }
        }
        return correcto;
    }


    // De memoria interna

    public Resultado leerInterna(String fichero, String codigo) {
        File miFichero;
        //mifichero = new File(getApplicationContext().getFilesDir(), nombreFichero);
        miFichero = new File(contexto.getFilesDir(), fichero);
        return leer(miFichero, codigo);
    }

    private Resultado leer(File fichero, String codigo) {
        FileInputStream fis = null;
        InputStreamReader isw = null;
        BufferedReader in = null;
        //String linea;
        StringBuilder miCadena = new StringBuilder();
        Resultado resultado = new Resultado();
        int n;
        resultado.setCodigo(true);
        try {
            fis = new FileInputStream(fichero);
            isw = new InputStreamReader(fis, codigo);
            in = new BufferedReader(isw);
            while ((n = in.read()) != -1)
                miCadena.append((char) n);
        } catch (IOException e) {

            resultado.setCodigo(false);
            resultado.setMensaje(e.getMessage());
        } finally {
            try {
                if (in != null) {
                    in.close();
                    resultado.setContenido(miCadena.toString());
                }
            } catch (IOException e) {
                //Log.e("Error al cerrar", e.getMessage());
                resultado.setCodigo(false);
                resultado.setMensaje(e.getMessage());
            }
        }
        return resultado;
    }
}

