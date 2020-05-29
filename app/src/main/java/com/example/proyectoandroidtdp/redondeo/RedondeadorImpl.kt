package com.example.proyectoandroidtdp.redondeo

import com.example.proyectoandroidtdp.CambioDeBase.Convertidor
import kotlin.math.abs
import kotlin.math.pow

class RedondeadorImpl : Redondeador {
    override fun redondeoTruncado(numero: String?, precision: String?): String? {
        var numeroRedondeoTruncado = numero
        val precisionInt = precision!!.toInt()
        val partes = numeroRedondeoTruncado!!.split("\\.").toTypedArray()
        val parteEntera: String
        var parteFraccionaria: String
        if (partes.size >= 2) {
            if (precisionInt == 0) return partes[0]
            parteEntera = partes[0]
            parteFraccionaria = partes[1]
            if (parteFraccionaria.length > precisionInt) parteFraccionaria = parteFraccionaria.substring(0, precisionInt)
            numeroRedondeoTruncado = if (parteFraccionaria == "") parteEntera else "$parteEntera.$parteFraccionaria"
        }
        return numeroRedondeoTruncado
    }

    override fun redondeoAumentacion(numero: String?, precision: String?): String? {
        val precisionInt = precision!!.toInt()
        val partes = numero!!.split("\\.").toTypedArray()
        val parteFraccionaria: String
        var toReturn: String
        val signo = numero[0]
        if (partes.size >= 2) {
            if (precisionInt == 0) return partes[0]
            parteFraccionaria = partes[1]
            if (parteFraccionaria.length > precisionInt) {
                val numeroTruncado = redondeoTruncado(numero, precisionInt.toString())
                val convertidor = Convertidor()
                val resultado = convertidor.toDecimal(numeroTruncado, 2)
                val lsb = 2.0.pow(-precisionInt.toDouble())
                val num = abs(resultado.toDouble())
                toReturn = convertidor.fromDecimal((num + lsb).toString(), 2)
                toReturn = acomodarDigitos(toReturn, precisionInt) //agrega los 0' necesarios para cumplir con la precision
                if (signo == '-') toReturn = "-$toReturn"
                return toReturn
            }
        }
        return numero
    }

    override fun redondeoBiased(numero: String?, precision: String?): String? {
        val precisionInt = precision!!.toInt()
        val partes = numero!!.split("\\.").toTypedArray()
        val parteFraccionaria: String
        if (partes.size >= 2) {
            if (precisionInt == 0) return partes[0]
            parteFraccionaria = partes[1]
            if (parteFraccionaria.length > precisionInt) {
                val bit = parteFraccionaria[precisionInt] //Obtengo el primer bit descartado
                return if (bit == '1') redondeoAumentacion(numero, precisionInt.toString()) else redondeoTruncado(numero, precisionInt.toString())
            }
        }
        return numero
    }

    override fun redondeoUnbiased(numero: String?, precision: String?): String? {
        val precisionInt = precision!!.toInt()
        val partes = numero!!.split("\\.").toTypedArray()
        val parteFraccionaria: String
        if (partes.size >= 2) {
            if (precisionInt == 0) return partes[0]
            parteFraccionaria = partes[1]
            if (parteFraccionaria.length > precisionInt) {
                val bit = parteFraccionaria[precisionInt] //Obtengo el primer bit descartado
                val lsb = parteFraccionaria[precisionInt - 1]
                return if (bit == '1') if (estoyEnUmbral(parteFraccionaria.substring(precisionInt))) //Si estoy en el umbral redondeo hacia el numero par
                    if (lsb == '0') redondeoTruncado(numero, precisionInt.toString()) else redondeoAumentacion(numero, precisionInt.toString()) else  //Estoy pasado del umbral
                    redondeoAumentacion(numero, precisionInt.toString()) else  //Estoy debajo del umbral
                    redondeoTruncado(numero, precisionInt.toString())
            }
        }
        return numero
    }

    private fun estoyEnUmbral(digitos: String): Boolean {
        var enUmbral = true
        var i = 1
        while (i < digitos.length && enUmbral) {
            //i == 1 xq el primer digito ya se que es 1
            enUmbral = digitos[i] == '0'
            i++
        }
        return enUmbral
    }

    private fun acomodarDigitos(numero: String, precision: Int): String {
        val partes = numero.split("\\.").toTypedArray()
        val parteEntera = partes[0]
        var parteFraccionaria = partes[1]
        val cantCeros = precision - parteFraccionaria.length
        for (i in 0 until cantCeros) {
            parteFraccionaria += "0"
        }
        return "$parteEntera.$parteFraccionaria"
    }
}