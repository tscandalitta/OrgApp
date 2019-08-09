package com.example.proyectoandroidtdp.Redondeo;

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
}
