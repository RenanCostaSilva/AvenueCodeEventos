package br.com.renancsdev.avenuecodeeventos.api.call

import androidx.lifecycle.liveData
import br.com.renancsdev.avenuecodeeventos.api.interfaces.API


class CallEvento(private val api: API) {

    fun buscarEventos() = liveData {

        val resposta = api.buscarEventosLiveData()
        if(resposta.isSuccessful){
            if(resposta != null){
                emit(Resultado.Sucesso(dado = resposta.body()))
            }else{
                emit(Resultado.Erro(exception = Exception("Nenhuma informação encontrada")))
            }
        } else {
            emit(Resultado.Erro(exception = Exception("Falha ao buscar os dados do usuario")))
        }

    }

}