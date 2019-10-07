package com.example.proyectoandroidtdp.GeneradorRangos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.proyectoandroidtdp.CambioDeBase.BaseSeleccionada;
import com.example.proyectoandroidtdp.CambioDeBase.BaseSeleccionadaBinario;
import com.example.proyectoandroidtdp.CambioDeBase.BaseSeleccionadaDecimal;
import com.example.proyectoandroidtdp.CambioDeBase.BaseSeleccionadaHexadecimal;
import com.example.proyectoandroidtdp.CambioDeBase.BaseSeleccionadaOctal;
import com.example.proyectoandroidtdp.Filtros.CreadorDeFiltros;
import com.example.proyectoandroidtdp.R;


public class RangosFragment extends Fragment {

    private CalculadorRangos calculadorRangos;
    private TextView rangoSM, rangoRC, rangoDRC;
    private Spinner spinnerDeBases;
    private BaseSeleccionada baseSeleccionada;
    private CharSequence cantBits;
    private int colorTexto;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_rangos, container, false);

        EditText cantidadBits = view.findViewById(R.id.txt_cantidad_bits);

        cantBits = "";

        rangoSM = view.findViewById(R.id.txtRango2);
        rangoRC = view.findViewById(R.id.txtRango1);
        rangoDRC = view.findViewById(R.id.txtRango3);

        colorTexto = cantidadBits.getCurrentTextColor();

        spinnerDeBases = view.findViewById(R.id.spinnerRangos);
        String [] bases = getResources().getStringArray(R.array.arraySpinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(),
                R.layout.item_spinner, R.id.texto_spinner,bases);
        arrayAdapter.setDropDownViewResource(R.layout.item_spinner);
        spinnerDeBases.setAdapter(arrayAdapter);


        spinnerDeBases.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {



             @Override
             public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                 TextView selectedText = adapterView.getChildAt(0).findViewById(R.id.texto_spinner);
                 if (selectedText != null) {
                     selectedText.setTextColor(colorTexto);
                 }
                 switch (position){
                     case 0:
                         baseSeleccionada = new BaseSeleccionadaBinario();
                         break;

                     case 1:
                         baseSeleccionada = new BaseSeleccionadaOctal();
                         break;

                     case 2:
                         baseSeleccionada = new BaseSeleccionadaDecimal();
                         break;

                     default:
                         baseSeleccionada = new BaseSeleccionadaHexadecimal();
                         break;
                 }
                 actualizarRangos(cantBits);
             }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        CreadorDeFiltros creadorDeFiltros = new CreadorDeFiltros();

        InputFilter[] filtros = {creadorDeFiltros.getFiltroDecimalEntero(), new InputFilter.LengthFilter(2)};
        cantidadBits.setFilters(filtros);

        calculadorRangos = new CalculadorRangos();

        cantidadBits.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                cantBits = s;
                actualizarRangos(cantBits);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }

    private void actualizarRangos(CharSequence s){
        if(s.length() > 0 && Integer.parseInt(s.toString()) >= 2){
            rangoSM.setText(calculadorRangos.getRangoSM(baseSeleccionada.getBase(),Integer.parseInt(s.toString())));
            rangoRC.setText(calculadorRangos.getRangoRC(baseSeleccionada.getBase(),Integer.parseInt(s.toString())));
            rangoDRC.setText(calculadorRangos.getRangoDRC(baseSeleccionada.getBase(),Integer.parseInt(s.toString())));
        }
        else{
            rangoDRC.setText("");
            rangoRC.setText("");
            rangoSM.setText("");
        }
    }

}
