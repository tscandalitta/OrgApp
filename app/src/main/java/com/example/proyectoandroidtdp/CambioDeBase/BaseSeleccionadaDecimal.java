package com.example.proyectoandroidtdp.CambioDeBase;

import android.text.InputFilter;
import android.text.InputType;
import android.widget.EditText;

public class BaseSeleccionadaDecimal extends BaseSeleccionada {

    private int base;

    public BaseSeleccionadaDecimal() {
        super();
        base = 10;
    }

    public InputFilter getFiltro() {
        return creadorDeFiltros.getFiltroDecimalEntero();
    }

    @Override
    public void handle(EditText editText) {
        int tipoDeEntrada = (InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editText.setInputType(tipoDeEntrada);
    }

    public int getBase() {
        return base;
    }
}


