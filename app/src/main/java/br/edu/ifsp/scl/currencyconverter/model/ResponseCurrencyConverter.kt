package br.edu.ifsp.scl.currencyconverter.model

data class ResponseCurrencyConverter (
    val amount: String,
    val baseCurrencyCode: String,
    val baseCurrencyName: String,
    val rates: Rates,
    val status: String,
    val updatedDate: String
)

data class Rates (
    val brl: Brl
)

data class Brl (
    val currencyName: String,
    val rate: String,
    val rateForAmount: String
)