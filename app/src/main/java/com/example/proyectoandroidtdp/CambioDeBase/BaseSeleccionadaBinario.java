package com.example.proyectoandroidtdp.CambioDeBase;

import android.text.InputFilter;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;

public class BaseSeleccionadaBinario extends BaseSeleccionada {

    private int base ;

    public BaseSeleccionadaBinario(){
        super();
        base = 2;
    }

    public InputFilter getFiltroEntero() {
        return creadorDeFiltros.getFiltroBinarioEntero();
    }

    public InputFilter getFiltroFraccionario() {
        return creadorDeFiltros.getFiltroBinarioFraccionario();
    }


    public void handle(EditText editText) {
        int tipoDeEntrada = (InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editText.setInputType(tipoDeEntrada);
    }

    public int getBase() {
        return base;
    }


    public void setResultados(CambioDeBaseFragment fragment) {

        TextView primerText = fragment.getPrimerTextView();
        TextView segundoText = fragment.getSegundoTextView();
        TextView tercerText = fragment.getTercerTextView();
        TextView numero = fragment.getTextViewPrincipal();

        String numBinario = numero.getText().toString();

        String numDecimal = convertidor.toDecimal(numBinario, 2);
        String numOctal = convertidor.fromDecimal(numDecimal, 8);
        String numHexa = convertidor.fromDecimal(numDecimal, 16);

        primerText.setText(numOctal);
        segundoText.setText(numDecimal);
        tercerText.setText(numHexa);
    }

    public void setLabels(CambioDeBaseFragment fragment) {
        fragment.getLabelBase1().setText("OCT");
        fragment.getLabelBase2().setText("DEC");
        fragment.getLabelBase3().setText("HEX");
    }


}
