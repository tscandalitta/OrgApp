package com.example.proyectoandroidtdp.Filtros;

import android.text.InputFilter;

public interface CreadorDeFiltrosAbstracto {

    /**
     * Crea y devuelve un InputFilter para numeros fraccionarios en base 2.
     * @return InputFilter para numeros binarios fraccionarios
     */
    InputFilter getFiltroBinarioFraccionario();

    /**
     * Crea y devuelve un InputFilter para numeros fraccionarios signados en base 2
     * @return InputFilter para numeros binarios fraccionarios signados
     */
    InputFilter getFiltroBinarioFraccionarioSignado();

    /**
     * Crea y devuelve un InputFilter para numeros fraccionarios en base 8.
     * @return InputFilter para numeros octales fraccionarios
     */
    InputFilter getFiltroOctalFraccionario();

    /**
     * Crea y devuelve un InputFilter para numeros fraccionarios en base 10.
     * @return InputFilter para numeros decimales fraccionarios
     */
    InputFilter getFiltroDecimalFraccionario();

    /**
     * Crea y devuelve un InputFilter para numeros fraccionarios en base 16.
     * @return InputFilter para numeros hexadecimales fraccionarios
     */
    InputFilter getFiltroHexaFraccionario();

    /**
     * Crea y devuelve un InputFilter para numeros enteros en base 2.
     * @return InputFilter para numeros enteros binarios.
     */
    InputFilter getFiltroBinarioEntero();

    /**
     * Crea y devuelve un InputFilter para numeros enteros en base 8.
     * @return InputFilter para numeros enteros octales.
     */
    InputFilter getFiltroOctalEntero();

    /**
     * Crea y devuelve un InputFilter para numeros enteros en base 10.
     * @return InputFilter para numeros enteros decimales.
     */
    InputFilter getFiltroDecimalEntero();

    /**
     * Crea y devuelve un InputFilter para numeros enteros en base 16.
     * @return InputFilter para numeros enteros hexadecimales.
     */
    InputFilter getFiltroHexaEntero();

}
