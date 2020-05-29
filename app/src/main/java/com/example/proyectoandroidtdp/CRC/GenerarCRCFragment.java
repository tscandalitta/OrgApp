package com.example.proyectoandroidtdp.CRC;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoandroidtdp.filtros.CreadorDeFiltros;
import com.example.proyectoandroidtdp.filtros.CreadorDeFiltrosAbstracto;
import com.example.proyectoandroidtdp.R;

import java.security.InvalidParameterException;

public class GenerarCRCFragment extends Fragment {

    GeneradorCRCAbstracto calculadorCRC;
    EditText txtMensaje, txtGenerador;
    TextView txtMensajeFinal, txtCRCFinal;
    Button btnCalcular;
    String mensaje, generador, resto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_generar_crc, container, false);

        txtMensaje = view.findViewById(R.id.txtCRCMensaje);
        txtGenerador = view.findViewById(R.id.txtCRCGenerador);
        txtMensajeFinal = view.findViewById(R.id.txtCRC1);
        txtCRCFinal = view.findViewById(R.id.txtCRC2);
        btnCalcular = view.findViewById(R.id.btnCalcularCRC);
        calculadorCRC = new GeneradorCRC();

        CreadorDeFiltrosAbstracto creadorDeFiltros = new CreadorDeFiltros();
        InputFilter[] filtros = {creadorDeFiltros.getFiltroBinarioEntero(), new InputFilter.LengthFilter(16)};
        InputFilter[] filtrosGenerador = {creadorDeFiltros.getFiltroBinarioEntero(), new InputFilter.LengthFilter(12)};
        txtMensaje.setFilters(filtros);
        txtGenerador.setFilters(filtrosGenerador);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resto = "";
                mensaje = txtMensaje.getText().toString();
                generador = txtGenerador.getText().toString();
                if(mensaje.length() > 0 && generador.length() > 0)
                    try {
                        resto = calculadorCRC.generarCRC(mensaje, generador);
                        txtMensajeFinal.setText(mensaje);
                    } catch (InvalidParameterException e) {
                        Toast toast = Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
                        toast.show();
                        txtMensajeFinal.setText("");
                    }
                else
                    txtMensajeFinal.setText("");
                txtCRCFinal.setText(resto);
            }
        });

        return view;
    }
}
