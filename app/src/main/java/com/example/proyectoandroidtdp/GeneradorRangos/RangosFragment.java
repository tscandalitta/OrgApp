package com.example.proyectoandroidtdp.GeneradorRangos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.proyectoandroidtdp.Filtros.CreadorDeFiltros;
import com.example.proyectoandroidtdp.R;


public class RangosFragment extends Fragment {

    CalculadorRangos calculadorRangos;
    TextView rangoSM, rangoRC, rangoDRC;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_rangos, container, false);

        EditText cantidadBits = view.findViewById(R.id.txt_cantidad_bits);

        rangoSM = view.findViewById(R.id.txtRango2);
        rangoRC = view.findViewById(R.id.txtRango1);
        rangoDRC = view.findViewById(R.id.txtRango3);

        CreadorDeFiltros creadorDeFiltros = new CreadorDeFiltros();

        InputFilter[] arr = {creadorDeFiltros.getFiltroDecimalEntero(), new InputFilter.LengthFilter(2)};
        cantidadBits.setFilters(arr);

        calculadorRangos = new CalculadorRangos();


        cantidadBits.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length()!=0 && Integer.parseInt(s.toString())>=2){

                    rangoSM.setText(calculadorRangos.getRangoSM(Integer.parseInt(s.toString())));
                    rangoRC.setText(calculadorRangos.getRangoRC(Integer.parseInt(s.toString())));
                    rangoDRC.setText(calculadorRangos.getRangoDRC(Integer.parseInt(s.toString())));
                }
                else{
                    rangoDRC.setText("");
                    rangoRC.setText("");
                    rangoSM.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }

}
