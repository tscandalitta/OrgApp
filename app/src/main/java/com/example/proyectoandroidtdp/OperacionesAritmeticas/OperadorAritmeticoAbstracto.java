package com.example.proyectoandroidtdp.OperacionesAritmeticas;

import java.security.InvalidParameterException;

public interface OperadorAritmeticoAbstracto {

    /**
     * Realiza la suma de dos numeros en signo magnitud.
     * @param numX operando X.
     * @param numY operando Y.
     * @param base base en la cual se opera.
     * @return devuelve la suma de los operando.
     * @throws OverflowException cuando se produce overflow en la suma.
     * @throws InvalidParameterException cuando detecta algun operando nulo.
     */
    String operarSM(String numX, String numY, int base) throws OverflowException, InvalidParameterException;

    /**
     * Realiza la suma de dos numeros en DRC.
     * @param numX operando X.
     * @param numY operando Y.
     * @param base base en la cual se opera.
     * @return devuelve el resultado de la operacion.
     * @throws OverflowException cuando se produce overflow en la operacion.
     * @throws InvalidParameterException cuando deecta algun operando nulo.
     */
    String operarDRC(String numX, String numY, int base) throws OverflowException,InvalidParameterException;

    /**
     * Realiza la suma de dos operando en RC.
     * @param numX operando X.
     * @param numY operando Y.
     * @param base base en la cual se opera.
     * @return devuelve el resultado de la operacion.
     * @throws OverflowException caundo se produce overflow en la operacion.
     * @throws InvalidParameterException cuando detecta algun operando nulo.
     */
    String operarRC(String numX, String numY, int base) throws OverflowException, InvalidParameterException;



}
