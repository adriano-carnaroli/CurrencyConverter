package br.edu.ifsp.scl.currencyconverter.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.ifsp.scl.currencyconverter.model.CurrencyConverterService
import br.edu.ifsp.scl.currencyconverter.model.ResponseAvailableCurrencies

class CurrencyConverterViewModel(context: Context): ViewModel() {
    private val model = CurrencyConverterService(context)

    fun buscaMoedasDisponiveis(): MutableLiveData<ResponseAvailableCurrencies> {
        return model.getAvailableCurrencies()
    }
}