package br.com.renancsdev.avenuecodeeventos.ui.activity

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.renancsdev.avenuecodeeventos.R
import br.com.renancsdev.avenuecodeeventos.api.EventoIDChamada
import br.com.renancsdev.avenuecodeeventos.api.EventosChamada
import br.com.renancsdev.avenuecodeeventos.databinding.ActivityDetalheEventoBinding
import br.com.renancsdev.avenuecodeeventos.databinding.DialogScreenAvenueBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.security.AccessController.getContext


class DetalheEvento : AppCompatActivity() {

    private lateinit var  binding: ActivityDetalheEventoBinding
    private var context = this@DetalheEvento
    private var activity = context as Activity
    private val layoutDetalhe = R.layout.activity_detalhe_evento

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inicializarLayout()
        setarBinding()

        CoroutineScope(Dispatchers.IO).launch{
            requisicaoAPI()
        }

    }

    fun inicializarLayout(){
        setContentView(layoutDetalhe)
    }

    fun setarBinding(){
        binding = DataBindingUtil.setContentView(context , layoutDetalhe)
    }

    private fun requisicaoAPI(){
        EventoIDChamada(context , binding , intent.getIntExtra("id",0)).verificarIdEventoRetornoDaApi()
    }

    private fun showDialog(mensagem: String) {
        val inflater = activity.layoutInflater
        val dialogBinding = DialogScreenAvenueBinding.inflate(inflater)
        val dialog = AlertDialog.Builder(activity).create()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        dialogBinding.btnFecharDialog.setOnClickListener {
            dialog.dismiss()
        }

        dialogBinding.tvDialogMsg.text = mensagem
        dialog.setView(dialogBinding.root)
        dialog.show()

    }
}