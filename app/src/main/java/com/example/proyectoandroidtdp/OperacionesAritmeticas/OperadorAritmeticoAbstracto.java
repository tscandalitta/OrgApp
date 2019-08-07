package com.example.proyectoandroidtdp.OperacionesAritmeticas;

import java.security.InvalidParameterException;

public interface OperadorAritmeticoAbstracto {

    String operarSM(String numX, String numY, int base) throws OverflowException, InvalidParameterException;
    String operarDRC(String numX, String numY, int base) throws OverflowException,InvalidParameterException;
    String operarRC(String numX, String numY, int base) throws OverflowException, InvalidParameterException;



}
