package br.edu.ifsp.scl.currencyconverter.model

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.edu.ifsp.scl.currencyconverter.R
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import java.lang.reflect.Type


class CurrencyConverterService(val context: Context) {
    /* Fila de requisições Volley */
    private val filaRequisicoesVolley = Volley.newRequestQueue(context)
    private val gson = Gson()

    fun converterMoedas(origen:String, destino:String, amount:Float): MutableLiveData<String>  {
        val url = StringBuilder(CurrencyConverterApi.URL_BASE)
            .append("${CurrencyConverterApi.END_POINT_CONVERTER}")
            .append("&from=${origen}&to=${destino}&amount=${amount}")
            .toString()

        val respostaRequisicaoLd = MutableLiveData<String>()
        val requisicao = buildRequestConverter(url, respostaRequisicaoLd, destino)

        filaRequisicoesVolley.add(requisicao)
        return respostaRequisicaoLd
    }

    private fun buildRequestConverter(url: String, respostaRequisicaoLd: MutableLiveData<String>, destino: String): JsonObjectRequest {
        return object: JsonObjectRequest(Method.GET,  url, null, { response: JSONObject? ->
                response?.let{
                    respostaRequisicaoLd.value =
                        ((it.get("rates") as JSONObject).get(destino) as JSONObject).get("rate_for_amount") as String?
                }
            }, { error: VolleyError? ->
                Log.e(context.getString(R.string.app_name), error?.message)
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                return mutableMapOf(
                    Pair(
                        CurrencyConverterApi.X_RAPIDAPI_KEY_FIELD,
                        CurrencyConverterApi.X_RAPIDAPI_KEY_VALUE
                    ),
                    Pair(
                        CurrencyConverterApi.X_RAPIDAPI_HOST_FIELD,
                        CurrencyConverterApi.X_RAPIDAPI_HOST_VALUE
                    )
                )
            }
        }
    }

    /* Acessa o WebService e retorna um LiveData que será preenchido */
    fun getAvailableCurrencies(): MutableLiveData<Map<String, String>> {
        /* Montando URL de consulta ao WebService */
        val url = StringBuilder(CurrencyConverterApi.URL_BASE)
            .append("${CurrencyConverterApi.END_POINT_AVAILABLE_CURRENCIES}")
            .toString()

        /* Montando requisição */
        val respostaRequisicaoLd = MutableLiveData<Map<String, String>>()
        val requisicao = buildRequest(url, respostaRequisicaoLd)

        /* Adiciona a requisição na fila de requisições Volley */
        filaRequisicoesVolley.add(requisicao)

        return respostaRequisicaoLd
    }

    private fun buildRequest(url: String, respostaRequisicaoLd: MutableLiveData<Map<String, String>>): JsonObjectRequest {
        return object: JsonObjectRequest(
            Method.GET,       // Método HTTP de requisição
            url,              // URL
            null,  // Objeto de requisição JSON - usando em POST
            { response: JSONObject? ->
                response?.let{
                    val jsonString = it.get("currencies").toString()//response.toString().replace("{\"currencies\":", "").replace(",\"status\":\"success\"}", "")
                    val empMapType: Type = object : TypeToken<Map<String?, String?>?>() {}.type
                    val currenciesResult: Map<String, String> = Gson().fromJson(jsonString, empMapType)
                    respostaRequisicaoLd.value = currenciesResult
                }
            }, // Listener de resposta
            { error: VolleyError? ->
                Log.e(context.getString(R.string.app_name), error?.message)
            } // Listener de erro
        ) {
            /* Corpo do JsonObjectRequest. Sobreescrevendo a função para passar cabeçalho na requisição */
            override fun getHeaders(): MutableMap<String, String> {
                /* Cabeçalho composto por Map com X_RAPIDAPI_KEY e X_RAPIDAPI_HOST */
                return mutableMapOf(
                    Pair(
                        CurrencyConverterApi.X_RAPIDAPI_KEY_FIELD,
                        CurrencyConverterApi.X_RAPIDAPI_KEY_VALUE
                    ),
                    Pair(
                        CurrencyConverterApi.X_RAPIDAPI_HOST_FIELD,
                        CurrencyConverterApi.X_RAPIDAPI_HOST_VALUE
                    )
                )
            }
        }
    }
}