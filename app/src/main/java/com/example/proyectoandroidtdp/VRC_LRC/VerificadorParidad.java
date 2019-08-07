package com.example.proyectoandroidtdp.VRC_LRC;

public class VerificadorParidad {

    public VerificadorParidad(){
    }

    /**
     * Calcula paridad par de una cadena.
     * @param cadena cadena a verificar.
     * @return el valor de paridad.
     */
    public boolean paridadPar(String cadena){
        int cantidadUnos=0;
        for(int i =0;i<cadena.length();i++){
            if(cadena.charAt(i)=='1')
                cantidadUnos++;
        }
        return cantidadUnos%2 != 0;
    }

    /**
     * Calcula la paridad impar de una cadena.
     * @param cadena a verificar.
     * @return el valor de paridad.
     */
    public boolean paridadImpar(String cadena){
        return !paridadPar(cadena);
    }
}
