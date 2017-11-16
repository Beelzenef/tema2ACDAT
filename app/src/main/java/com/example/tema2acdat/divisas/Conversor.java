package com.example.tema2acdat.divisas;

import com.example.tema2acdat.operandoficheros.OperandoMemoria;

/**
 * Clase Conversor de cambio de divisas (EUR/USD)
 *
 * @author Elena G (Beelzenef)
 */

public final class Conversor {

    // Variables
    private double cambioMoneda;

    private OperandoMemoria operandoMemoria;

    // Constructores

    public Conversor() {
        cambioDefault();
    }

    // Métodos

    public String cambioADolares(String valor) {
        return Double.toString(Double.parseDouble(valor) / cambioMoneda);
    }

    public String cambioAEuros(String valor) {
        return Double.toString(Double.parseDouble(valor) * cambioMoneda);
    }

    public void setCambioFichero() {
        // Leer cambio desde fichero
    }

    public void cambioDefault() {
        this.cambioMoneda = 0.84;
    }

}