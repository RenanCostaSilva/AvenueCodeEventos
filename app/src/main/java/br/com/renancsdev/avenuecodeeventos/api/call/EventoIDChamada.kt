package br.com.renancsdev.avenuecodeeventos.api.call

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import br.com.renancsdev.avenuecodeeventos.R
import br.com.renancsdev.avenuecodeeventos.api.interfaces.API
import br.com.renancsdev.avenuecodeeventos.api.service.ServiceBuilder
import br.com.renancsdev.avenuecodeeventos.databinding.ActivityDetalheEventoBinding
import br.com.renancsdev.avenuecodeeventos.databinding.DialogScreenAveneSetDataBinding
import br.com.renancsdev.avenuecodeeventos.databinding.DialogScreenAvenueBinding
import br.com.renancsdev.avenuecodeeventos.extension.esconder
import br.com.renancsdev.avenuecodeeventos.extension.mostrar
import br.com.renancsdev.avenuecodeeventos.model.Evento
import br.com.renancsdev.avenuecodeeventos.ui.activity.MainActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Timer
import kotlin.concurrent.schedule


class EventoIDChamada(var context: Context, var binding: ActivityDetalheEventoBinding , var id: Int) {

    private var nome = ""
    private var email = ""
    private var idEvent = 0

    //val dialog: AlertDialog = AlertDialog.Builder(context as Activity).create()
    private val inflater = (context as Activity).layoutInflater

    //Api
    private fun idEventoChamadaApi(): Call<Evento> = ServiceBuilder.buildService(API::class.java).buscarEventoPorID(id)
    private fun eventoCheckIn():      Call<Evento> = ServiceBuilder.buildService(API::class.java).fazerCheckIn(nome , email , idEvent)

    fun verificarIdEventoRetornoDaApi(){

        idEventoChamadaApi().enqueue(object : Callback<Evento> {
            override fun onResponse(call: Call<Evento>, response: Response<Evento>) {
                if(respostaIDEventoDaApi(response)){
                    if(checarCorpoIdEventoApi(response)){

                        exibirImagemComGlide(response.body()!!.image , binding.detalheEventoWallpapper)
                        exibirImagemComGlide(response.body()!!.image , binding.detalheEventoThumb)
                        exibirDado(response.body()!!)
                        fazerCheckInEvento(id)

                    }
                }
            }
            override fun onFailure(call: Call<Evento>, t: Throwable) {
                exiberMensagemErro("verificarIdEventoRetornoDaApi" , "${t.message}")
            }
        })
    }

    private fun respostaIDEventoDaApi(resposta: Response<Evento>): Boolean{

        var check = false
        if(resposta.isSuccessful) {
            check = true
        }else{
            exiberMensagemErro("respostaIDEventoDaApi" , "${resposta.errorBody().toString()}")
        }

        return check
    }

    private fun checarCorpoIdEventoApi(resposta: Response<Evento>): Boolean {

        binding.pbRecyclerDetalhe.mostrar()
        var check = false

        if (resposta.body() != null) {
            check = true
        } else {
            exiberMensagemErro("checarCorpoIdEventoApi" , "Failed to get response")
        }
        binding.pbRecyclerDetalhe.esconder()

        return check
    }


    private fun verificarEventoRetornoCheckIn(){
        eventoCheckIn().enqueue(object : Callback<Evento> {
            override fun onResponse(call: Call<Evento>, response: Response<Evento>) {
                if(respostaIDEventoDaApi(response)){

                    showDialogSucess("CheckIn\nrealizado com sucesso !")
                    if(response.code() == 200 || response.code() == 201){
                        delay()
                    }else{
                        showDialogError("Erro\nao realizar o checkin")
                    }

                }
            }
            override fun onFailure(call: Call<Evento>, t: Throwable) {
                exiberMensagemErro("verificarEventoRetornoCheckIn" , "${t.message}")
            }
        })
    }

    private fun showDialogInputDados(idEvento: Int) {

        val dialog = AlertDialog.Builder(context as Activity).create()
        val inflater = (context as Activity).layoutInflater
        val binding = dialogBindingCheckIn(dialog , inflater , idEvento)

        dialog.setView(binding.root)
        dialog.show()

    }
    private fun dialogBindingCheckIn(dialog: AlertDialog , layoutInflater: LayoutInflater , id: Int): DialogScreenAveneSetDataBinding {

        val dialogBinding = DialogScreenAveneSetDataBinding.inflate(layoutInflater)
        dialogBinding.btnDialogDados.setOnClickListener {
            nome = dialogBinding.tilDialogDadosNome.toString()
            email = dialogBinding.tilDialogDadosEmail.toString()
            idEvent = id

            verificarEventoRetornoCheckIn()
            dialog.dismiss()
        }

        return dialogBinding
    }


    private fun showDialogSucess(mensagem: String) {

        val dialog = AlertDialog.Builder(context as Activity).create()
        val inflater = (context as Activity).layoutInflater
        val binding = dialogBindingSucess(dialog , inflater , mensagem)

        dialog.setView(binding.root)
        dialog.show()

    }
    private fun dialogBindingSucess(dialog: AlertDialog , layoutInflater: LayoutInflater , mensagem: String): DialogScreenAvenueBinding {

        val dialogBinding = DialogScreenAvenueBinding.inflate(layoutInflater)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialogBinding.imgDialogTipoMensagem.setImageDrawable(ResourcesCompat.getDrawable(context.resources , R.drawable.ic_check_green , null))
        } else {
            dialogBinding.imgDialogTipoMensagem.setImageDrawable(context.resources.getDrawable(R.drawable.ic_check_green))
        }

        dialogBinding.btnFecharDialog.setOnClickListener {
            dialog.dismiss()
        }

        dialogBinding.tvDialogMsg.text = mensagem

        return dialogBinding
    }


    private fun showDialogError(mensagem: String) {

        val dialog = AlertDialog.Builder(context as Activity).create()
        val inflater = (context as Activity).layoutInflater
        val binding = dialogBindingError(dialog , inflater , mensagem)

        dialog.setView(binding.root)
        dialog.show()

    }
    private fun dialogBindingError(dialog: AlertDialog , layoutInflater: LayoutInflater , mensagem: String): DialogScreenAvenueBinding {

        val dialogBinding = DialogScreenAvenueBinding.inflate(layoutInflater)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialogBinding.imgDialogTipoMensagem.setImageDrawable(ResourcesCompat.getDrawable(context.resources , R.drawable.ic_error_red , null))
        } else {
            dialogBinding.imgDialogTipoMensagem.setImageDrawable(context.resources.getDrawable(R.drawable.ic_error_red))
        }

        dialogBinding.btnFecharDialog.setOnClickListener {
            dialog.dismiss()
        }

        dialogBinding.tvDialogMsg.text = mensagem

        return dialogBinding
    }



    private fun delay(){
        Timer().schedule(4000) {
            context.startActivity(Intent(context , MainActivity::class.java))
        }
    }
    private fun fazerCheckInEvento(id: Int){
        binding.btnEventCheckIn.setOnClickListener {
            showDialogInputDados(id)
        }
    }
    private fun exibirDado(evento: Evento){
        binding.detalheEventoNome.text = evento.title
        binding.detalheEventoDescricao.text = evento.description
    }
    private fun exibirImagemComGlide(urlRequisicao: String , imagem: ImageView){
        Glide.with(context)
            .load(urlRequisicao)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(10) ))
            .error(R.drawable.img404)
            .override(500,500)
            .into(imagem)
    }
    private fun exiberMensagemErro(metodo: String , mensagem: String){
        Log.e("EventoCheckIn", "$metodo - $mensagem")
        Toast.makeText(context , "$metodo - $mensagem" , Toast.LENGTH_SHORT).show()
    }

}