package com.example.proyectoandroidtdp.OperacionesAritmeticas;

import com.example.proyectoandroidtdp.SistemasDeRepresentacion.Complementador;

import java.security.InvalidParameterException;

@SuppressWarnings("StringConcatenationInLoop")
public class OperadorAritmetico implements  OperadorAritmeticoAbstracto {



    private void verificarOperandos(String numX, String numY, int base) throws InvalidParameterException{
        if(numX.equals("") || numY.equals(""))
            throw new InvalidParameterException("Operando/s nulo/s, por favor complete ambos campos.");

        if(numX.length() != numY.length())
            throw new InvalidParameterException("Operandos de distinta longitud");

        if(!(numX.charAt(0)=='0' || numX.charAt(0)=='F' || numX.charAt(0) == ((char) (base-1)+'0')) ||
                !(numY.charAt(0) == '0' || numY.charAt(0) == 'F' || numY.charAt(0) == ((char) (base-1)+'0'))){
            throw new InvalidParameterException("Por favor, ingrese un número con signo válido."+" "+
                    "0 o "+Integer.toString(base-1,base).toUpperCase());
        }

        if(numX.length() == 1) //Como X e Y tienen la misma longitud, chequeo solo X
            throw new InvalidParameterException("Ingrese un número con longitud válida.");
    }

    public String operarSM(String numX, String numY, int base) throws OverflowException, InvalidParameterException {

        String resultado = sumarSM(numX,numY,base);

        if(resultado.length() > numX.length())
            throw new OverflowException();

        return resultado;
    }

    private String sumarSM(String numX, String numY, int base) throws InvalidParameterException {

        verificarOperandos(numX,numY,base);

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

        String resultado = sumarSM(x,y,base);

        if(resultado.length() > numX.length())
            throw new OverflowException();

        return complementador.toDRC(resultado,base);
    }

    public String operarRC(String numX, String numY, int base) throws OverflowException, InvalidParameterException{
        Complementador complementador = new Complementador();

        String x = complementador.toRC(numX,base);
        String y = complementador.toRC(numY,base);

        String resultado = sumarSM(x,y,base);

        ////////////////////////////////Solucion hecha a medida debido a que usamos sumarSM()///////////////////////////////
        if (!numeroValidoRC(resultado, numX.length()))
            throw new OverflowException();
        if(resultado.length() > numX.length()) {
            if(base == 16)
                resultado = resultado.replace('1','F');
            else
                resultado = resultado.replace('1',(char)(base-1 + '0'));
            resultado = resultado.substring(1);
        }

        return complementador.toRC(resultado,base);
    }

    /**
     * Verifica que el resultado sea valido
     * @param resultado resultado obtenido tras operar en RC
     * @param longitud: longitud de los operandos
     *  largo que operandos, es porque son del mismo signo
     * @return valido o no
     */
    private boolean numeroValidoRC(String resultado, int longitud) {
        boolean valido = true;

        if(resultado.length() > longitud) {
            if(resultado.charAt(1) != '1')
                valido = false;
            for(int i = 2; i < resultado.length() && valido; i++) //verifico que sea el caso limite del mayor negativo "(base-1)000..00"
                valido = resultado.charAt(i) == '0';
        }
        return valido;
    }
}