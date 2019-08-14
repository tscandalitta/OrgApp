package com.example.proyectoandroidtdp.Redondeo;

import com.example.proyectoandroidtdp.CambioDeBase.Convertidor;

public class Redondeador implements RedondeadorAbstracto {

    public Redondeador(){
    }

    public String redondeoTruncado(String numero, String precision) {
        int pre = Integer.parseInt(precision);
        String[] partes = numero.split("\\.");
        String parteEntera, parteFraccionaria;

        if(partes.length >= 2) {
            parteEntera = partes[0];
            parteFraccionaria = partes[1];

            if(parteFraccionaria.length() > pre)
                parteFraccionaria = parteFraccionaria.substring(0, pre);

            if(parteFraccionaria.equals(""))
                numero = parteEntera;
            else
                numero = parteEntera + "." + parteFraccionaria;
        }
        return numero;
    }

    public String redondeoAumentacion(String numero, String precision){
        int cantBits = Integer.parseInt(precision);
        String[] partes = numero.split("\\.");
        String parteEntera, parteFraccionaria;

        if(partes.length>=2){
            parteFraccionaria = partes[1];
            if(parteFraccionaria.length()>cantBits){

                String numeroTruncado = redondeoTruncado(numero,precision);
                Convertidor convertidor = new Convertidor();
                String resultado = convertidor.toDecimal(numeroTruncado,2);
                double lsb = Math.pow(2,-Integer.parseInt(precision));
                double num = Double.parseDouble(resultado);
                return convertidor.fromDecimal(num+lsb+"",2);
            }
        }

        return numero;
    }

    public String redondeoBiased(String numero, String precision){
        int cantBits = Integer.parseInt(precision);
        String[] partes = numero.split("\\.");
        String parteEntera, parteFraccionaria;

        if(partes.length>=2){
            parteFraccionaria = partes[1];
            parteEntera = partes[0];

            if(parteFraccionaria.length()>cantBits){
                char bit = parteFraccionaria.charAt(cantBits);

                if(bit=='1'){
                    return redondeoAumentacion(numero,precision);
                }
                else{
                    return redondeoTruncado(numero,precision);
                }
            }
        }
        return numero;

    }
}
