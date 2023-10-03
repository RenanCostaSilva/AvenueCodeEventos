package br.com.renancsdev.avenuecodeeventos.ui.activity

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.renancsdev.avenuecodeeventos.R
import br.com.renancsdev.avenuecodeeventos.api.sealed.Resultado
import br.com.renancsdev.avenuecodeeventos.databinding.ActivityDetalheEventoBinding
import br.com.renancsdev.avenuecodeeventos.databinding.DialogScreenAveneSetDataBinding
import br.com.renancsdev.avenuecodeeventos.databinding.DialogScreenAvenueBinding
import br.com.renancsdev.avenuecodeeventos.extension.paraTexto
import br.com.renancsdev.avenuecodeeventos.extension.toastCurto
import br.com.renancsdev.avenuecodeeventos.model.Evento
import br.com.renancsdev.avenuecodeeventos.util.navegacao.Redirecionar
import br.com.renancsdev.avenuecodeeventos.util.validacao.Valida
import br.com.renancsdev.avenuecodeeventos.viewmodel.detalhe.DetalheViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetalheEvento : AppCompatActivity() {

    private lateinit var  binding: ActivityDetalheEventoBinding
    private val viewModel by viewModel<DetalheViewModel>()
    private var context = this@DetalheEvento
    private val layoutDetalhe = R.layout.activity_detalhe_evento

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inicializarLayout()
        setarBinding()
        buscarEventos()

    }

    private fun inicializarLayout(){
        setContentView(layoutDetalhe)
    }

    private fun setarBinding(){
        binding = DataBindingUtil.setContentView(context , layoutDetalhe)
    }

    private fun buscarEventos(){
        viewModel.buscarEventosPorID(intent.getIntExtra("id",0)).observe(this@DetalheEvento){ model ->
            model?.let { resultado -> viewModelResultado(resultado , intent.getIntExtra("id",0))} ?: false
        }
    }

    private fun viewModelResultado (resultado: Resultado<Evento?>, id: Int){
        when (resultado){
            is Resultado.Sucesso ->{
                resultado.dado?.let {
                    exibirImagemComGlide(resultado.dado.image , binding.detalheEventoWallpapper)
                    exibirImagemComGlide(resultado.dado.image , binding.includeEvento.detalheEventoThumb)
                    exibirDado(resultado.dado)
                    eventoClickDialogCheckIn(id)
                    binding.pbRecyclerDetalhe.visibility = View.GONE
                    true
                } ?: false
            }
            is Resultado.Erro -> false
        }
    }

    // Eventos
    private fun exibirImagemComGlide(urlRequisicao: String , imagem: ImageView){
        Glide.with(context)
            .load(urlRequisicao)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(10) ))
            .error(R.drawable.img404)
            .override(500,500)
            .into(imagem)
    }
    private fun exibirDado(evento: Evento){
        binding.includeEvento.detalheEventoNome.text = evento.title
        binding.includeEvento.detalheEventoDescricao.text = evento.description
    }
    private fun eventoClickDialogCheckIn(id: Int){
        binding.includeEvento.btnEventCheckIn.setOnClickListener {
            showDialogInputDadosMaterial3(id)
        }
    }
    //


    private fun viewModelResultadoCheckIn (resultado: Resultado<Evento?>){
        when (resultado){
            is Resultado.Sucesso ->{
                showDialogSucess("Cadastrado no evento com sucesso !.")
                true
            }
            is Resultado.Erro -> {
                showDialogError("Erro ao se cadastrar no evento selecionado")
                false
            }
        }
    }


    // Dialog de Input
    private fun showDialogInputDadosMaterial3(idEvento: Int) {


        val madb = MaterialAlertDialogBuilder(context).create()
        madb.setView(dialogBindingCheckInMaterial3(madb , (context as Activity).layoutInflater , idEvento ).root)
        madb.show()

    }
    private fun dialogBindingCheckInMaterial3(madb: androidx.appcompat.app.AlertDialog, layoutInflater: LayoutInflater, id: Int): DialogScreenAveneSetDataBinding{

        val dialogBinding = DialogScreenAveneSetDataBinding.inflate(layoutInflater)

        dialogBinding.btnDialogDados.setOnClickListener {

            if(Valida().validaformulario(this@DetalheEvento , dialogBinding)){
                fazerCheckInLiveData(dialogBinding.tilDialogDadosNome.paraTexto() , dialogBinding.tilDialogDadosEmail.paraTexto() , id)
                delay()
            }

            madb.dismiss()
        }

        return dialogBinding
    }
    // -- //


    // Dialog de Sucesso e Erro
    private fun showDialogSucess(mensagem: String) {

        val dialog = AlertDialog.Builder(context as Activity).create()
        val inflater = (context as Activity).layoutInflater
        val binding = dialogBindingSucess(dialog , inflater , mensagem)

        dialog.setView(binding.root)
        dialog.show()

    }
    private fun dialogBindingSucess(dialog: AlertDialog , layoutInflater: LayoutInflater , mensagem: String): DialogScreenAvenueBinding {

        val dialogBinding = DialogScreenAvenueBinding.inflate(layoutInflater)
        dialogBinding.tvDialogMsg.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_check_green , 0 , 0 , 0)

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
        dialogBinding.tvDialogMsg.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_error_red , 0 , 0 , 0)

        dialogBinding.btnFecharDialog.setOnClickListener {
            dialog.dismiss()
        }

        dialogBinding.tvDialogMsg.text = mensagem

        return dialogBinding
    }
    // -- //


    private fun delay(){
        Redirecionar().telaPrincipalComDelay(4000 , this@DetalheEvento)
    }
    private fun fazerCheckInLiveData(nome: String , email: String , id: Int){
        viewModel.fazerCheckIN(nome , email , id).observe(this@DetalheEvento){ model ->
            model?.let {
                resultado ->
                viewModelResultadoCheckIn(resultado)
            } ?: false
        }
    }

    private fun exiberMensagemErro(metodo: String , mensagem: String){
        Log.e("EventoCheckIn", "$metodo - $mensagem")
        "$metodo - $mensagem".toastCurto(this@DetalheEvento)
    }

}