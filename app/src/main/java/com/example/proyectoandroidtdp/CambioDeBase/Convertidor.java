package com.example.proyectoandroidtdp.CambioDeBase;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Convertidor implements ConvertidorAbstracto{

    public Convertidor(){
    }

    /**
     * Transforma un numero en cualquier base a un numero decimal.
     * @param numero numero a convertir.
     * @param baseOrigen base del numero ingresado.
     * @return numero en decimal.
     */

    public String toDecimal(String numero, int baseOrigen) {

        double resultadoDecimal = 0;

        String toReturn = "";

        String[] partes = numero.split("\\.");

        String parteFraccionaria;
        int digitoActual;

        String parteEnteraEnDecimal="";

        //Tiene parte entera
        if (partes.length >= 1) {
            String parteEntera = partes[0];
            if (!parteEntera.equals("")) {
                parteEnteraEnDecimal = Integer.parseInt(parteEntera, baseOrigen) + "";
            }
            //Tiene parte Decimal
            if (partes.length == 2) {

                parteFraccionaria = partes[1];

                int potencia = -1;
                for (int i = 0; i < parteFraccionaria.length(); i++) {
                    switch (parteFraccionaria.charAt(i)) {
                        case 'A':
                            digitoActual = 10;
                            resultadoDecimal += (digitoActual * Math.pow(baseOrigen, potencia));
                            break;
                        case 'B':
                            digitoActual = 11;
                            resultadoDecimal += (digitoActual * Math.pow(baseOrigen, potencia));
                            break;
                        case 'C':
                            digitoActual = 12;
                            resultadoDecimal += (digitoActual * Math.pow(baseOrigen, potencia));
                            break;
                        case 'D':
                            digitoActual = 13;
                            resultadoDecimal += (digitoActual * Math.pow(baseOrigen, potencia));
                            break;
                        case 'E':
                            digitoActual = 14;
                            resultadoDecimal += (digitoActual * Math.pow(baseOrigen, potencia));
                            break;
                        case 'F':
                            digitoActual = 15;
                            resultadoDecimal += (digitoActual * Math.pow(baseOrigen, potencia));
                            break;

                        default:
                            digitoActual = Integer.parseInt("" + parteFraccionaria.charAt(i));
                            resultadoDecimal += (digitoActual * Math.pow(baseOrigen, potencia));
                            break;
                    }
                    potencia--;
                }
            }
            if (!numero.equals(".") && !numero.equals("")) {
                String[] partesAux = ("" + resultadoDecimal).split("\\.");
                toReturn = parteEnteraEnDecimal + "." + partesAux[1];
            }
        }
        return toReturn;
    }


    /**
     * Transforma un numero decimal en un numero de otra base.
     * @param numero numero decimal a convertir
     * @param baseDestino base en la cual retornar el resultado.
     * @return numero convertido en la base seleccionada.
     */
    public String fromDecimal(String numero, int baseDestino) {

        Double num = Double.parseDouble(numero);
        int precision = 10;
        BigDecimal bd = new BigDecimal(num);
        BigDecimal mult = new BigDecimal(baseDestino).pow(precision);
        bd = bd.multiply(mult);
        BigInteger bi = bd.toBigInteger();
        StringBuilder str = new StringBuilder(bi.toString(baseDestino));
        while (str.length() <=precision) {
            str.insert(0, "0");
        }
        str.insert(str.length()-precision, ".");

        return sacarCeros(str.toString().toUpperCase());
    }


    /**
     * Elimina los 0s de más del numero dado.
     * @param num numero con 0s de más.
     * @return numero con los 0s correspondiendtes.
     */
    private  String sacarCeros(String num) {
        int i;
        for(i = num.length()-1; i >= 0; i--)
            if(num.charAt(i)!='0')
                break;

        if (num.charAt(i) == '.')
            return num.substring(0,i)+".0";

        return num.substring(0,i+1);
    }
}
