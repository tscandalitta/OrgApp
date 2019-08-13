package com.example.proyectoandroidtdp.Hamming;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.proyectoandroidtdp.Filtros.CreadorDeFiltros;
import com.example.proyectoandroidtdp.Filtros.CreadorDeFiltrosAbstracto;
import com.example.proyectoandroidtdp.R;


public class HammingFragment extends Fragment {

    GeneradorHammingAbstracto generador;
    EditText txtMensaje;
    TextView txtHamming3, txtHamming4, bit1, bit2, bit3, bit4, bit5;
    Button btnCalcular;
    String mensaje;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hamming, container, false);

        generador = new GeneradorHamming();
        inicializarTextViews(view);
        CreadorDeFiltrosAbstracto creadorDeFiltros = new CreadorDeFiltros();
        InputFilter[] filtros = {creadorDeFiltros.getFiltroBinarioEntero(), new InputFilter.LengthFilter(16)};
        txtMensaje.setFilters(filtros);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensaje = txtMensaje.getText().toString();
                String hamming3 = generador.getHamming3(mensaje);
                txtHamming3.setText(hamming3);
                txtHamming4.setText(generador.getHamming4(mensaje));
                mostrarBitsCodigo(hamming3);
            }
        });
        return view;
    }

    private void mostrarBitsCodigo(String msg){
        int[] bitsCodigo = generador.bitsCodigo(msg);
        limpiarBitsCodigo();
        switch(bitsCodigo.length){
            case 5:
                bit5.setText(Integer.toString(bitsCodigo[4]));
            case 4:
                bit4.setText(Integer.toString(bitsCodigo[3]));
            case 3:
                bit3.setText(Integer.toString(bitsCodigo[2]));
            case 2:
                bit2.setText(Integer.toString(bitsCodigo[1]));
            case 1:
                bit1.setText(Integer.toString(bitsCodigo[0]));
        }
    }

    private void limpiarBitsCodigo(){
        bit5.setText("-");
        bit4.setText("-");
        bit3.setText("-");
        bit2.setText("-");
        bit1.setText("-");
    }

    private void inicializarTextViews(View view){
        txtMensaje = view.findViewById(R.id.txtMensajeHamming);
        txtHamming3 = view.findViewById(R.id.txtResultHamming3);
        txtHamming4 = view.findViewById(R.id.txtResultHamming4);
        btnCalcular = view.findViewById(R.id.btnCalcularHamming);
        bit1 = view.findViewById(R.id.txtHammingC1);
        bit2 = view.findViewById(R.id.txtHammingC2);
        bit3 = view.findViewById(R.id.txtHammingC3);
        bit4 = view.findViewById(R.id.txtHammingC4);
        bit5 = view.findViewById(R.id.txtHammingC5);
    }
}
