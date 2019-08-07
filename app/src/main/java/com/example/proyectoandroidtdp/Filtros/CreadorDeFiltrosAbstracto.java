package com.example.proyectoandroidtdp.Filtros;

import android.text.InputFilter;

public interface CreadorDeFiltrosAbstracto {

    InputFilter getFiltroBinarioFraccionario();
    InputFilter getFiltroOctalFraccionario();
    InputFilter getFiltroDecimalFraccionario();
    InputFilter getFiltroHexaFraccionario();
    InputFilter getFiltroBinarioEntero();
    InputFilter getFiltroOctalEntero();
    InputFilter getFiltroDecimalEntero();
    InputFilter getFiltroHexaEntero();

}
