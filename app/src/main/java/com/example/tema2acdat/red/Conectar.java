package com.example.tema2acdat.red;

import com.example.tema2acdat.operandoficheros.Resultado;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.CloseableHttpClient;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;

/**
 * Created by usuario on 7/11/17.
 */

public class Conectar {


    private static String leer(InputStream entrada) throws IOException {
        BufferedReader in;
        String linea;
        StringBuilder miCadena = new StringBuilder();
        in = new BufferedReader(new InputStreamReader(entrada), 32000);
        while ((linea = in.readLine()) != null)
            miCadena.append(linea);
//miCadena.append(linea).append('\n');
        in.close();
        return miCadena.toString();
    }

    public static Resultado conectarJava(String texto) {
        URL url;
        HttpURLConnection urlConnection = null;
        int respuesta;
        Resultado resultado = new Resultado();
        try {
            url = new URL(texto);
            urlConnection = (HttpURLConnection) url.openConnection();
            respuesta = urlConnection.getResponseCode();
            if (respuesta == HttpURLConnection.HTTP_OK) {
                resultado.setCodigo(true);
                resultado.setContenido(leer(urlConnection.getInputStream()));
            } else {
                resultado.setCodigo(false);
                resultado.setMensaje("Error en el acceso a la web: " + String.valueOf(respuesta));
            }
        } catch (IOException e) {
            resultado.setCodigo(false);
            resultado.setMensaje("Excepción: " + e.getMessage());
        } finally {
            try {
                if (urlConnection != null)
                    urlConnection.disconnect();
            } catch (Exception e) {
                resultado.setCodigo(false);
                resultado.setMensaje("Excepción: " + e.getMessage());
            }
            return resultado;
        }
    }

    public static Resultado conectarApache(String texto) {
        CloseableHttpClient cliente = null;
        HttpPost peticion;
        HttpResponse respuesta;
        int valor;
        Resultado resultado = new Resultado();
        try {
            //cliente = new DefaultHttpClient();
            cliente = HttpClientBuilder.create().build();
            peticion = new HttpPost(texto);
            respuesta = cliente.execute(peticion);
            valor = respuesta.getStatusLine().getStatusCode();
            if (valor == HttpURLConnection.HTTP_OK) {
                resultado.setCodigo(true);
                resultado.setContenido(leer(respuesta.getEntity().getContent()));
            } else {
                resultado.setCodigo(false);
                resultado.setMensaje("Error en el acceso a la web: " + String.valueOf(valor));
            }
            cliente.close();
        } catch (IOException e) {
            resultado.setCodigo(false);
            resultado.setMensaje("Excepción: " + e.getMessage());
            if (cliente != null)
                try {
                    cliente.close();
                } catch (IOException excep) {
                    resultado.setCodigo(false);
                    resultado.setMensaje("Excepción: " + excep.getMessage());
                }
        }
        return resultado;
    }

}
