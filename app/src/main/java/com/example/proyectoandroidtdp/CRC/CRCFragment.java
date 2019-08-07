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

import com.example.proyectoandroidtdp.Filtros.CreadorDeFiltros;
import com.example.proyectoandroidtdp.Filtros.CreadorDeFiltrosAbstracto;
import com.example.proyectoandroidtdp.R;

import java.security.InvalidParameterException;

public class CRCFragment extends Fragment {

    GeneradorCRCAbstracto calculadorCRC;
    EditText txtMensaje, txtGenerador, txtMensajeRecibido, txtGeneradorRecibido;
    TextView txtMensajeFinal, txtCRCFinal;
    Button btnCalcular, btnVerificar;
    String mensaje, generador, resto, mensajeRecibido, generadorRecibido;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_crc, container, false);

        txtMensaje = view.findViewById(R.id.txtCRCMensaje);
        txtGenerador = view.findViewById(R.id.txtCRCGenerador);
        txtMensajeRecibido = view.findViewById(R.id.txtCRCMensajeRecibido);
        txtGeneradorRecibido = view.findViewById(R.id.txtCRCGeneradorRecibido);
        txtMensajeFinal = view.findViewById(R.id.txtCRC1);
        txtCRCFinal = view.findViewById(R.id.txtCRC2);
        btnCalcular = view.findViewById(R.id.btnCalcularCRC);
        btnVerificar = view.findViewById(R.id.btnVerificarCRC);
        calculadorCRC = new GeneradorCRC();

        CreadorDeFiltrosAbstracto creadorDeFiltros = new CreadorDeFiltros();
        InputFilter[] filtros = {creadorDeFiltros.getFiltroBinarioEntero(), new InputFilter.LengthFilter(16)};
        InputFilter[] filtrosGenerador = {creadorDeFiltros.getFiltroBinarioEntero(), new InputFilter.LengthFilter(12)};
        txtMensaje.setFilters(filtros);
        txtGenerador.setFilters(filtrosGenerador);
        txtMensajeRecibido.setFilters(filtros);
        txtGeneradorRecibido.setFilters(filtrosGenerador);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensaje = txtMensaje.getText().toString();
                generador = txtGenerador.getText().toString();
                try {
                    resto = calculadorCRC.generarCRC(mensaje, generador);
                    txtMensajeFinal.setText(mensaje);
                }
                catch (InvalidParameterException e){
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
                    toast.show();
                }
                txtCRCFinal.setText(resto);
            }
        });
        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensajeRecibido = txtMensajeRecibido.getText().toString();
                generadorRecibido = txtGeneradorRecibido.getText().toString();
                Toast toast;
                try {
                    if (calculadorCRC.verificarCRC(mensajeRecibido, generadorRecibido))
                        toast = Toast.makeText(getActivity().getApplicationContext(), "Mensaje recibido correctamente", Toast.LENGTH_SHORT);
                    else
                        toast = Toast.makeText(getActivity().getApplicationContext(), "Mensaje recibido con errores", Toast.LENGTH_SHORT);
                }
                catch (InvalidParameterException e){
                    toast = Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
                }
                toast.show();
            }
        });
        return view;
    }
}
