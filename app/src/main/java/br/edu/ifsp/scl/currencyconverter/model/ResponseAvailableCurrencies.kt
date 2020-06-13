package br.edu.ifsp.scl.currencyconverter.model

data class ResponseAvailableCurrencies (
    val currencies: Map<String, String>,
    val status: String
)