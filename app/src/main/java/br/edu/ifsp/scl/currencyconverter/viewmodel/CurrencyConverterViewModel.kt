package br.edu.ifsp.scl.currencyconverter.viewmodel

import android.content.Context
import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.ifsp.scl.currencyconverter.model.CurrencyConverterService

class CurrencyConverterViewModel(context: Context): ViewModel() {
    private val model = CurrencyConverterService(context)

    fun buscaMoedasDisponiveis(): MutableLiveData<Map<String, String>> {
        return model.getAvailableCurrencies()
    }

    fun converterMoedas(origen:String, destino:String, amount: Float): MutableLiveData<String> {
        return model.converterMoedas(origen, destino, amount)
    }
}