package com.example.proyectoandroidtdp.Redondeo

interface Redondeador {
    /**
     * Redondea un numero binario aplicandole Truncado
     * @param numero numero a redondear
     * @param precision cantidad de bits a utilizar
     * @return numero redondeado
     */

    
    fun redondeoTruncado(numero: String?, precision: String?): String?

    /**
     * Redondea un numero binario aplicandole Aumentacion
     * @param numero numero a redondear
     * @param precision cantidad de bits a utilizar
     * @return numero redondeado
     */
    fun redondeoAumentacion(numero: String?, precision: String?): String?

    /**
     * Redondea un numero binario aplicandole Proximidad Biased
     * @param numero numero a redondear
     * @param precision cantidad de bits a utilizar
     * @return numero redondeado
     */
    fun redondeoBiased(numero: String?, precision: String?): String?

    /**
     * Redondea un numero binario aplicandole Proximidad Unbiased
     * @param numero numero a redondear
     * @param precision cantidad de bits a utilizar
     * @return numero redondeado
     */
    fun redondeoUnbiased(numero: String?, precision: String?): String?
}