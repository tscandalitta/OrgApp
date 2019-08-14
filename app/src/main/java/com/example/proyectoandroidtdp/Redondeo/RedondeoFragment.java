package com.example.proyectoandroidtdp.Redondeo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyectoandroidtdp.R;

public class RedondeoFragment extends Fragment {

    RedondeadorAbstracto redondeador;
    TextView txtNumero, txtPrecision, txtTruncado, txtAumentacion,txtBiased;
    Button btnRedondear;
    String numeroOriginal, numeroRedondeado, precision;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_redondeo, container, false);

        redondeador = new Redondeador();

        txtNumero = view.findViewById(R.id.txtNumeroARedondear);
        txtPrecision = view.findViewById(R.id.txtPrecision);
        txtTruncado = view.findViewById(R.id.txtRedondeoTruncado);
        txtAumentacion = view.findViewById(R.id.txtRedondeoAumentacion);
        txtBiased = view.findViewById(R.id.txtRedondeoBiased);

        btnRedondear = view.findViewById(R.id.btnRedondear);

        InputFilter[] filtroNumero = {new InputFilter.LengthFilter(20)};
        InputFilter[] filtroPrecision = {new InputFilter.LengthFilter(2)};
        txtNumero.setFilters(filtroNumero);
        txtPrecision.setFilters(filtroPrecision);

        btnRedondear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeroOriginal = txtNumero.getText().toString();
                precision = txtPrecision.getText().toString();
                if(numeroOriginal.length() > 0 && precision.length() > 0) {
                    numeroRedondeado = redondeador.redondeoTruncado(numeroOriginal, precision);
                    String numeroAumentacion = redondeador.redondeoAumentacion(numeroOriginal, precision);
                    String numeroBiased = redondeador.redondeoBiased(numeroOriginal,precision);

                    txtTruncado.setText(numeroRedondeado);
                    txtAumentacion.setText(numeroAumentacion);
                    txtBiased.setText(numeroBiased);

                }
            }
        });

        return view;
    }
}