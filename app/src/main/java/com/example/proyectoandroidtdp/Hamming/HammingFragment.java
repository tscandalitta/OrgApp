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
    TextView txtHamming3, txtHamming4;
    Button btnCalcular;
    String mensaje;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hamming, container, false);

        generador = new GeneradorHamming();
        txtMensaje = view.findViewById(R.id.txtMensajeHamming);
        txtHamming3 = view.findViewById(R.id.txtResultHamming3);
        txtHamming4 = view.findViewById(R.id.txtResultHamming4);
        btnCalcular = view.findViewById(R.id.btnCalcularHamming);

        CreadorDeFiltrosAbstracto creadorDeFiltros = new CreadorDeFiltros();
        InputFilter[] filtros = {creadorDeFiltros.getFiltroBinarioEntero(), new InputFilter.LengthFilter(16)};
        txtMensaje.setFilters(filtros);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensaje = txtMensaje.getText().toString();
                txtHamming3.setText(generador.getHamming3(mensaje));
                txtHamming4.setText(generador.getHamming4(mensaje));
            }
        });
        return view;
    }
}
