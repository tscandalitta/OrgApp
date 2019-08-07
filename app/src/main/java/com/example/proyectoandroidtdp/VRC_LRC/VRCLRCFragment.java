package com.example.proyectoandroidtdp.VRC_LRC;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.proyectoandroidtdp.Filtros.CreadorDeFiltros;
import com.example.proyectoandroidtdp.Filtros.CreadorDeFiltrosAbstracto;
import com.example.proyectoandroidtdp.R;


public class VRCLRCFragment extends Fragment {

    private Button boton;
    private Switch toggle;
    private EditText numero;
    private TextView textoBitParidad;
    private VerificadorParidad verificadorParidad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vrc_lrc,container, false);

        numero = view.findViewById(R.id.numeroParidad);
        toggle = view.findViewById(R.id.switch1);
        boton = view.findViewById(R.id.button);
        textoBitParidad = view.findViewById(R.id.textView3);
        verificadorParidad = new VerificadorParidad();

        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textoBitParidad.setText("");

                if(toggle.getText().toString().equals("Impar"))
                    toggle.setText(R.string.par);
                else
                    toggle.setText(R.string.impar);
            }
        });

        CreadorDeFiltrosAbstracto creador = new CreadorDeFiltros();
        InputFilter [] arr = {creador.getFiltroBinarioEntero()};
        numero.setFilters(arr);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(toggle.isChecked())
                    if (verificadorParidad.paridadPar(numero.getText() + ""))
                        textoBitParidad.setText("0");
                    else
                        textoBitParidad.setText("1");
                else
                    if (verificadorParidad.paridadImpar(numero.getText() + ""))
                        textoBitParidad.setText("0");
                    else
                        textoBitParidad.setText("1");
            }
        });
        return view;
    }
}
