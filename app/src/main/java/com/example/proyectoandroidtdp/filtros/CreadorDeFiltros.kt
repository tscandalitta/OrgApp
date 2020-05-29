package com.example.proyectoandroidtdp.filtros

import android.text.InputFilter

class CreadorDeFiltros : CreadorDeFiltrosAbstracto {

    override val filtroBinarioFraccionario: InputFilter?
        get() = InputFilter { source, start, end, _, _, _ ->
            for (i in start until end) {
                if (source[i] < '.' || source[i] > '1') {
                    return@InputFilter ""
                }
            }
            null
        }
    override val filtroBinarioFraccionarioSignado: InputFilter?
        get() = InputFilter { source, start, end, _, _, _ ->
            for (i in start until end) {
                if (source[i] < '-' || source[i] > '1') {
                    return@InputFilter ""
                }
            }
            null
        }

    override val filtroOctalFraccionario: InputFilter?
        get() = InputFilter { source, start, end, _, _, _ ->
            for (i in start until end) {
                if (source[i] < '.' || source[i] > '7') {
                    return@InputFilter ""
                }
            }
            null
        }


    override val filtroDecimalFraccionario: InputFilter?
        get() = InputFilter { source, start, end, _, _, _ ->
            for (i in start until end) {
                if (source[i] < '.' || source[i] > '9') {
                    return@InputFilter ""
                }
            }
            null
        }


    override val filtroHexaFraccionario: InputFilter?
        get() = InputFilter { source, start, end, _, _, _ ->
            val sourceUpper = source.toString().toUpperCase()
            for (i in start until end) {
                if (!(sourceUpper[i] == '.' || Character.isDigit(sourceUpper[i]) ||
                                sourceUpper[i] in 'A'..'F' ||
                                sourceUpper[i] in 'a'..'f')) {
                    return@InputFilter ""
                }
            }
            sourceUpper
        }
    override val filtroBinarioEntero: InputFilter?
        get() = InputFilter { source, start, end, _, _, _ ->
            for (i in start until end) {
                if (source[i] < '0' || source[i] > '1') {
                    return@InputFilter ""
                }
            }
            null
        }
    override val filtroOctalEntero: InputFilter?
        get() = InputFilter { source, start, end, _, _, _ ->
            for (i in start until end) {
                if (source[i] < '0' || source[i] > '7') {
                    return@InputFilter ""
                }
            }
            null
        }
    override val filtroDecimalEntero: InputFilter?
        get() = InputFilter { source, start, end, _, _, _ ->
            for (i in start until end) {
                if (source[i] < '0' || source[i] > '9') {
                    return@InputFilter ""
                }
            }
            null
        }
    override val filtroHexaEntero: InputFilter?
        get() = InputFilter { source, start, end, _, _, _ ->
            var sourceUpper = source.toString().toUpperCase()
            for (i in start until end) {
                if (!(Character.isDigit(sourceUpper[i]) ||
                                sourceUpper[i] in 'A'..'F' ||
                                sourceUpper[i] in 'a'..'f')) {
                    return@InputFilter ""
                }
            }
            source
        }
}