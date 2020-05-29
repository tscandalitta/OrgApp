package com.example.proyectoandroidtdp.conversor

class Conversor : ConversorAbstracto {
    override fun arrayToString(array: IntArray): String {
        var toReturn = ""
        for (i in array) toReturn += i
        return toReturn
    }

    override fun stringToArray(msg: String): IntArray {
        val array = IntArray(msg.length)
        for (i in array.indices) array[i] = msg[i].toString().toInt()
        return array
    }
}