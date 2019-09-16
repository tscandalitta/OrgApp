package com.example.proyectoandroidtdp.CRC;

import java.security.InvalidParameterException;

public interface GeneradorCRCAbstracto {


    /**
     * Genera codigo crc a partir de un mensaje y un polinomio generador
     * @param msj mensaje a transmitir
     * @param gen polinomio generador
     * @return codigo binario a transmitir
     * @throws InvalidParameterException si la longitud del mensaje es menor que la del polinomio
     * generador o si el poliniomio generador es inválido
     */
    String generarCRC(String msj, String gen) throws InvalidParameterException;

    /**
     * Verifica si el resto es nulo
     * @param resto resto del calculo de CRC
     * @return true si el resto es nulo, false en caso contrario
     */
    boolean restoNulo(String resto);
}
