package com.example.proyectoandroidtdp.CambioDeBase;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoandroidtdp.Filtros.CreadorDeFiltros;
import com.example.proyectoandroidtdp.Filtros.CreadorDeFiltrosAbstracto;
import com.example.proyectoandroidtdp.R;


public class CambioDeBaseFragment extends Fragment {

    private EditText editTextNumero,txtNroConvertido3,txtNroConvertido2,txtNroConvertido1;
    private TextView labelBase1, labelBase2, labelBase3;

    private String baseSeleccionada;

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

        txtNroConvertido1.setTextIsSelectable(false);

        labelBase1 = view.findViewById(R.id.txtBase1);
        labelBase2 = view.findViewById(R.id.txtBase2);
        labelBase3 = view.findViewById(R.id.txtBase3);


        //BUSCO EL SPINNER Y LO CREO.
        Spinner spinnerDeBases = view.findViewById(R.id.spinner);
        String [] bases = getResources().getStringArray(R.array.arraySpinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(),
                android.R.layout.simple_list_item_1,bases);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinnerDeBases.setAdapter(arrayAdapter);


        spinnerDeBases.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                //limpio editText
                editTextNumero.setText("");

                InputFilter inputFilter, maxLength;
                CreadorDeFiltrosAbstracto creador = new CreadorDeFiltros();

                int tipoDeEntrada = (InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED |
                        InputType.TYPE_NUMBER_FLAG_DECIMAL);

                switch(position) {
                    //Binario
                    case 0:
                        labelBase1.setText(getString(R.string.oct));
                        labelBase2.setText(getString(R.string.dec));
                        labelBase3.setText(getString(R.string.hex));

                        baseSeleccionada = "BIN";

                        inputFilter = creador.getFiltroBinarioFraccionario();
                        maxLength = new InputFilter.LengthFilter(20);
                        break;
                    //Octal
                    case 1:
                        labelBase1.setText(getString(R.string.bin));
                        labelBase2.setText(getString(R.string.dec));
                        labelBase3.setText(getString(R.string.hex));

                        baseSeleccionada = "OCT";

                        inputFilter = creador.getFiltroOctalFraccionario();
                        maxLength = new InputFilter.LengthFilter(10);
                        break;
                    //Decimal
                    case 2:
                        labelBase1.setText(getString(R.string.bin));
                        labelBase2.setText(getString(R.string.oct));
                        labelBase3.setText(getString(R.string.hex));

                        baseSeleccionada = "DEC";

                        inputFilter = creador.getFiltroDecimalFraccionario();
                        maxLength = new InputFilter.LengthFilter(10);
                        break;
                    //Hexadecimal
                    default:
                        labelBase1.setText(getString(R.string.bin));
                        labelBase2.setText(getString(R.string.oct));
                        labelBase3.setText(getString(R.string.dec));

                        baseSeleccionada = "HEX";

                        tipoDeEntrada = (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD |
                                InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS  );
                        inputFilter = creador.getFiltroHexaFraccionario();
                        maxLength = new InputFilter.LengthFilter(7);
                }

                InputFilter[] filtersArray = {inputFilter, maxLength};
                editTextNumero.setFilters(filtersArray);
                editTextNumero.setInputType(tipoDeEntrada);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        editTextNumero.addTextChangedListener(new TextWatcher() {

            ConvertidorAbstracto convertidor = new Convertidor();

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()!=0&&s.charAt(0)!='.')
                    switch(baseSeleccionada) {
                        case "BIN":
                            String numBinario = editTextNumero.getText().toString();

                            String numDecimal = convertidor.toDecimal(numBinario, 2);
                            String numOctal = convertidor.fromDecimal(numDecimal, 8);
                            String numHexa = convertidor.fromDecimal(numDecimal, 16);

                            txtNroConvertido1.setText(numOctal);
                            txtNroConvertido2.setText(numDecimal);
                            txtNroConvertido3.setText(numHexa);
                            break;
                        case "OCT":
                            String numOctal1 = editTextNumero.getText().toString();

                            String numDecimal1 = convertidor.toDecimal(numOctal1, 8);
                            String numBinario1 = convertidor.fromDecimal(numDecimal1, 2);
                            String numHexa1 = convertidor.fromDecimal(numDecimal1, 16);

                            txtNroConvertido1.setText(numBinario1);
                            txtNroConvertido2.setText(numDecimal1);
                            txtNroConvertido3.setText(numHexa1);
                            break;
                        case "DEC":
                            String numDecimal2 = editTextNumero.getText().toString();

                            String numBinario2 = convertidor.fromDecimal(numDecimal2,2);
                            String numOctal2 = convertidor.fromDecimal(numDecimal2,8);
                            String numHexa2 = convertidor.fromDecimal(numDecimal2,16);

                            txtNroConvertido1.setText(numBinario2);
                            txtNroConvertido2.setText(numOctal2);
                            txtNroConvertido3.setText(numHexa2);
                            break;
                        case "HEX":
                            String numHexa3 = editTextNumero.getText().toString();

                            //Cuento la cantidad de puntos del string
                            if(cantPuntos(numHexa3) > 1)
                                //Error, no se convierte
                                Toast.makeText(getActivity(),"El numero contiene mas de 1 punto decimal. " +
                                        "Por favor, ingreselo correctamente",Toast.LENGTH_SHORT).show();
                            else{
                                String numDecimal3 = convertidor.toDecimal(numHexa3,16);
                                String numOctal3 = convertidor.fromDecimal(numDecimal3,8);
                                String numBinario3 = convertidor.fromDecimal(numDecimal3,2);
                                txtNroConvertido1.setText(numBinario3);
                                txtNroConvertido2.setText(numOctal3);
                                txtNroConvertido3.setText(numDecimal3);
                            }
                    }
                else{
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

    private int cantPuntos(String numero){
        int cantPuntos=0;
        for(int i = 0; i < numero.length(); i++)
            if(numero.charAt(i) == '.')
                cantPuntos++;
        return cantPuntos;
    }






}