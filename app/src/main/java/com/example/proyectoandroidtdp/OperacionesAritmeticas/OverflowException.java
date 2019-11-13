package com.example.proyectoandroidtdp.OperacionesAritmeticas;

public class OverflowException extends Exception {
    public OverflowException(){
        super("Se produjo overflow al realizar la operación");
    }
}
