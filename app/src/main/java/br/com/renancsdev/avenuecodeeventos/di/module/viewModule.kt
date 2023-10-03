package br.com.renancsdev.avenuecodeeventos.di.module

import br.com.renancsdev.avenuecodeeventos.viewmodel.detalhe.DetalheViewModel
import br.com.renancsdev.avenuecodeeventos.viewmodel.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {

    viewModel { MainViewModel(get()) }
    viewModel { DetalheViewModel(get()) }

}