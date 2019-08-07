package com.example.proyectoandroidtdp.SistemasDeRepresentacion;

import java.security.InvalidParameterException;

public interface ComplementadorAbstracto {
    String convertirRCtoDRC(String numero, int base);
    String convertirDRCtoRC(String numero, int base);
    String toDRC(String numero, int base);
    String toRC(String numero, int base)throws InvalidParameterException;



}
