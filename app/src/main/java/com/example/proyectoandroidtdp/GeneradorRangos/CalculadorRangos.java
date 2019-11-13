package com.example.proyectoandroidtdp.GeneradorRangos;

import com.example.proyectoandroidtdp.OperacionesAritmeticas.OverflowException;

public class CalculadorRangos implements CalculadorRangosAbstracto {

    public CalculadorRangos(){

    }

    public String getRangoSM(int base, int cantidadBits) throws OverflowException {

        int maximo = (int) Math.pow(base, cantidadBits - 1) - 1;

        if(maximo == Integer.MAX_VALUE - 1)
            throw new OverflowException();

        return "(-" + maximo + ", " + maximo + ")";
    }

    public String getRangoDRC(int base, int cantidadBits) throws OverflowException{
        return getRangoSM(base, cantidadBits);
    }

    public String getRangoRC(int base, int cantidadBits) throws OverflowException{

        int maximo = (int) Math.pow(base, cantidadBits - 1) - 1;
        int minimo = maximo + 1;

        if(maximo == Integer.MAX_VALUE - 1)
            throw new OverflowException();

        return "(-" + minimo + ", " + maximo + ")";
    }



}
