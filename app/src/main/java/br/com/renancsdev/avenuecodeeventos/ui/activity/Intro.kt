package br.com.renancsdev.avenuecodeeventos.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import br.com.renancsdev.avenuecodeeventos.R
import br.com.renancsdev.avenuecodeeventos.databinding.ActivityIntroBinding
import br.com.renancsdev.avenuecodeeventos.util.animacao.Animacao

class Intro : AppCompatActivity() {

    private lateinit var binding: ActivityIntroBinding
    private var layoutIntro  = R.layout.activity_intro
    private var context  = this@Intro

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setarConfiguracaoInicial()
        animacaoRedirecionamento()

    }

    private fun setarConfiguracaoInicial(){
        setarLayout()
        setarBinding()
    }

    private fun setarLayout(){
        setContentView(layoutIntro)
    }

    private fun setarBinding(){
        binding = DataBindingUtil.setContentView(context , layoutIntro)
    }

    private fun animacaoRedirecionamento() = Animacao().animacaoRedirecionamento(2000 , this@Intro)

}