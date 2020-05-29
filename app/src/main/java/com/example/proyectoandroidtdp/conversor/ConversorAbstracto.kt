package com.example.proyectoandroidtdp.conversor

interface ConversorAbstracto {
    fun arrayToString(array: IntArray): String
    fun stringToArray(msg: String): IntArray
}