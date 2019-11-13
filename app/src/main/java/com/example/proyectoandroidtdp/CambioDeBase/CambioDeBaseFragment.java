package com.example.proyectoandroidtdp.CambioDeBase;

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

import com.example.proyectoandroidtdp.R;


public class CambioDeBaseFragment extends Fragment {

    private TextView txtNroConvertido3,txtNroConvertido2,txtNroConvertido1;
    private EditText editTextNumero;
    private TextView labelBase1, labelBase2, labelBase3;
    int colorTexto;
    private BaseSeleccionada base;
    Spinner spinnerDeBases;
    private CambioDeBaseFragment cambioDeBaseFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (container == null)
            return null;

        View view = inflater.inflate(R.layout.fragment_cambio_de_base,container, false);

        //BUSCO EL EDIT TEXT
        editTextNumero = view.findViewById(R.id.txtNumeroIngresado);
        txtNroConvertido3 = view.findViewById(R.id.txtNroConvertido3);
        txtNroConvertido1 = view.findViewById(R.id.txtNroConvertido1);
        txtNroConvertido2 = view.findViewById(R.id.txtNroConvertido2);
        labelBase1 = view.findViewById(R.id.txtBase1);
        labelBase2 = view.findViewById(R.id.txtBase2);
        labelBase3 = view.findViewById(R.id.txtBase3);

        colorTexto = txtNroConvertido1.getCurrentTextColor();

        cambioDeBaseFragment = this;

        txtNroConvertido1.setTextIsSelectable(false);

        //BUSCO EL SPINNER Y LO CREO.
        spinnerDeBases = view.findViewById(R.id.spinner);
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

                //limpio editText
                editTextNumero.setText("");

                InputFilter inputFilter, maxLength;

                switch(position) {
                    //Binario
                    case 0:

                        base = new BaseSeleccionadaBinario();
                        base.setLabels(cambioDeBaseFragment);
                        inputFilter = base.getFiltroFraccionario();
                        maxLength = new InputFilter.LengthFilter(20);
                        break;
                    //Octal
                    case 1:

                        base = new BaseSeleccionadaOctal();
                        base.setLabels(cambioDeBaseFragment);
                        inputFilter = base.getFiltroFraccionario();
                        maxLength = new InputFilter.LengthFilter(10);
                        break;
                    //Decimal
                    case 2:

                        base = new BaseSeleccionadaDecimal();
                        base.setLabels(cambioDeBaseFragment);
                        inputFilter = base.getFiltroFraccionario();
                        maxLength = new InputFilter.LengthFilter(10);
                        break;
                    //Hexadecimal
                    default:

                        base = new BaseSeleccionadaHexadecimal();
                        base.setLabels(cambioDeBaseFragment);
                        inputFilter = base.getFiltroFraccionario();
                        maxLength = new InputFilter.LengthFilter(7);
                }


                base.handle(editTextNumero);
                InputFilter[] filtersArray = {inputFilter,maxLength};
                editTextNumero.setFilters(filtersArray);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        editTextNumero.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0 && s.charAt(0) != '.'){
                    base.setResultados(cambioDeBaseFragment);
                }
                else {
                    txtNroConvertido1.setText("");
                    txtNroConvertido2.setText("");
                    txtNroConvertido3.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        return view;
    }


    public TextView getPrimerTextView(){
        return txtNroConvertido1;
    }

    public TextView getSegundoTextView(){
        return txtNroConvertido2;
    }

    public TextView getTercerTextView(){
        return txtNroConvertido3;
    }

    public TextView getTextViewPrincipal(){
        return editTextNumero;
    }

    public TextView getLabelBase1(){
        return labelBase1;
    }

    public TextView getLabelBase2(){
        return labelBase2;
    }

    public TextView getLabelBase3(){
        return labelBase3;
    }






}