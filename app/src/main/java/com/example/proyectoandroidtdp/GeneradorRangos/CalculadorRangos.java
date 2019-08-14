package com.example.proyectoandroidtdp.GeneradorRangos;

public class CalculadorRangos implements CalculadorRangosAbstracto {

    public CalculadorRangos(){

    }

    public String getRangoSM(int cantidadBits){

        int maximo = (int) Math.pow(2, cantidadBits - 1) - 1;

        return "(-" + maximo + ", " + maximo + ")";
    }

    public String getRangoDRC(int cantidadBits){
        return getRangoSM(cantidadBits);
    }

    public String getRangoRC(int cantidadBits){

        int maximo = (int) Math.pow(2, cantidadBits - 1) - 1;
        int minimo = maximo + 1;

        return "(-" + minimo + ", " + maximo + ")";
    }



}
