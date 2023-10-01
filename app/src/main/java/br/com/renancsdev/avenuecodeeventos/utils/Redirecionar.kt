package br.com.renancsdev.avenuecodeeventos.utils

import android.content.Context
import android.content.Intent
import br.com.renancsdev.avenuecodeeventos.ui.activity.MainActivity
import java.util.Timer
import kotlin.concurrent.schedule

class Redirecionar() {

    fun telaPrincipal(context: Context){
        context.startActivity(Intent(context , MainActivity::class.java))
    }

    fun telaPrincipalComDelay(duracao:Long , context: Context){
        Timer().schedule(duracao) {
            context.startActivity(Intent(context , MainActivity::class.java))
        }

    }

}