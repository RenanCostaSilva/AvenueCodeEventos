package br.com.renancsdev.avenuecodeeventos.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import br.com.renancsdev.avenuecodeeventos.R
import br.com.renancsdev.avenuecodeeventos.databinding.ActivityIntroBinding

class Intro : AppCompatActivity() {

    private lateinit var binding: ActivityIntroBinding
    var layoutIntro  = R.layout.activity_intro
    var context  = this@Intro

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setarConfiguracaoInicial()
        animacaoRedirecionamento()

    }

    fun setarConfiguracaoInicial(){
        setarLayout()
        setarBinding()
    }

    private fun setarLayout(){
        setContentView(layoutIntro)
    }

    fun setarBinding(){
        binding = DataBindingUtil.setContentView(context , layoutIntro)
    }

    fun animacaoRedirecionamento(){
        Handler(Looper.getMainLooper()).postDelayed({
            context.startActivity(Intent(this@Intro, MainActivity::class.java))
            overridePendingTransition(R.anim.fade_in , R.anim.fade_out)
        },2000)
    }

}