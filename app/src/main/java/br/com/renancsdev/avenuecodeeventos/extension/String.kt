package br.com.renancsdev.avenuecodeeventos.extension

import android.content.Context
import android.util.Log
import android.widget.Toast

fun String.toastCurto(context: Context) {
    return  Toast.makeText(context , this, Toast.LENGTH_SHORT).show()
}

fun String.toastLongo(context: Context) {
    return  Toast.makeText(context , this , Toast.LENGTH_LONG).show()
}


fun String.loeE(metodo: String): Int{
    return Log.e(metodo , this)
}