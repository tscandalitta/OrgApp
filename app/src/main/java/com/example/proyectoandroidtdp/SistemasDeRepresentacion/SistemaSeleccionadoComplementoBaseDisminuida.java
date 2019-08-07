package com.example.proyectoandroidtdp.SistemasDeRepresentacion;

import java.security.InvalidParameterException;

public class SistemaSeleccionadoComplementoBaseDisminuida extends SistemaSeleccionado {


    public SistemaSeleccionadoComplementoBaseDisminuida(SistemasDeRepresentacionFragment fragment){
        super(fragment);
    }
    @Override
    public void setearTextos(String num, int base)throws InvalidParameterException {
        String numSM = complementador.toDRC(num,base);
        String numRC = complementador.convertirDRCtoRC(num,base);

        this.fragment.setearTextoEnPrimerEditText(numSM);
        this.fragment.setearTextoEnSegundoEditText(numRC);
    }

    @Override
    public void setearLabels() {
        this.fragment.setearTextoPrimerTextField("SM");
        this.fragment.setearTextoSegundoTextField("RC");
    }
}
