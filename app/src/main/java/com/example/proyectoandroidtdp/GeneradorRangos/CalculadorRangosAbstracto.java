package com.example.proyectoandroidtdp.GeneradorRangos;

import com.example.proyectoandroidtdp.OperacionesAritmeticas.OverflowException;

public interface CalculadorRangosAbstracto {

    String getRangoSM(int base, int cantidadBits) throws OverflowException;
    String getRangoDRC(int base, int cantidadBits) throws OverflowException;
    String getRangoRC(int base, int cantidadBits) throws OverflowException;


}
