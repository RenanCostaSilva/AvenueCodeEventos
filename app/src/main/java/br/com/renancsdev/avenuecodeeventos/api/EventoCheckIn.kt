package br.com.renancsdev.avenuecodeeventos.api

import android.content.Context
import android.util.Log
import android.widget.Toast
import br.com.renancsdev.avenuecodeeventos.R
import br.com.renancsdev.avenuecodeeventos.databinding.ActivityDetalheEventoBinding
import br.com.renancsdev.avenuecodeeventos.model.Evento
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventoCheckIn(var context: Context) {

    private val request = ServiceBuilder.buildService(API::class.java)
    private var nome = ""
    private var email = ""
    private var id = 0

    //Api
    fun eventoCheckIn(): Call<Evento> = request.fazerCheckIn(nome , email , id)

    fun verificarEventoRetornoCheckIn(){
        eventoCheckIn().enqueue(object : Callback<Evento> {
            override fun onResponse(call: Call<Evento>, response: Response<Evento>) {
                Toast.makeText(context , "$${response.isSuccessful}" , Toast.LENGTH_SHORT).show()
            }
            override fun onFailure(call: Call<Evento>, t: Throwable) {
                exiberMensagemErro("verificarEventoRetornoCheckIn","${t.message}")
            }
        })
    }
    fun respostaIDEventoDaApi(resposta: Response<Evento>): Boolean{

        var check = false
        if(resposta.isSuccessful) {
            check = true
        }else{
            exiberMensagemErro("respostaIDEventoDaApi","${resposta.errorBody().toString()}")
        }

        return check
    }
    fun checarCorpoIdEventoApi(resposta: Response<Evento>): Boolean {

        //binding.flSkyFilmesLoading.mostar()
        var check = false

        if (resposta.body() != null) {
            check = true
        } else {
            exiberMensagemErro("checarCorpoIdEventoApi","Failed to get response")
        }
        //binding.flSkyFilmesLoading.esconder()
        return check
    }

    fun exiberMensagemErro(metodo: String , mensagem: String){
        Log.e("EventoCheckIn", "$metodo - $mensagem")
        Toast.makeText(context , "$metodo - $mensagem" , Toast.LENGTH_SHORT).show()
    }

}