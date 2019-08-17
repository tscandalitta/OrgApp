package com.example.proyectoandroidtdp.CRC;

import java.security.InvalidParameterException;

public interface GeneradorCRCAbstracto {


    /**
     * Genera codigo crc a partir de un mensaje y un polinomio generador
     * @param msj mensaje a transmitir
     * @param gen polinomio generador
     * @return codigo binario a transmitir
     * @throws InvalidParameterException
     */
    String generarCRC(String msj, String gen) throws InvalidParameterException;

    /**
     * Verifica si un mensaje recibido es valido a partir de un polinomio generador
     * @param msj mensaje a transmitir
     * @param gen polinomio generador
     * @return true si el mensaje es valido, false en caso contrario
     */
    boolean verificarCRC(String msj, String gen);
}
