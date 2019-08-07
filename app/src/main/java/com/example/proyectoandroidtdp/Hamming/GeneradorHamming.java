package com.example.proyectoandroidtdp.Hamming;

import com.example.proyectoandroidtdp.Conversor.Conversor;
import com.example.proyectoandroidtdp.Conversor.ConversorAbstracto;

public class GeneradorHamming implements GeneradorHammingAbstracto {

    private ConversorAbstracto conversor;

    public GeneradorHamming(){
        conversor = new Conversor();
    }

    public String getHamming3(String msj){
        return conversor.arrayToString(calcularHamming3(msj));
    }

    public String getHamming4(String msj){
        return conversor.arrayToString(calcularHamming4(msj));
    }

    private int[] calcularHamming3(String mensaje){
        int bitsCodigo = 0;
        int bitsMensaje = mensaje.length();
        //calcular los bits de paridad necesarios usando m+r+1 <= 2^r
        boolean cortar = false;
        while(!cortar)
            if((bitsMensaje + bitsCodigo + 1) <= Math.pow(2, bitsCodigo))
                cortar = true;
            else
                bitsCodigo++;

        int longitudMensajeTransmitir = bitsMensaje + bitsCodigo;
        int potencia = 0, j = 0, posicion;
        int[] mensajeAtransmitir = new int[longitudMensajeTransmitir + 1] ; //+1 porque arranca en 1
        for(int i = 1; i <= longitudMensajeTransmitir; i++){
            posicion = (int) Math.pow(2,potencia);
            if(i % posicion != 0){          //Si la posicion i no es potencia de 2, meto los bits de datos.
                mensajeAtransmitir[i] = Integer.parseInt(Character.toString(mensaje.charAt(j)));
                j++;
            }
            else
                potencia++;
        }

        for(int i = 0; i < bitsCodigo; i++){
            int posicionBitCodigo = (int) Math.pow(2,i);
            int bigStep = posicionBitCodigo * 2;
            int comienzo = posicionBitCodigo;
            int checkPos = comienzo;

            boolean fin = false;
            while(!fin) {
                for (int k = comienzo; k <= comienzo + posicionBitCodigo - 1; k++){
                    checkPos = k;
                    if (k > longitudMensajeTransmitir)
                        break;
                    mensajeAtransmitir[posicionBitCodigo] ^= mensajeAtransmitir[checkPos];
                }
                if (checkPos > longitudMensajeTransmitir)
                    fin = true;
                else
                    comienzo += bigStep;
            }
        }
        int[] toReturn = new int[mensajeAtransmitir.length-1];
        System.arraycopy(mensajeAtransmitir, 1, toReturn, 0, toReturn.length);
        return toReturn;
    }

    private int[] calcularHamming4(String mensaje){
        int[] hamming3;
        int[] hamming4 = new int[0];
        if(mensaje.length() > 0) {
            hamming3 = calcularHamming3(mensaje);
            hamming4 = new int[hamming3.length + 1];
            int bitParidad = 0;
            for (int i = 0; i < hamming3.length; i++) {
                hamming4[i] = hamming3[i];
                bitParidad = bitParidad ^ hamming3[i];
            }
            hamming4[hamming3.length] = bitParidad;
        }
        return hamming4;
    }
}
