package com.example.proyectoandroidtdp.CambioDeBase;

import android.text.InputFilter;
import android.widget.EditText;

import com.example.proyectoandroidtdp.Filtros.CreadorDeFiltros;
import com.example.proyectoandroidtdp.Filtros.CreadorDeFiltrosAbstracto;

public abstract class BaseSeleccionada {

    protected CreadorDeFiltrosAbstracto creadorDeFiltros;
    protected ConvertidorAbstracto convertidor;

    public BaseSeleccionada (){
        creadorDeFiltros = new CreadorDeFiltros();
        convertidor = new Convertidor();
    }

    public abstract InputFilter getFiltro();
    public abstract void handle(EditText editText);
    public abstract int getBase();

    public abstract void setResultados(CambioDeBaseFragment fragment);
    public abstract void setLabels(CambioDeBaseFragment fragment);
}
