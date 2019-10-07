package com.example.proyectoandroidtdp.GeneradorRangos;

public class CalculadorRangos implements CalculadorRangosAbstracto {

    public CalculadorRangos(){

    }

    public String getRangoSM(int base, int cantidadBits){

        int maximo = (int) Math.pow(base, cantidadBits - 1) - 1;

        return "(-" + maximo + ", " + maximo + ")";
    }

    public String getRangoDRC(int base, int cantidadBits){
        return getRangoSM(base, cantidadBits);
    }

    public String getRangoRC(int base, int cantidadBits){

        int maximo = (int) Math.pow(base, cantidadBits - 1) - 1;
        int minimo = maximo + 1;

        return "(-" + minimo + ", " + maximo + ")";
    }



}
