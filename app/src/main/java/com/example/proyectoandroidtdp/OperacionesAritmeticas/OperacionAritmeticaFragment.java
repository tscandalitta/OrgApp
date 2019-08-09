package com.example.proyectoandroidtdp.OperacionesAritmeticas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoandroidtdp.CambioDeBase.BaseSeleccionada;
import com.example.proyectoandroidtdp.CambioDeBase.BaseSeleccionadaBinario;
import com.example.proyectoandroidtdp.CambioDeBase.BaseSeleccionadaDecimal;
import com.example.proyectoandroidtdp.CambioDeBase.BaseSeleccionadaHexadecimal;
import com.example.proyectoandroidtdp.CambioDeBase.BaseSeleccionadaOctal;
import com.example.proyectoandroidtdp.R;

import java.security.InvalidParameterException;


public class OperacionAritmeticaFragment extends Fragment {

    private EditText numeroX,numeroY;
    private TextView resultado;
    private BaseSeleccionada baseSeleccionada;
    private Button botonConvertir;
    private int colorTexto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_operaciones_aritmeticas, container, false);
        numeroX = view.findViewById(R.id.txtNroX);
        numeroY = view.findViewById(R.id.txtNroY);
        botonConvertir = view.findViewById(R.id.botonCalcular);
        resultado = view.findViewById(R.id.txtNroResultado);
        colorTexto = resultado.getCurrentTextColor();


        //BUSCO EL SPINNER Y LO CREO.
        final Spinner spinnerDeBases = view.findViewById(R.id.spinnerBaseOperacion);
        String [] bases = getResources().getStringArray(R.array.arraySpinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(),
                R.layout.item_spinner, R.id.texto_spinner,bases);
        arrayAdapter.setDropDownViewResource(R.layout.item_spinner);
        spinnerDeBases.setAdapter(arrayAdapter);

        spinnerDeBases.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                TextView selectedText = (TextView) parent.getChildAt(0).findViewById(R.id.texto_spinner);
                if (selectedText != null) {
                    selectedText.setTextColor(colorTexto);
                }

                InputFilter inputFilter;

                numeroX.setText("");
                numeroY.setText("");
                resultado.setText("");

                switch(position) {
                    //Binario
                    case 0:
                        baseSeleccionada = new BaseSeleccionadaBinario();
                        break;
                    //Octal
                    case 1:
                        baseSeleccionada= new BaseSeleccionadaOctal();
                        break;
                    //Decimal
                    case 2:
                        baseSeleccionada= new BaseSeleccionadaDecimal();
                        break;
                    //Hexa
                    default:
                        baseSeleccionada= new BaseSeleccionadaHexadecimal();

                }

                inputFilter = baseSeleccionada.getFiltro();
                InputFilter[] filtersArray = {inputFilter, new InputFilter.LengthFilter(16)};
                baseSeleccionada.handle(numeroX);
                baseSeleccionada.handle(numeroY);

                numeroX.setFilters(filtersArray);
                numeroY.setFilters(filtersArray);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //BUSCO EL SPINNER Y LO CREO.
        final Spinner spinnerDeSistemas = view.findViewById(R.id.spinnerSistemaOperacion);
        String [] sistemas = getResources().getStringArray(R.array.arraySpinnerSistemasDeRepresentacion);
        ArrayAdapter<String> arrayAdapterSR = new ArrayAdapter<>(getActivity().getApplicationContext(),
                R.layout.item_spinner, R.id.texto_spinner,sistemas);
        arrayAdapter.setDropDownViewResource(R.layout.item_spinner);
        spinnerDeSistemas.setAdapter(arrayAdapterSR);

        spinnerDeSistemas.setOnItemSelectedListener((new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                TextView selectedText = parent.getChildAt(0).findViewById(R.id.texto_spinner);
                if (selectedText != null) {
                    selectedText.setTextColor(colorTexto);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }));


        botonConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OperadorAritmeticoAbstracto operadorAritmetico = new OperadorAritmetico();

                try{
                   String sistemaSeleccionado = spinnerDeSistemas.getSelectedItem().toString();
                    String numX = numeroX.getText().toString();
                    String numY = numeroY.getText().toString();
                    String suma = "";
                    int base = baseSeleccionada.getBase();

                    switch(sistemaSeleccionado){
                        case "SM":
                            suma = operadorAritmetico.operarSM(numX,numY,base);
                            break;
                        case "DRC":
                            suma = operadorAritmetico.operarDRC(numX,numY,base);
                            break;
                        case "RC":
                            suma = operadorAritmetico.operarRC(numX,numY,base);
                            break;
                    }

                    resultado.setText(suma);


                }catch (OverflowException | InvalidParameterException e){
                    Toast toast1 =
                            Toast.makeText(getActivity().getApplicationContext(),
                                    e.getMessage(), Toast.LENGTH_LONG);

                    toast1.show();
                }
            }
        });

        return view;
    }



}
