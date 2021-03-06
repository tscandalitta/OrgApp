package com.example.proyectoandroidtdp.SistemasDeRepresentacion;

import java.security.InvalidParameterException;

@SuppressWarnings("StringConcatenationInLoop")
public class Complementador implements ComplementadorAbstracto{

    public Complementador(){
    }



    public String convertirRCtoDRC(String numero, int base){
        String toReturn = "";
        if(numero.length() > 1) {
            if (numero.charAt(0) == (char) (base - 1 + '0') || numero.charAt(0) == 'F') {
                //PASO A SIGNO MAGNITUD
                String signoMagnitud = toRC(numero, base);
                //PASO A COMPLEMENTO A BASE DISMINUIDA
                toReturn = toDRC(signoMagnitud, base);
            } else
                toReturn = numero;
        }
        return toReturn;
    }

    /**
     * Verifica si un numero es de la forma "(base-1)0000000"
     * @param numero numero a chequear
     * @return true si es el menor negativo en rc, false en caso contrario
     */
    private boolean menorNegativoRC(String numero){
        boolean menor = true;
        for(int i=1; i<numero.length() && menor; i++){
            if(numero.charAt(i) != '0')
                menor = false;
        }
        return menor;
    }


    public String convertirDRCtoRC(String numero, int base){
        String toReturn = "";
        if(numero.length() > 1){
            if(numero.charAt(0) == (char)(base-1 + '0') || numero.charAt(0) == 'F'){
                //PASO A SIGNO MAGNITUD
                String signoMagnitud = toDRC(numero,base);
                //PASO A COMPLEMENTO A BASE
                toReturn = toRC(signoMagnitud,base);
            }
            else
                toReturn = numero;
        }
        return toReturn;
    }


    public String toDRC(String numero, int base){
        String nuevoNum = "";
        if(numero.length() > 1) {
            // SI ES NEGATIVO COMPLEMENTO
            if (numero.charAt(0) == (char) (base-1 + '0') || numero.charAt(0) == 'F')
                nuevoNum = complementar(numero, base);
            else
                nuevoNum = numero;
        }
        return nuevoNum;
    }


    public String toRC(String numero, int base)throws InvalidParameterException{
        if(numero.length() > 1){
            if(numero.charAt(0) == '0')
                return numero;
            if(menorNegativoRC(numero))
                return numero;

            String numeroComplementado = complementar(numero,base);
            String numeroComplementoBase="";

            boolean yaSume=false;

            for(int i = numero.length()-1; i >= 0; i--){
                int digito = Integer.parseInt(numeroComplementado.charAt(i)+"",base);
                if(!yaSume){
                    if(digito == base-1)
                        numeroComplementoBase = 0 + numeroComplementoBase;
                    else{
                        numeroComplementoBase =  Integer.toString(digito+1,base) + numeroComplementoBase;
                        yaSume = true;
                    }
                }
                else
                    numeroComplementoBase =  Integer.toString(digito,base) + numeroComplementoBase;
            }
            if(base > 10)
                numeroComplementoBase = numeroComplementoBase.toUpperCase();
            return numeroComplementoBase;
        }
        else
            return "";
    }

    /**
     * Realiza el proceso de complementacion de un numero dado.
     * @param numero numero que se desea complementar.
     * @param base base del numero ingresado
     * @return devuelve numero complementado.
     */
    private String complementar(String numero, int base){

        if(numero.charAt(0) == 'F')
            numero = numero.replaceFirst("F","0");
        else
            if(Integer.parseInt("" + numero.charAt(0),base) == base - 1)
                numero = numero.replaceFirst("" + (base-1),"0");

        String numeroComplementado ="";

        for(int i = 0; i < numero.length(); i++){
            switch (numero.charAt(i)){
                case 'A':
                    numeroComplementado += ((base-1)-10);
                    break;
                case 'B':
                    numeroComplementado += ((base-1)-11);
                    break;
                case 'C':
                    numeroComplementado += ((base-1)-12);
                    break;
                case 'D':
                    numeroComplementado += ((base-1)-13);
                    break;
                case 'E':
                    numeroComplementado += ((base-1)-14);
                    break;
                case 'F':
                    numeroComplementado += ((base-1)-15);
                    break;
                default:
                    switch ((base-1) - Integer.parseInt("" + numero.charAt(i))){
                        case 15:
                            numeroComplementado += "F";
                            break;
                        case 14:
                            numeroComplementado += "E";
                            break;
                        case 13:
                            numeroComplementado += "D";
                            break;
                        case 12:
                            numeroComplementado += "C";
                            break;
                        case 11:
                            numeroComplementado += "B";
                            break;
                        case 10:
                            numeroComplementado += "A";
                            break;
                        default:
                            numeroComplementado += (base-1) - Integer.parseInt("" + numero.charAt(i));
                    }
                    break;
            }
        }
        return numeroComplementado;
    }
}
