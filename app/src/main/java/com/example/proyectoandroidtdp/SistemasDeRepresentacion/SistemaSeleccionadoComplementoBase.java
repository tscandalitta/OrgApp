package com.example.proyectoandroidtdp.SistemasDeRepresentacion;

import java.security.InvalidParameterException;

public class SistemaSeleccionadoComplementoBase extends SistemaSeleccionado {

    public SistemaSeleccionadoComplementoBase(SistemasDeRepresentacionFragment fragment){
        super(fragment);
    }
    @Override
    public void setearTextos(String num, int base)throws InvalidParameterException {

        String numSM = complementador.toRC(num,base);
        String numDRC = complementador.convertirRCtoDRC(num,base);

        this.fragment.setearTextoEnPrimerEditText(numSM);
        this.fragment.setearTextoEnSegundoEditText(numDRC);
    }

    @Override
    public void setearLabels() {
        this.fragment.setearTextoPrimerTextField("SM");
        this.fragment.setearTextoSegundoTextField("DRC");
    }
}
