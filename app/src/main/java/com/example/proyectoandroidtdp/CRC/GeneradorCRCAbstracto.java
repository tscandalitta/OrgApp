package com.example.proyectoandroidtdp.CRC;

import java.security.InvalidParameterException;

public interface GeneradorCRCAbstracto {

    String generarCRC(String msj, String gen) throws InvalidParameterException;
    boolean verificarCRC(String msj, String gen);
}
