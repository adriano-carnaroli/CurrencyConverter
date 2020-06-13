package br.edu.ifsp.scl.currencyconverter.view

import android.os.Build
import android.os.Bundle
import android.os.StrictMode
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

        /* Listener de click no botão */
        btnConverter.setOnClickListener {
            /* Acessar Web Service */
            viewModel.buscaMoedasDisponiveis().observe(
                this@MainActivity,
                Observer {
                    print(it)
                }
            )
        }
    }
}
