package br.com.renancsdev.avenuecodeeventos.extension

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.naoVazio(): Boolean {
    return this.editText?.text?.isNotEmpty() == true
}

fun TextInputLayout.paraTexto(): String {
    return this.editText?.text.toString()
}