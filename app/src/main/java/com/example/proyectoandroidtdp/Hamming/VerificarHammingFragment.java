package com.example.proyectoandroidtdp.Hamming;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.proyectoandroidtdp.R;

public class VerificarHammingFragment extends Fragment {

    GeneradorHammingAbstracto generador;
    EditText txtPaquete;
    Button btnVerificar;
    String paqueteRecibido;
    Spinner spinner;
    TextView txtPolitica1, txtPolitica2, txtConclusion1, txtConclusion2;
    int politica = 0;

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

        String [] hammings = getResources().getStringArray(R.array.arraySpinnerHamming);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(),
                R.layout.item_spinner, R.id.texto_spinner,hammings);
        arrayAdapter.setDropDownViewResource(R.layout.item_spinner);

        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                TextView selectedText = parent.getChildAt(0).findViewById(R.id.texto_spinner);
                if (selectedText != null) {
                    selectedText.setTextColor(txtPaquete.getCurrentTextColor());
                }

                switch(position){
                    case 0: //Hamming 3
                        txtPolitica1.setText("D = 1, C = 1");
                        txtPolitica2.setText("D = 2, C = 0");
                        politica = 0;
                        break;
                    case 1: //Hamming 4
                        txtPolitica1.setText("D = 2, C = 1");
                        txtPolitica2.setText("D = 3, C = 0");
                        politica = 1;
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
                if(politica == 0) {//Se selecciono hamming 3
                    txtConclusion1.setText(generador.verificarHamming3(paqueteRecibido, 0));
                    txtConclusion2.setText(generador.verificarHamming3(paqueteRecibido, 1));
                }
                else {//Si selecciono hamming 4
                    txtConclusion1.setText(generador.verificarHamming4(paqueteRecibido, 0));
                    txtConclusion2.setText(generador.verificarHamming4(paqueteRecibido, 1));
                }
            }
        });

        return view;
    }
}
