package com.example.proyectoandroidtdp.CambioDeBase;

import android.text.InputFilter;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BaseSeleccionadaHexadecimal extends BaseSeleccionada {

    private int base;

    public BaseSeleccionadaHexadecimal(){
        super();
        base = 16;
    }

    public InputFilter getFiltroEntero(){
        return creadorDeFiltros.getFiltroHexaEntero();
    }
    public InputFilter getFiltroFraccionario() {
        return creadorDeFiltros.getFiltroHexaFraccionario();
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

    @Override
    public void setResultados(CambioDeBaseFragment fragment) {
        TextView primerText = fragment.getPrimerTextView();
        TextView segundoText = fragment.getSegundoTextView();
        TextView tercerText = fragment.getTercerTextView();
        TextView numero = fragment.getTextViewPrincipal();


        String numHexa3 = numero.getText().toString();

        //Cuento la cantidad de puntos del string
        if(cantPuntos(numHexa3) > 1)
            //Error, no se convierte
            Toast.makeText(fragment.getActivity(),"El número contiene más de 1 punto decimal.\n"+
                    "Por favor, ingréselo correctamente.",
                    Toast.LENGTH_SHORT).show();
        else{
            String numDecimal3 = convertidor.toDecimal(numHexa3,16);
            String numOctal3 = convertidor.fromDecimal(numDecimal3,8);
            String numBinario3 = convertidor.fromDecimal(numDecimal3,2);
            primerText.setText(numBinario3);
            segundoText.setText(numOctal3);
            tercerText.setText(numDecimal3);
        }

    }


    private int cantPuntos(String numero){
        int cantPuntos=0;
        for(int i = 0; i < numero.length(); i++)
            if(numero.charAt(i) == '.')
                cantPuntos++;
        return cantPuntos;
    }

    @Override
    public void setLabels(CambioDeBaseFragment fragment) {
        fragment.getLabelBase1().setText("BIN");
        fragment.getLabelBase2().setText("OCT");
        fragment.getLabelBase3().setText("DEC");
    }


}
