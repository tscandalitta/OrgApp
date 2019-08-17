package com.example.proyectoandroidtdp.Hamming;

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

import com.example.proyectoandroidtdp.Filtros.CreadorDeFiltros;
import com.example.proyectoandroidtdp.Filtros.CreadorDeFiltrosAbstracto;
import com.example.proyectoandroidtdp.R;

import java.security.InvalidParameterException;

public class VerificarHammingFragment extends Fragment {


    private static final int HAMMING3 = 0;
    private static final int HAMMING4 = 1;
    GeneradorHammingAbstracto generador;
    EditText txtPaquete;
    Button btnVerificar;
    String paqueteRecibido;
    Spinner spinner;
    TextView txtPolitica1, txtPolitica2, txtConclusion1, txtConclusion2;
    int hammingSeleccionado = HAMMING3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_verificar_hamming, container, false);

        generador = new GeneradorHamming();
        txtPaquete = view.findViewById(R.id.txtPaqueteHamming);
        btnVerificar = view.findViewById(R.id.btnVerificarHamming);
        spinner = view.findViewById(R.id.spinnerHamming);
        txtPolitica1 = view.findViewById(R.id.txtHammingPolitica1);
        txtPolitica2 = view.findViewById(R.id.txtHammingPolitica2);
        txtConclusion1 = view.findViewById(R.id.txtHammingResult1);
        txtConclusion2 = view.findViewById(R.id.txtHammingResult2);

        CreadorDeFiltrosAbstracto creadorDeFiltros = new CreadorDeFiltros();
        InputFilter[] filtros = {creadorDeFiltros.getFiltroBinarioEntero(), new InputFilter.LengthFilter(21)};
        txtPaquete.setFilters(filtros);

        String [] hammings = getResources().getStringArray(R.array.arraySpinnerHamming);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(),
                R.layout.item_spinner, R.id.texto_spinner,hammings);
        arrayAdapter.setDropDownViewResource(R.layout.item_spinner);

        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                TextView selectedText = parent.getChildAt(0).findViewById(R.id.texto_spinner);
                if (selectedText != null)
                    selectedText.setTextColor(txtPaquete.getCurrentTextColor());

                switch(position){
                    case 0: //Hamming 3
                        txtPolitica1.setText("D = 1, C = 1");
                        txtPolitica2.setText("D = 2, C = 0");
                        hammingSeleccionado = HAMMING3;
                        break;
                    case 1: //Hamming 4
                        txtPolitica1.setText("D = 2, C = 1");
                        txtPolitica2.setText("D = 3, C = 0");
                        hammingSeleccionado = HAMMING4;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paqueteRecibido = txtPaquete.getText().toString();
                if(paqueteRecibido.length() > 1) {
                    Toast toast;
                    try {
                        if (hammingSeleccionado == HAMMING3) {
                            txtConclusion1.setText(generador.verificarHamming3(paqueteRecibido, 0));
                            txtConclusion2.setText(generador.verificarHamming3(paqueteRecibido, 1));
                        } else {
                            txtConclusion1.setText(generador.verificarHamming4(paqueteRecibido, 1));
                            txtConclusion2.setText(generador.verificarHamming4(paqueteRecibido, 0));
                        }
                    } catch (InvalidParameterException e) {
                        toast = Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
                        toast.show();
                        txtConclusion1.setText("");
                        txtConclusion2.setText("");
                    }
                }
            }
        });

        return view;
    }
}
