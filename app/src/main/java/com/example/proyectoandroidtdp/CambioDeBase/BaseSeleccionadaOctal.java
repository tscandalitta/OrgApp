package com.example.proyectoandroidtdp.CambioDeBase;

import android.text.InputFilter;
import android.text.InputType;
import android.widget.EditText;

public class BaseSeleccionadaOctal extends BaseSeleccionada {

    private int base;

    public BaseSeleccionadaOctal() {
        super();
        base = 8;
    }

    public InputFilter getFiltro() {
        return creadorDeFiltros.getFiltroOctalEntero();
    }

    public void handle(EditText editText) {
        int tipoDeEntrada = (InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editText.setInputType(tipoDeEntrada);
    }

    @Override
    public int getBase() {
        return base;
    }
}
