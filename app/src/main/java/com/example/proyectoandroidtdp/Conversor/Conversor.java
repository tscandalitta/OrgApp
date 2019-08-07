package com.example.proyectoandroidtdp.Conversor;

@SuppressWarnings("StringConcatenationInLoop")
public class Conversor implements ConversorAbstracto {

    public Conversor(){
    }

    public int[] stringToArray(String msg) {
        int[] array = new int[msg.length()];
        for(int i = 0; i < array.length; i++)
            array[i] = Integer.parseInt(Character.toString(msg.charAt(i)));
        return array;
    }

    public String arrayToString(int[] array) {
        String toReturn = "";
        for(int i : array)
            toReturn += i;
        return toReturn;
    }
}
