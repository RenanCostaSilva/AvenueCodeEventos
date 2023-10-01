package br.com.renancsdev.avenuecodeeventos.di.module

import br.com.renancsdev.avenuecodeeventos.api.call.CallEvento
import br.com.renancsdev.avenuecodeeventos.api.call.CallEventoCheckIN
import br.com.renancsdev.avenuecodeeventos.api.call.CallEventoID
import org.koin.dsl.module

val apiModule = module {

    // Provide GithubRepository
    single { CallEvento(get()) }
    single { CallEventoID(get()) }
    single { CallEventoCheckIN(get()) }

}