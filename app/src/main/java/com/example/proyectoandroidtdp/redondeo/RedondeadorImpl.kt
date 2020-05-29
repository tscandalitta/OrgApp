package com.example.proyectoandroidtdp.Redondeo

import com.example.proyectoandroidtdp.CambioDeBase.Convertidor
import kotlin.math.abs
import kotlin.math.pow

class RedondeadorImpl : Redondeador {
    override fun redondeoTruncado(numero: String?, pre: String?): String? {
        var numero = numero
        val precision = pre!!.toInt()
        val partes = numero!!.split("\\.").toTypedArray()
        val parteEntera: String
        var parteFraccionaria: String
        if (partes.size >= 2) {
            if (precision == 0) return partes[0]
            parteEntera = partes[0]
            parteFraccionaria = partes[1]
            if (parteFraccionaria.length > precision) parteFraccionaria = parteFraccionaria.substring(0, precision)
            numero = if (parteFraccionaria == "") parteEntera else "$parteEntera.$parteFraccionaria"
        }
        return numero
    }

    override fun redondeoAumentacion(numero: String?, pre: String?): String? {
        val precision = pre!!.toInt()
        val partes = numero!!.split("\\.").toTypedArray()
        val parteFraccionaria: String
        var toReturn: String
        val signo = numero[0]
        if (partes.size >= 2) {
            if (precision == 0) return partes[0]
            parteFraccionaria = partes[1]
            if (parteFraccionaria.length > precision) {
                val numeroTruncado = redondeoTruncado(numero, pre)
                val convertidor = Convertidor()
                val resultado = convertidor.toDecimal(numeroTruncado, 2)
                val lsb = 2.0.pow(-pre.toInt().toDouble())
                val num = abs(resultado.toDouble())
                toReturn = convertidor.fromDecimal((num + lsb).toString(), 2)
                toReturn = acomodarDigitos(toReturn, precision) //agrega los 0' necesarios para cumplir con la precision
                if (signo == '-') toReturn = "-$toReturn"
                return toReturn
            }
        }
        return numero
    }

    override fun redondeoBiased(numero: String?, pre: String?): String? {
        val precision = pre!!.toInt()
        val partes = numero!!.split("\\.").toTypedArray()
        val parteFraccionaria: String
        if (partes.size >= 2) {
            if (precision == 0) return partes[0]
            parteFraccionaria = partes[1]
            if (parteFraccionaria.length > precision) {
                val bit = parteFraccionaria[precision] //Obtengo el primer bit descartado
                return if (bit == '1') redondeoAumentacion(numero, pre) else redondeoTruncado(numero, pre)
            }
        }
        return numero
    }

    override fun redondeoUnbiased(numero: String?, pre: String?): String? {
        val precision = pre!!.toInt()
        val partes = numero!!.split("\\.").toTypedArray()
        val parteFraccionaria: String
        if (partes.size >= 2) {
            if (precision == 0) return partes[0]
            parteFraccionaria = partes[1]
            if (parteFraccionaria.length > precision) {
                val bit = parteFraccionaria[precision] //Obtengo el primer bit descartado
                val lsb = parteFraccionaria[precision - 1]
                return if (bit == '1') if (estoyEnUmbral(parteFraccionaria.substring(precision))) //Si estoy en el umbral redondeo hacia el numero par
                    if (lsb == '0') redondeoTruncado(numero, pre) else redondeoAumentacion(numero, pre) else  //Estoy pasado del umbral
                    redondeoAumentacion(numero, pre) else  //Estoy debajo del umbral
                    redondeoTruncado(numero, pre)
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