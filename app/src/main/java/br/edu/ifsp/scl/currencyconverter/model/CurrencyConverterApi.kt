package br.edu.ifsp.scl.currencyconverter.model

object CurrencyConverterApi {
    val URL_BASE = "https://currency-converter5.p.rapidapi.com/currency/"

    val END_POINT_AVAILABLE_CURRENCIES = "list"
    val END_POINT_CONVERTER = "convert?format=json" //&from=AUD&to=BRL&amount=1

    val X_RAPIDAPI_KEY_FIELD = "x-rapidapi-key"
    val X_RAPIDAPI_KEY_VALUE = "794cc3ff9emsh3e11ff7e188ca40p194238jsnc52ced900b6d"
    val X_RAPIDAPI_HOST_FIELD = "x-rapidapi-host"
    val X_RAPIDAPI_HOST_VALUE = "currency-converter5.p.rapidapi.com"
}