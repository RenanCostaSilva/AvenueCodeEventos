package br.com.renancsdev.avenuecodeeventos.ui.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import br.com.renancsdev.avenuecodeeventos.R
import br.com.renancsdev.avenuecodeeventos.api.EventosChamada
import br.com.renancsdev.avenuecodeeventos.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val context = this@MainActivity
    private val activity = context as Activity
    private val layoutMain = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setarLayout()
        setarBinding()
        esconderEventos()
        requisicaoAPI()

    }


    private fun setarLayout(){
        setContentView(layoutMain)
    }
    private fun setarBinding(){
        binding = DataBindingUtil.setContentView(context , layoutMain)
    }

    private fun requisicaoAPI(){
        CoroutineScope(Dispatchers.IO).launch{
            EventosChamada(context , binding).verificarEventoRetornoDaApi()
        }
    }


    fun esconderEventos(){
        binding.includeErro404.visibility = View.GONE
    }
}
