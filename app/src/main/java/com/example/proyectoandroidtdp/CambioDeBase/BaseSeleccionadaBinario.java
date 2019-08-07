package com.example.proyectoandroidtdp.CambioDeBase;

import android.text.InputFilter;
import android.text.InputType;
import android.widget.EditText;

public class BaseSeleccionadaBinario extends BaseSeleccionada {

    private int base ;

    public BaseSeleccionadaBinario(){
        super();
        base = 2;
    }

    public InputFilter getFiltro() {
        return creadorDeFiltros.getFiltroBinarioEntero();
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
