package br.com.renancsdev.avenuecodeeventos.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.renancsdev.avenuecodeeventos.R
import br.com.renancsdev.avenuecodeeventos.api.sealed.Resultado
import br.com.renancsdev.avenuecodeeventos.databinding.ActivityMainBinding
import br.com.renancsdev.avenuecodeeventos.extension.esconder
import br.com.renancsdev.avenuecodeeventos.model.Evento
import br.com.renancsdev.avenuecodeeventos.ui.adapter.RecyclerViewEvento
import br.com.renancsdev.avenuecodeeventos.viewmodel.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val viewModel by viewModel<MainViewModel>()
    private val context     = this@MainActivity
    private val layoutMain  = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setarLayout()
        setarBinding()
        esconderEventos()
        buscarEventos()
    }


    private fun setarLayout(){
        setContentView(layoutMain)
    }
    private fun setarBinding(){
        binding = DataBindingUtil.setContentView(context , layoutMain)
    }

    private fun buscarEventos(){
        viewModel.buscaListaEventos().observe(this@MainActivity){ model ->
            model?.let { resultado -> viewModelResultado(resultado)} ?: false
        }
    }

    private fun viewModelResultado (resultado: Resultado<ArrayList<Evento>?>){
        when (resultado){
            is Resultado.Sucesso ->{
                resultado.dado?.let {
                    initFilmesEventoAdapter(it , binding)
                    true
                } ?: false
            }
            is Resultado.Erro -> false
        }
    }


    //Adapter
    private fun initFilmesEventoAdapter(evento: List<Evento>, binding: ActivityMainBinding){

        binding.rvEventoMain.apply {
            layoutManager = LinearLayoutManager(binding.root.context)
            adapter       = RecyclerViewEvento(evento)
        }
        binding.pbRecyclerMain.esconder()

    }

    fun esconderEventos(){
        binding.includeErro404.esconder()
    }
}
