package com.example.proyectoandroidtdp.OperacionesAritmeticas;

public class OverflowException extends Exception {
    public OverflowException(){
        super("Se produjo Overflow al realizar la operacion");
    }
}
