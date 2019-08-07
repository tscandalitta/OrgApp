package com.example.proyectoandroidtdp.CambioDeBase;

import android.text.InputFilter;
import android.text.InputType;
import android.widget.EditText;

public class BaseSeleccionadaHexadecimal extends BaseSeleccionada {

    private int base;

    public BaseSeleccionadaHexadecimal(){
        super();
        base = 16;
    }

    @Override
    public InputFilter getFiltro() {
        return creadorDeFiltros.getFiltroHexaEntero();
    }

    @Override
    public void handle(EditText editText) {
        int tipoDeEntrada = (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD |
                InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS  );
        editText.setInputType(tipoDeEntrada);
    }

    public int getBase() {
        return base;
    }
}
