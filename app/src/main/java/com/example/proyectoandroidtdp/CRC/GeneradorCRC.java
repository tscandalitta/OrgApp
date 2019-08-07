package com.example.proyectoandroidtdp.CRC;

import com.example.proyectoandroidtdp.Conversor.Conversor;
import com.example.proyectoandroidtdp.Conversor.ConversorAbstracto;

import java.security.InvalidParameterException;

public class GeneradorCRC implements GeneradorCRCAbstracto {

    private ConversorAbstracto conversor;

    public GeneradorCRC(){
        conversor = new Conversor();
    }

    /**
     * Verifica que el primer y ultimo caracter del polinomio generador sean '1'
     * @param generador polinomio generador
     * @return true si la estructura del polinomio es correcta, false en caso contrario
     */
    private boolean generadorValido(String generador){
        int ultimaPosicion = generador.length()-1;
        boolean esValido = false;
        if(generador.length() > 1)
            esValido = (generador.charAt(0) == '1' && generador.charAt(ultimaPosicion) == '1');
        return esValido;
    }

    public String generarCRC(String msj, String gen) throws InvalidParameterException{
        if(msj.length() < gen.length())
            throw new InvalidParameterException("El mensaje debe ser más largo");
        if(!generadorValido(gen))
           throw new InvalidParameterException("El polinomio generador es inválido");

        int bitsMensaje, bitsGenerador, bitsTotales, gradoGenerador;

        bitsMensaje = msj.length();
        bitsGenerador = gen.length();
        gradoGenerador = bitsGenerador - 1;
        bitsTotales = bitsMensaje + gradoGenerador;

        int[] mensaje = conversor.stringToArray(msj);
        int[] generador = conversor.stringToArray(gen);
        int[] resto = new int[bitsTotales];

        System.arraycopy(mensaje,0,resto,0,mensaje.length);

        computarCRC(generador, resto);

        String  mensajeFinal = conversor.arrayToString(resto);
        return mensajeFinal.substring(bitsTotales - gradoGenerador);
    }

    private void computarCRC(int[] generador, int[] mensaje){
        int puntero = 0;
        boolean cortar = false;
        while(!cortar){
            for(int i = 0; i < generador.length; i++)
                mensaje[puntero+i] = (mensaje[puntero+i] ^ generador[i]);

            while((mensaje[puntero] == 0) && (puntero != (mensaje.length - 1)))
                puntero++;

            if((mensaje.length - puntero) < generador.length)
                cortar = true;
        }
    }

    /**
     * Verifica si el mensaje recibido llegó sin errores.
     * @param msg Mensaje reicibido
     * @param gen Polinomio generador
     * @return true si el mensaje llegó sin errores, false en caso contrario
     */
    public boolean verificarCRC(String msg, String gen) throws InvalidParameterException{
        String resto = generarCRC(msg,gen);
        return restoNulo(resto);
    }

    private boolean restoNulo(String resto){
        boolean esNulo = true;
        for(int i = 0; i < resto.length() && esNulo; i++)
            esNulo = resto.charAt(i) == '0';
        return esNulo;
    }
}
