package com.example.proyectoandroidtdp.Hamming;

public interface GeneradorHammingAbstracto {

    String getHamming3(String msg);
    String getHamming4(String msg);
    int[] bitsCodigo(String msg);
}
