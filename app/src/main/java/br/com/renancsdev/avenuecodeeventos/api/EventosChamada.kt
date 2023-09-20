package br.com.renancsdev.avenuecodeeventos.api

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.renancsdev.avenuecodeeventos.R
import br.com.renancsdev.avenuecodeeventos.databinding.ActivityDetalheEventoBinding
import br.com.renancsdev.avenuecodeeventos.databinding.ActivityMainBinding
import br.com.renancsdev.avenuecodeeventos.model.Evento
import br.com.renancsdev.avenuecodeeventos.ui.adapter.RecyclerViewEvento
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EventosChamada(var context: Context, var binding: ActivityMainBinding) {

    //Api
    private fun eventoChamadaApi(): Call<ArrayList<Evento>> = ServiceBuilder.buildService(API::class.java).busarEventos()

    fun verificarEventoRetornoDaApi(){
        eventoChamadaApi().enqueue(object : Callback<ArrayList<Evento>> {
            override fun onResponse(call: Call<ArrayList<Evento>>, response: Response<ArrayList<Evento>>) {
                if(respostaEventoDaApi(response)){
                    binding.includeErro404.visibility = View.GONE
                    mostrarEventoConteudoDaRespostaDaApi(response , binding)
                }
            }
            override fun onFailure(call: Call<ArrayList<Evento>>, t: Throwable) {
                binding.pbRecyclerMain.visibility = View.GONE
                binding.includeErro404.visibility = View.VISIBLE
                exiberMensagemErro("verificarEventoRetornoDaApi" , "${t.message}")
            }
        })
    }
    fun respostaEventoDaApi(resposta: Response<ArrayList<Evento>>): Boolean{

        var check = false
        if(resposta.isSuccessful) {
            check = true
        }else{
            exiberMensagemErro("respostaEventoDaApi" , resposta.errorBody().toString())
        }

        return check
    }

    fun mostrarEventoConteudoDaRespostaDaApi(resposta: Response<ArrayList<Evento>>, mainBinding: ActivityMainBinding ){

        binding.pbRecyclerMain.visibility = View.VISIBLE
        if (resposta.body() != null) {
            initFilmesEventoAdapter(resposta.body()!! , mainBinding )
        } else {
            exiberMensagemErro("mostrarEventoConteudoDaRespostaDaApi" , "Failed to get response")
        }
        binding.pbRecyclerMain.visibility = View.GONE
    }

    //Adapter
    private fun initFilmesEventoAdapter(evento: List<Evento>, binding: ActivityMainBinding){

        binding.rvEventoMain.apply {
            layoutManager = LinearLayoutManager(binding.root.context)
            adapter       = RecyclerViewEvento(evento)
        }

    }

    fun exiberMensagemErro(metodo: String , mensagem: String){
        Log.e("EventosChamada", "$metodo - $mensagem")
        Toast.makeText(context , "$metodo - $mensagem" , Toast.LENGTH_SHORT).show()
    }

}