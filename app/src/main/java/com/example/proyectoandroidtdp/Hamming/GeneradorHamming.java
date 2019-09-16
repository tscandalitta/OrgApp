package com.example.proyectoandroidtdp.Hamming;

import com.example.proyectoandroidtdp.Conversor.Conversor;
import com.example.proyectoandroidtdp.Conversor.ConversorAbstracto;

import java.security.InvalidParameterException;

public class GeneradorHamming implements GeneradorHammingAbstracto {

    private static final int D1C1 = 0;
    private static final int D3C0 = 0;

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
        for(int i = 0; i < arr.length - 1; i++)
            if(i != 0 && i != 1 && i != 3 && i != 7 && i != 15)
                toReturn += arr[i];
        toReturn += arr[arr.length - 1]; //agrego el ultimo bit aca para evitar bug con paridad en posicion potencia
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
    public String verificarHamming3(String msg, int politica) throws InvalidParameterException {
        if(!longitudValida(msg,3))
            throw new InvalidParameterException("Longitud de mensaje inválida");
        String mensajeRecibido = getBitsMensaje(msg);
        int sindrome;
        int[] bitsCodigoRecibidos = getBitsCodigo(msg);
        String paqueteRecalculado = getHamming3(mensajeRecibido);
        int[] bitsCodigoRecalculados = getBitsCodigo(paqueteRecalculado);
        sindrome = calcularSindrome(bitsCodigoRecibidos,bitsCodigoRecalculados);

        return mensajeHamming3(sindrome,politica,msg.length());
    }

    public String verificarHamming4(String msg, int politica) throws InvalidParameterException {
        if(!longitudValida(msg,4))
            throw new InvalidParameterException("Longitud de mensaje inválida");
        String mensajeRecibidoConParidad = getBitsMensaje(msg);
        char paridadRecibida = mensajeRecibidoConParidad.charAt(mensajeRecibidoConParidad.length() - 1);
        String mensajeRecibido = mensajeRecibidoConParidad.substring(0,mensajeRecibidoConParidad.length() - 1);
        int sindrome;
        int[] bitsCodigoRecibidos = getBitsCodigo(msg);
        String paqueteRecalculado = getHamming4(mensajeRecibido);
        int[] bitsCodigoRecalculados = getBitsCodigo(paqueteRecalculado);
        char paridadRecalculada = paqueteRecalculado.charAt(paqueteRecalculado.length() - 1);
        sindrome = calcularSindrome(bitsCodigoRecibidos,bitsCodigoRecalculados);

        return mensajeHamming4(sindrome,politica,msg.length(), paridadRecibida, paridadRecalculada);
    }

    private String mensajeHamming3(int sindrome, int politica, int longitud){
        String mensaje;
        if(sindrome == 0)
            mensaje = "No se detectaron errores";
        else
            if (politica == D1C1)
                if(sindrome <= longitud)
                    mensaje = "Se detectó error simple en la posición " + sindrome;
                else
                    mensaje = "Se detectó error, se desconoce la longitud de la ráfaga";
            else //politica = D2C0
                if(sindrome <= longitud)
                    mensaje = "Se detectó error simple o doble";
                else
                    mensaje = "Se detectó error doble";
        return  mensaje;
    }
    private String mensajeHamming4(int sindrome, int politica, int longitud, int paridadRecibida, int paridadRecalculada){
        String mensaje;
        int xorParidad = paridadRecibida ^ paridadRecalculada;

        if(politica == D3C0) {
            if (sindrome == 0) {
                if (xorParidad == 0)
                    mensaje = "No se detectaron errores";
                else
                    mensaje = "Se detectó error triple o error en bit de paridad";
            }
            else {
                if (xorParidad == 0)
                    mensaje = "Se detectó error doble";
                else
                    if (sindrome <= longitud)
                        mensaje = "Se detectó error simple o triple";
                    else
                        mensaje = "Se detectó error triple";
            }
        }
        else{ //politica = D2C1
            if (sindrome == 0) {
                if (xorParidad == 0)
                    mensaje = "No se detectaron errores";
                else
                    mensaje = "Se detectó error en bit de paridad";
            }
            else {
                if (xorParidad == 0)
                    mensaje = "Se detectó error doble";
                else
                    if (sindrome <= longitud)
                        mensaje = "Se detectó porción de error???????";
                    else
                        mensaje = "Se detectó error de longitud mayor a 2";
            }
        }
        return mensaje;
    }

    private int calcularSindrome(int [] bitRecibidos, int [] bitsRecalculados){
        String sindromeString = "";
        for(int i = bitRecibidos.length - 1; i >= 0; i--)
            sindromeString += bitRecibidos[i] ^ bitsRecalculados[i];
        return Integer.parseInt(sindromeString,2);
    }

    private boolean longitudValida(String mensaje, int hamming){
        int agregado = 0;
        if(hamming == 4)
            agregado = 1;
        boolean longitudValida = true;
        for(int i = 0; i < mensaje.length() && longitudValida; i++)
            longitudValida = (Math.pow(2,i) + agregado) != mensaje.length();
        return longitudValida;
    }

}


