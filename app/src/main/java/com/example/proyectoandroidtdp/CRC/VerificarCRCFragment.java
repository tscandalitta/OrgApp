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

public class VerificarCRCFragment extends Fragment {

    GeneradorCRCAbstracto calculadorCRC;
    EditText txtMensajeRecibido, txtGeneradorRecibido;
    Button btnVerificar;
    String mensajeRecibido, generadorRecibido;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_verificar_crc, container, false);

        calculadorCRC = new GeneradorCRC();
        txtMensajeRecibido = view.findViewById(R.id.txtCRCMensajeRecibido);
        txtGeneradorRecibido = view.findViewById(R.id.txtCRCGeneradorRecibido);
        btnVerificar = view.findViewById(R.id.btnVerificarCRC);

        CreadorDeFiltrosAbstracto creadorDeFiltros = new CreadorDeFiltros();
        InputFilter[] filtros = {creadorDeFiltros.getFiltroBinarioEntero(), new InputFilter.LengthFilter(16)};
        InputFilter[] filtrosGenerador = {creadorDeFiltros.getFiltroBinarioEntero(), new InputFilter.LengthFilter(12)};
        txtMensajeRecibido.setFilters(filtros);
        txtGeneradorRecibido.setFilters(filtrosGenerador);
        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensajeRecibido = txtMensajeRecibido.getText().toString();
                generadorRecibido = txtGeneradorRecibido.getText().toString();
                if(mensajeRecibido.length() > 0 && generadorRecibido.length() > 0){
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
            }
        });
        return view;
    }

}
