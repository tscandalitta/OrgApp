package com.example.proyectoandroidtdp.SistemasDeRepresentacion;

import java.security.InvalidParameterException;

public abstract class SistemaSeleccionado {

    protected ComplementadorAbstracto complementador;
    protected SistemasDeRepresentacionFragment fragment;

    public SistemaSeleccionado(SistemasDeRepresentacionFragment fragment){
        complementador = new Complementador();
        this.fragment = fragment;
    }

    public abstract void setearTextos(String num,int base) throws InvalidParameterException;
    public abstract void setearLabels();
}
