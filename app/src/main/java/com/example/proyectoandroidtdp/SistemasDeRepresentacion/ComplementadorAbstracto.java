package com.example.proyectoandroidtdp.SistemasDeRepresentacion;

import java.security.InvalidParameterException;

public interface ComplementadorAbstracto {

    /**
     * Transforma un numero en Complemento a la Base en un numero en Complemento a la Base Disminuida.
     * @param numero numero en Complemento a la Base a tranformar.
     * @param base base del numero ingresado.
     * @return numero en complemento a la base Disminuida transformado.
     */
    String convertirRCtoDRC(String numero, int base);

    /**
     * Transforma un numero en Complemento a la Base Disminuida en un numero en Complemento a la Base.
     * @param numero numero en Complemento a la Base Disminuida a tranformar.
     * @param base base del numero ingresado.
     * @return numero en Complemento a la Base transformado.
     */
    String convertirDRCtoRC(String numero, int base);

    /**
     * Transforma un numero en Signo Magnitud a un numero en Complemento a la Base Disminuida.
     * @param numero numero en Signo Magnitud a transformar.
     * @param base base del numero ingresado.
     * @return numero en Complemento a la Base Disminuida
     */
    String toDRC(String numero, int base);

    /**
     * Transforma un numero en Signo Magnitud a un numero en Complemento a la Base.
     * @param numero numero en Signo Magnitud a transformar.
     * @param base base del numero ingresado.
     * @return numero en Complemento a la Base.
     */
    String toRC(String numero, int base)throws InvalidParameterException;



}
