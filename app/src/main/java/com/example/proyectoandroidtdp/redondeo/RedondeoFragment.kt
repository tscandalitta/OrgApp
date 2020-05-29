package com.example.proyectoandroidtdp.redondeo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.proyectoandroidtdp.filtros.CreadorDeFiltros
import com.example.proyectoandroidtdp.filtros.CreadorDeFiltrosAbstracto
import com.example.proyectoandroidtdp.R

class RedondeoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_redondeo, container, false)

        val txtNumero: TextView = view.findViewById(R.id.txtNumeroARedondear)
        val txtPrecision: TextView = view.findViewById(R.id.txtPrecision)
        val txtTruncado: TextView = view.findViewById(R.id.txtRedondeoTruncado)
        val txtAumentacion: TextView = view.findViewById(R.id.txtRedondeoAumentacion)
        val txtBiased: TextView = view.findViewById(R.id.txtRedondeoBiased)
        val txtUnbiased: TextView = view.findViewById(R.id.txtRedondeoUnbiased)
        val btnRedondear: Button = view.findViewById(R.id.btnRedondear)

        val redondeador: Redondeador = RedondeadorImpl()
        val creadorDeFiltros: CreadorDeFiltrosAbstracto = CreadorDeFiltros()
        val filtroNumero = arrayOf(creadorDeFiltros.filtroBinarioFraccionarioSignado, LengthFilter(20))
        val filtroPrecision = arrayOf<InputFilter>(LengthFilter(2))

        txtNumero.filters = filtroNumero
        txtPrecision.filters = filtroPrecision
        val tipoDeEntrada = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_SIGNED or
                InputType.TYPE_NUMBER_FLAG_DECIMAL
        txtNumero.inputType = tipoDeEntrada

        btnRedondear.setOnClickListener {
            val numeroOriginal = txtNumero.text.toString()
            val precision = txtPrecision.text.toString()
            if (numeroOriginal.isNotEmpty() && precision.isNotEmpty()) {
                val numeroRedondeado = redondeador.redondeoTruncado(numeroOriginal, precision)
                val numeroAumentacion = redondeador.redondeoAumentacion(numeroOriginal, precision)
                val numeroBiased = redondeador.redondeoBiased(numeroOriginal, precision)
                val numeroUnbiased = redondeador.redondeoUnbiased(numeroOriginal, precision)

                txtTruncado.text = numeroRedondeado
                txtAumentacion.text = numeroAumentacion
                txtBiased.text = numeroBiased
                txtUnbiased.text = numeroUnbiased
            }
        }
        return view
    }
}