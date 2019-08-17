package com.example.proyectoandroidtdp.Redondeo;

public interface RedondeadorAbstracto {


    /**
     * Redondea un numero binario aplicandole Truncado
     * @param numero numero a redondear
     * @param precision cantidad de bits a utilizar
     * @return numero redondeado
     */
    String redondeoTruncado(String numero, String precision);

    /**
     * Redondea un numero binario aplicandole Aumentacion
     * @param numero numero a redondear
     * @param precision cantidad de bits a utilizar
     * @return numero redondeado
     */
    String redondeoAumentacion(String numero, String precision);

    /**
     * Redondea un numero binario aplicandole Proximidad Biased
     * @param numero numero a redondear
     * @param precision cantidad de bits a utilizar
     * @return numero redondeado
     */
    String redondeoBiased(String numero, String precision);

    /**
     * Redondea un numero binario aplicandole Proximidad Unbiased
     * @param numero numero a redondear
     * @param precision cantidad de bits a utilizar
     * @return numero redondeado
     */
    String redondeoUnbiased(String numero, String precision);
}
