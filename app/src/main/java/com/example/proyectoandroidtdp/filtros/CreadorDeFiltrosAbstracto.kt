package com.example.proyectoandroidtdp.filtros

import android.text.InputFilter

interface CreadorDeFiltrosAbstracto {

    /**
     * Crea y devuelve un InputFilter para numeros fraccionarios en base 2.
     * @return InputFilter para numeros binarios fraccionarios
     */
    val filtroBinarioFraccionario: InputFilter?

    /**
     * Crea y devuelve un InputFilter para numeros fraccionarios signados en base 2
     * @return InputFilter para numeros binarios fraccionarios signados
     */
    val filtroBinarioFraccionarioSignado: InputFilter?

    /**
     * Crea y devuelve un InputFilter para numeros fraccionarios en base 8.
     * @return InputFilter para numeros octales fraccionarios
     */
    val filtroOctalFraccionario: InputFilter?

    /**
     * Crea y devuelve un InputFilter para numeros fraccionarios en base 10.
     * @return InputFilter para numeros decimales fraccionarios
     */
    val filtroDecimalFraccionario: InputFilter?

    /**
     * Crea y devuelve un InputFilter para numeros fraccionarios en base 16.
     * @return InputFilter para numeros hexadecimales fraccionarios
     */
    val filtroHexaFraccionario: InputFilter?

    /**
     * Crea y devuelve un InputFilter para numeros enteros en base 2.
     * @return InputFilter para numeros enteros binarios.
     */
    val filtroBinarioEntero: InputFilter?

    /**
     * Crea y devuelve un InputFilter para numeros enteros en base 8.
     * @return InputFilter para numeros enteros octales.
     */
    val filtroOctalEntero: InputFilter?

    /**
     * Crea y devuelve un InputFilter para numeros enteros en base 10.
     * @return InputFilter para numeros enteros decimales.
     */
    val filtroDecimalEntero: InputFilter?

    /**
     * Crea y devuelve un InputFilter para numeros enteros en base 16.
     * @return InputFilter para numeros enteros hexadecimales.
     */
    val filtroHexaEntero: InputFilter?
}