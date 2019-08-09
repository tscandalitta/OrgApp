package com.example.proyectoandroidtdp.SistemasDeRepresentacion;

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
import com.example.proyectoandroidtdp.R;

public class SistemasDeRepresentacionFragment extends Fragment {

    private EditText editTextNumero;
    private TextView labelRepresentacion1,labelRepresentacion2, txtRepresentacion1,txtRepresentacion2;
    private SistemaSeleccionado sistemaSeleccionado;
    private BaseSeleccionada baseSeleccionada;
    private SistemasDeRepresentacionFragment este;
    private int colorTexto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null)
            return null;

        View view = inflater.inflate(R.layout.fragment_sistemas_de_representacion,container, false);

        //BUSCO EL EDIT TEXT
        editTextNumero = view.findViewById(R.id.txtNumeroIngresadoSR);

        txtRepresentacion1 = view.findViewById(R.id.txtRepresentacion1);
        txtRepresentacion2 = view.findViewById(R.id.txtRepresentacion2);

        labelRepresentacion1 = view.findViewById(R.id.labelRepresentacion1);
        labelRepresentacion2 = view.findViewById(R.id.labelRepresentacion2);

        este = this;
        colorTexto = txtRepresentacion1.getCurrentTextColor();

        //BUSCO EL SPINNER Y LO CREO.
        Spinner spinnerDeBases = view.findViewById(R.id.spinnerSRBases);
        String [] bases = getResources().getStringArray(R.array.arraySpinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(),
                R.layout.item_spinner, R.id.texto_spinner,bases);
        arrayAdapter.setDropDownViewResource(R.layout.item_spinner);

        //BUSCO EL SPINNER Y LO CREO.
        Spinner spinnerDeRepresentaciones = view.findViewById(R.id.spinnerSRSistemas);
        String [] basesSR = getResources().getStringArray(R.array.arraySpinnerSistemasDeRepresentacion);
        ArrayAdapter<String> arrayAdapterSR = new ArrayAdapter<>(getActivity().getApplicationContext(),
                R.layout.item_spinner, R.id.texto_spinner,basesSR);
        arrayAdapter.setDropDownViewResource(R.layout.item_spinner);

        spinnerDeBases.setAdapter(arrayAdapter);

        spinnerDeBases.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                TextView selectedText = (TextView) adapterView.getChildAt(0).findViewById(R.id.texto_spinner);
                if (selectedText != null) {
                    selectedText.setTextColor(colorTexto);
                }
                //limpio editText
                editTextNumero.setText("");
                InputFilter inputFilter;

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

                baseSeleccionada.handle(editTextNumero);

                editTextNumero.setFilters(filtersArray);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinnerDeRepresentaciones.setAdapter(arrayAdapterSR);

        spinnerDeRepresentaciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                TextView selectedText = (TextView) adapterView.getChildAt(0).findViewById(R.id.texto_spinner);
                if (selectedText != null) {
                    selectedText.setTextColor(colorTexto);
                }

                //limpio editText
                editTextNumero.setText("");

                switch(position) {
                    //SM
                    case 0:
                        sistemaSeleccionado = new SistemaSeleccionadoSignoMagnitud(este);
                        break;
                    //DRC
                    case 1:
                        sistemaSeleccionado = new SistemaSeleccionadoComplementoBaseDisminuida(este);

                        break;
                    default:
                        sistemaSeleccionado = new SistemaSeleccionadoComplementoBase(este);
                        break;
                }
                sistemaSeleccionado.setearLabels();
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
                if (s.length()!=0){
                    String texto = s.toString();
                    if(texto.charAt(0)=='0'||texto.charAt(0)=='F'||texto.charAt(0)==((char) (baseSeleccionada.getBase()-1)+'0')){
                        String num = editTextNumero.getText().toString();
                        sistemaSeleccionado.setearTextos(num,baseSeleccionada.getBase());
                    }
                }
                else{
                    // SI NO HAY NADA, VACIO LOS TEXTFIELD
                    txtRepresentacion1.setText("");
                    txtRepresentacion2.setText("");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        return view;
    }

    public void setearTextoEnPrimerEditText(String texto){
        txtRepresentacion1.setText(texto);
    }

    public void setearTextoEnSegundoEditText(String texto){
        txtRepresentacion2.setText(texto);
    }

    public void setearTextoPrimerTextField(String texto){
        labelRepresentacion1.setText(texto);
    }

    public void setearTextoSegundoTextField(String texto){
        labelRepresentacion2.setText(texto);
    }
}
