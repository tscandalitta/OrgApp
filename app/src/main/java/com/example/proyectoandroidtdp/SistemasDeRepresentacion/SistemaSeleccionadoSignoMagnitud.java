package com.example.proyectoandroidtdp.SistemasDeRepresentacion;

import java.security.InvalidParameterException;

public class SistemaSeleccionadoSignoMagnitud extends SistemaSeleccionado {


    public SistemaSeleccionadoSignoMagnitud(SistemasDeRepresentacionFragment fragment){
        super(fragment);
    }

    public void setearTextos( String num, int baseSeleccionada)throws InvalidParameterException {

        String numDRC = this.complementador.toDRC(num,baseSeleccionada);
        String numRC = this.complementador.toRC(num,baseSeleccionada);

        this.fragment.setearTextoEnPrimerEditText(numDRC);
        this.fragment.setearTextoEnSegundoEditText(numRC);
    }

    public void setearLabels() {
        this.fragment.setearTextoPrimerTextField("DRC");
        this.fragment.setearTextoSegundoTextField("RC");
    }
}
