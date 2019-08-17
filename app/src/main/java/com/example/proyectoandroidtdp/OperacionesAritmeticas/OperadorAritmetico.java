package com.example.proyectoandroidtdp.OperacionesAritmeticas;

import com.example.proyectoandroidtdp.SistemasDeRepresentacion.Complementador;

import java.security.InvalidParameterException;

@SuppressWarnings("StringConcatenationInLoop")
public class OperadorAritmetico implements  OperadorAritmeticoAbstracto {



    public String operarSM(String numX, String numY, int base) throws OverflowException, InvalidParameterException {

        if(numX.equals("") || numY.equals(""))
            throw new InvalidParameterException("Operando nulo, por favor complete ambos campos.");

        if(numX.length() != numY.length())
            throw new InvalidParameterException("Operandos de distinta longitud");

        if(!(numX.charAt(0)=='0' || numX.charAt(0)=='F' || numX.charAt(0) == ((char) (base-1)+'0')) ||
                !(numY.charAt(0) == '0' || numY.charAt(0) == 'F' || numY.charAt(0) == ((char) (base-1)+'0'))){
            throw new InvalidParameterException("Por favor ingrese un numero con signo valido"+" "+
                    "0 o "+Integer.toString(base-1,base).toUpperCase() );
        }

        if(numX.length() == 1 || numY.length() == 1)
            throw new InvalidParameterException("Ingrese un numero con longitud valida.");

        String numeroXsinSigno = numX.substring(1);
        String numeroYsinSigno = numY.substring(1);

        int x = Integer.parseInt(numeroXsinSigno,base);
        int y = Integer.parseInt(numeroYsinSigno,base);

        int xSignado = hacerPositivo(numX.charAt(0) + "",x,base);
        int ySignado = hacerPositivo(numY.charAt(0) + "",y,base);

        int resultado = xSignado + ySignado;

        String resultadoString = Integer.toString(resultado,base);

        String resultadoStringConSigno = (resultado < 0)?
                (resultadoString.replace('-',(char)(base-1 + '0'))) : ("0" + resultadoString);

        if(resultadoStringConSigno.length() > numX.length())
            throw new OverflowException();
        else
            if(resultadoStringConSigno.length() < numX.length()){
                int diferencia = numX.length() - resultadoStringConSigno.length();

                String signoConCeros = resultadoStringConSigno.charAt(0) + "";
                String magnitud = resultadoStringConSigno.substring(1);

                while(diferencia > 0){
                    signoConCeros += "0";
                    diferencia--;
                }
                resultadoStringConSigno = signoConCeros + magnitud;
            }
        return resultadoStringConSigno;
    }

    /**
     * Convierte el numero pasado por parametro a positivo.
     * @param signo signo del numero.
     * @param numero magnitud recibida.
     * @param base base en la que esta el numero.
     * @return el numero convertido a positivo.
     */
    private int hacerPositivo(String signo, int numero, int base) {
        String signoNegativo = Integer.toString(base-1);
        if (signo.equals(signoNegativo))
            return numero * -1;
        else
            return numero;
    }


    public String operarDRC(String numX, String numY, int base) throws OverflowException,InvalidParameterException{
        Complementador complementador = new Complementador();

        String x = complementador.toDRC(numX,base);
        String y = complementador.toDRC(numY,base);

        String resultado = operarSM(x,y,base);

        return complementador.toDRC(resultado,base);
    }


    public String operarRC(String numX, String numY, int base) throws OverflowException, InvalidParameterException{
        Complementador complementador = new Complementador();

        String x = complementador.toRC(numX,base);
        String y = complementador.toRC(numY,base);

        String resultado = operarSM(x,y,base);

        return complementador.toRC(resultado,base);
    }
}