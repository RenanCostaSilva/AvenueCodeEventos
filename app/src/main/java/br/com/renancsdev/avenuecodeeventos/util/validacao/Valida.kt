package br.com.renancsdev.avenuecodeeventos.util.validacao

import android.content.Context
import br.com.renancsdev.avenuecodeeventos.databinding.DialogScreenAveneSetDataBinding
import br.com.renancsdev.avenuecodeeventos.extension.naoVazio
import br.com.renancsdev.avenuecodeeventos.extension.paraTexto
import br.com.renancsdev.avenuecodeeventos.extension.toastCurto

class Valida {

    private val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"

    private fun isValidEmail(email: String): Boolean {
        return email.matches(emailRegex.toRegex())
    }

    fun validaformulario(contexto : Context, dialogBinding: DialogScreenAveneSetDataBinding): Boolean {

        var check = false
        if(dialogBinding.tilDialogDadosNome.naoVazio() && dialogBinding.tilDialogDadosEmail.naoVazio()){

            if(isValidEmail(dialogBinding.tilDialogDadosEmail.paraTexto())){

                check = true
            }else{
                "O email digitado é inválido !".toastCurto(contexto)
            }

        }else{
            "O seu nome e o e-mail são obrigatórios !".toastCurto(contexto)
        }

        return check
    }

}