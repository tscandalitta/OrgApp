package com.example.proyectoandroidtdp.Redondeo;

import com.example.proyectoandroidtdp.CambioDeBase.Convertidor;

public class Redondeador implements RedondeadorAbstracto {

    public Redondeador(){
    }

    public String redondeoTruncado(String numero, String pre) {
        int precision = Integer.parseInt(pre);
        String[] partes = numero.split("\\.");
        String parteEntera, parteFraccionaria;

        if(partes.length >= 2) {
            parteEntera = partes[0];
            parteFraccionaria = partes[1];

            if(parteFraccionaria.length() > precision)
                parteFraccionaria = parteFraccionaria.substring(0, precision);

            if(parteFraccionaria.equals(""))
                numero = parteEntera;
            else
                numero = parteEntera + "." + parteFraccionaria;
        }
        return numero;
    }

    public String redondeoAumentacion(String numero, String pre){
        int precision = Integer.parseInt(pre);
        String[] partes = numero.split("\\.");
        String parteFraccionaria, toReturn;
        char signo = numero.charAt(0);

        if(partes.length >= 2){
            parteFraccionaria = partes[1];
            if(parteFraccionaria.length() > precision){

                String numeroTruncado = redondeoTruncado(numero,pre);
                Convertidor convertidor = new Convertidor();
                String resultado = convertidor.toDecimal(numeroTruncado,2);
                double lsb = Math.pow(2,-Integer.parseInt(pre));
                double num = Math.abs(Double.parseDouble(resultado));
                toReturn = convertidor.fromDecimal(num + lsb + "",2);
                toReturn = acomodarDigitos(toReturn, precision);  //agrega los 0' necesarios para cumplir con la precision
                if(signo == '-')
                    toReturn = "-" + toReturn;
                return toReturn;
            }
        }
        return numero;
    }

    public String redondeoBiased(String numero, String pre){
        int precision = Integer.parseInt(pre);
        String[] partes = numero.split("\\.");
        String parteFraccionaria;

        if(partes.length >= 2){
            parteFraccionaria = partes[1];

            if(parteFraccionaria.length() > precision){
                char bit = parteFraccionaria.charAt(precision);  //Obtengo el primer bit descartado
                if(bit == '1')
                    return redondeoAumentacion(numero,pre);
                else
                    return redondeoTruncado(numero,pre);
            }
        }
        return numero;
    }

    public String redondeoUnbiased(String numero, String pre){
        int precision = Integer.parseInt(pre);
        String[] partes = numero.split("\\.");
        String parteFraccionaria;

        if(partes.length >= 2) {
            parteFraccionaria = partes[1];

            if(parteFraccionaria.length() > precision){
                char bit = parteFraccionaria.charAt(precision);  //Obtengo el primer bit descartado
                char lsb = parteFraccionaria.charAt(precision - 1);
                if(bit == '1')
                    if(estoyEnUmbral(parteFraccionaria.substring(precision))) //Si estoy en el umbral redondeo hacia el numero par
                        if(lsb == '0')
                            return redondeoTruncado(numero,pre);
                        else
                            return redondeoAumentacion(numero,pre);
                    else  //Estoy pasado del umbral
                        return redondeoAumentacion(numero,pre);

                else  //Estoy debajo del umbral
                    return redondeoTruncado(numero,pre);
            }
        }
        return numero;
    }

    private boolean estoyEnUmbral(String digitos){
        boolean enUmbral = true;
        for(int i = 1; i < digitos.length() && enUmbral; i++)  //i == 1 xq el primer digito ya se que es 1
            enUmbral = digitos.charAt(i) == '0';
        return enUmbral;
    }

    private String acomodarDigitos(String numero, int precision){
        String[] partes = numero.split("\\.");
        String parteEntera = partes[0];
        String parteFraccionaria = partes[1];
        int cantCeros = precision - parteFraccionaria.length();
        for(int i = 0; i < cantCeros; i++){
            parteFraccionaria += "0";
        }
        return parteEntera + "." + parteFraccionaria;
    }
}
