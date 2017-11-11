package com.example.tema2acdat.divisas;

/**
 * Clase Conversor de cambio de divisas (EUR/USD)
 * @author Elena G (Beelzenef)
 */

public final class Conversor {

    // Variables
    private double cambioMoneda;

    // Constructores

    public Conversor()
    {
        this.cambioMoneda = 0.84;
    }

    public Conversor(double cM)
    {
        this.cambioMoneda = cM;
    }

    // Métodos

    public String cambioADolares(String valor)
    {
        return Double.toString(Double.parseDouble(valor) / cambioMoneda);
    }

    public String cambioAEuros(String valor)
    {
        return Double.toString(Double.parseDouble(valor) * cambioMoneda);
    }

}