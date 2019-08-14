package com.example.proyectoandroidtdp.Redondeo;

public interface RedondeadorAbstracto {

    String redondeoTruncado(String numero, String precision);
    String redondeoAumentacion(String numero, String precision);
  //  String redondeoHaciaArriba(String numero, int precision);
  //  String redondeoHaciaAbajo(String numero, int precision);
    String redondeoBiased(String numero, String precision);
  //  String redondeoUnbiased(String numero, int precision);
}
