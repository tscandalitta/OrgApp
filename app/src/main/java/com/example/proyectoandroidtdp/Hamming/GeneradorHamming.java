package com.example.proyectoandroidtdp.Hamming;

import com.example.proyectoandroidtdp.Conversor.Conversor;
import com.example.proyectoandroidtdp.Conversor.ConversorAbstracto;

public class GeneradorHamming implements GeneradorHammingAbstracto {

    private ConversorAbstracto conversor;

    public GeneradorHamming(){
        conversor = new Conversor();
    }

    public String getHamming3(String msg){
        return conversor.arrayToString(calcularHamming3(msg));
    }

    public String getHamming4(String msg){
        return conversor.arrayToString(calcularHamming4(msg));
    }

    public int[] getBitsCodigo(String msg) {
        int[] arr = conversor.stringToArray(msg);
        int bitsCodigo = 0;
        for(int i = 1; i < arr.length; i*=2)
            bitsCodigo++;
        int[] bits = new int[bitsCodigo];
        int pos = 0;
        for(int i = 1; i < arr.length; i*=2)
            bits[pos++] = arr[i-1];
        return bits;
    }

    public String getBitsMensaje(String msg){
        int[] arr = conversor.stringToArray(msg);
        String toReturn = "";
        for(int i = 0; i < arr.length; i++)
            if(i != 0 && i != 1 && i != 3 && i != 7 && i != 15)
                toReturn += arr[i];
        return toReturn;
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
    // 0 corresponde a la politica de correccion simple y deteccion simple.
    // 1 corresponde a la politica de deteccion simple y doble.
    public String verificarHamming3(String msg,int politica){
        String mensajeRecibido = getBitsMensaje(msg);
        int sindrome;
        String resultadoHamming;
        int[] bitsCodigoRecibidos = getBitsCodigo(msg);
        String paqueteRecalculado = getHamming3(mensajeRecibido);
        int[] bitsCodigoRecalculados = getBitsCodigo(paqueteRecalculado);
        sindrome = calcularSindrome(bitsCodigoRecibidos,bitsCodigoRecalculados);

        if(sindrome==0)
            resultadoHamming="NO SE DETECTARON ERRORES";
        else {
            resultadoHamming=mensajeHamming(sindrome,politica,mensajeRecibido.length()-1);
        }
        return resultadoHamming;
    }

    private String mensajeHamming(int sindrome, int politica, int longitud){
        String mensaje;
        if (politica == 0 && sindrome <= longitud)
            mensaje="SE DETECTO ERROR SIMPLE EN LA POSICION: "+sindrome;
        else{
            if (politica == 0 && sindrome > longitud )
                mensaje="SE DETECTO ERROR, SE DESCONOCE LA LONGITUD DE LA RAFAGA";
            else{
                if(politica == 1 && sindrome <= longitud)
                    mensaje="SE DETECTO ERROR SIMPLE O DOBLE";
                else
                    mensaje="SE DETECTO ERROR DOBLE";
            }
        }
        return  mensaje;
    }

    private int calcularSindrome(int [] bitRecibidos, int [] bitsRecalculados){
        String sindromeString = "";
        for(int i = bitRecibidos.length-1;i>=0;i--)
            sindromeString += bitRecibidos[i]^bitsRecalculados[i];
        return Integer.parseInt(sindromeString,2);

    }

    @Override
    public String verificarHamming4(String msg, int politica) {
        return null;
    }
}


