package com.example.proyectoandroidtdp.CambioDeBase;

public interface ConvertidorAbstracto {

    String toDecimal(String numero, int baseOrigen);
    String fromDecimal(String numero, int baseDestino);
}
