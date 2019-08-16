package com.example.proyectoandroidtdp.Hamming;

import java.security.InvalidParameterException;

public interface GeneradorHammingAbstracto {

    String getHamming3(String msg);
    String getHamming4(String msg);
    int[] getBitsCodigo(String msg);
    String getBitsMensaje(String msg);
    String verificarHamming3(String msg, int politica) throws InvalidParameterException;
    String verificarHamming4(String msg, int politica) throws InvalidParameterException ;
}
