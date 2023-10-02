package br.com.renancsdev.avenuecodeeventos.di.module

import br.com.renancsdev.avenuecodeeventos.api.call.CallEvento
import org.koin.dsl.module

val apiModule = module {

    single { CallEvento(get()) }

}