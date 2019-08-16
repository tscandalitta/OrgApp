package com.example.proyectoandroidtdp.Hamming;

public interface GeneradorHammingAbstracto {

    String getHamming3(String msg);
    String getHamming4(String msg);
    int[] getBitsCodigo(String msg);
    String getBitsMensaje(String msg);
    String verificarHamming3(String msg, int politica);
    String verificarHamming4(String msg, int politica);
}
