package br.com.renancsdev.avenuecodeeventos.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import br.com.renancsdev.avenuecodeeventos.R
import br.com.renancsdev.avenuecodeeventos.ui.activity.MainActivity

class Animacao{

    fun animacaoRedirecionamento(delayTempo: Long , context: Context){
        Handler(Looper.getMainLooper()).postDelayed({
            context.startActivity(Intent(context as Activity , MainActivity::class.java))
            context.overridePendingTransition(R.anim.fade_in , R.anim.fade_out)
        },delayTempo)
    }

}