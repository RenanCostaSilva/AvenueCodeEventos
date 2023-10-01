package br.com.renancsdev.avenuecodeeventos.di.application

import android.app.Application
import br.com.renancsdev.avenuecodeeventos.di.module.apiModule
import br.com.renancsdev.avenuecodeeventos.di.module.redeModule
import br.com.renancsdev.avenuecodeeventos.di.module.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext

class Aplicacao: Application() {

    private val appModules = listOf(
        redeModule,
        apiModule,
        vmModule
    )

    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@Aplicacao)
            modules(appModules)
        }
    }

}