package com.example.proyectoandroidtdp.CambioDeBase;

import android.text.InputFilter;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;

public class BaseSeleccionadaOctal extends BaseSeleccionada {

    private int base;

    public BaseSeleccionadaOctal() {
        super();
        base = 8;
    }

    public InputFilter getFiltro() {
        return creadorDeFiltros.getFiltroOctalFraccionario();
    }

    public void handle(EditText editText) {
        int tipoDeEntrada = (InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editText.setInputType(tipoDeEntrada);
    }

    @Override
    public int getBase() {
        return base;
    }

    @Override
    public void setResultados(CambioDeBaseFragment fragment) {

        TextView primerText = fragment.getPrimerTextView();
        TextView segundoText = fragment.getSegundoTextView();
        TextView tercerText = fragment.getTercerTextView();
        TextView numero = fragment.getTextViewPrincipal();

        String numOctal1 = numero.getText().toString();

        String numDecimal1 = convertidor.toDecimal(numOctal1, 8);
        String numBinario1 = convertidor.fromDecimal(numDecimal1, 2);
        String numHexa1 = convertidor.fromDecimal(numDecimal1, 16);

        primerText.setText(numBinario1);
        segundoText.setText(numDecimal1);
        tercerText.setText(numHexa1);



    }

    @Override
    public void setLabels(CambioDeBaseFragment fragment) {
        fragment.getLabelBase1().setText("BIN");
        fragment.getLabelBase2().setText("DEC");
        fragment.getLabelBase3().setText("HEX");
    }
}
