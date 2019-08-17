package com.example.proyectoandroidtdp.CambioDeBase;

import android.text.InputFilter;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;

public class BaseSeleccionadaDecimal extends BaseSeleccionada {

    private int base;

    public BaseSeleccionadaDecimal() {
        super();
        base = 10;
    }

    public InputFilter getFiltro() {
        return creadorDeFiltros.getFiltroDecimalFraccionario();
    }

    @Override
    public void handle(EditText editText) {
        int tipoDeEntrada = (InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editText.setInputType(tipoDeEntrada);
    }

    public int getBase() {
        return base;
    }

    @Override
    public void setResultados(CambioDeBaseFragment fragment) {
        TextView primerText = fragment.getPrimerTextView();
        TextView segundoText = fragment.getSegundoTextView();
        TextView tercerText = fragment.getTercerTextView();
        TextView numero = fragment.getTextViewPrincipal();

        String numDecimal2 = numero.getText().toString();

        String numBinario2 = convertidor.fromDecimal(numDecimal2,2);
        String numOctal2 = convertidor.fromDecimal(numDecimal2,8);
        String numHexa2 = convertidor.fromDecimal(numDecimal2,16);

        primerText.setText(numBinario2);
        segundoText.setText(numOctal2);
        tercerText.setText(numHexa2);

    }

    @Override
    public void setLabels(CambioDeBaseFragment fragment) {
        fragment.getLabelBase1().setText("BIN");
        fragment.getLabelBase2().setText("OCT");
        fragment.getLabelBase3().setText("HEX");
    }
}


