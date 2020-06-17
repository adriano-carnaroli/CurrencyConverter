package br.edu.ifsp.scl.currencyconverter.view

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.edu.ifsp.scl.currencyconverter.R
import br.edu.ifsp.scl.currencyconverter.viewmodel.CurrencyConverterViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CurrencyConverterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = getString(R.string.currency_onverter_sdm)

        viewModel = CurrencyConverterViewModel(this)

        viewModel.buscaMoedasDisponiveis().observe(
            this@MainActivity,
            Observer {
                var lista:ArrayList<String> = it.keys.toList() as ArrayList<String>
                val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista)
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spOrigem!!.setAdapter(aa)
                spDestino!!.setAdapter(aa)
                spDestino.setSelection(lista.indexOf("BRL"))
                spOrigem.setSelection(lista.indexOf("USD"))
            }
        )

        /* Listener de click no botão */
        btnConverter.setOnClickListener {
            /* Acessar Web Service */
            viewModel.converterMoedas(spOrigem.selectedItem as String,
                spDestino.selectedItem as String, textAmount.text.toString().toFloat()).observe(
                this@MainActivity,
                Observer {
                    textResultado.text = it
                }
            )
        }
    }
}
