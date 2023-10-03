package br.com.renancsdev.avenuecodeeventos.api.call

import androidx.lifecycle.liveData
import br.com.renancsdev.avenuecodeeventos.api.interfaces.API
import br.com.renancsdev.avenuecodeeventos.api.sealed.Resultado


class CallEvento(private val api: API) {

    fun buscarEventos() = liveData {

        if(api.buscarEventosLiveData().isSuccessful){
            emit(Resultado.Sucesso(dado = api.buscarEventosLiveData().body()))
        } else {
            emit(Resultado.Erro(exception = Exception("Falha ao buscar os dados do usuario")))
        }

    }

    fun buscarEventosPorID(id: Int) = liveData {

        if(api.buscarEventoPorIDLiveData(id).isSuccessful){
            emit(Resultado.Sucesso(dado = api.buscarEventoPorIDLiveData(id).body()))
        } else {
            emit(Resultado.Erro(exception = Exception("Falha ao buscar os dados do usuario")))
        }

    }

    fun fazerCheckIn( nome: String , email: String , id: Int) = liveData {

        val resposta = api.fazerCheckInLiveData(nome , email , id)

        if(api.fazerCheckInLiveData(nome , email , id).isSuccessful){
            if(api.fazerCheckInLiveData(nome , email , id).code() == 200 || api.fazerCheckInLiveData(nome , email , id).code() == 201){
                emit(Resultado.Sucesso(dado = api.fazerCheckInLiveData(nome , email , id).body()))
            }
            else{
                emit(Resultado.Erro(exception = Exception("Resposta não esperada: ${resposta.code()}")))
            }
        } else {
            emit(Resultado.Erro(exception = Exception("Falha ao buscar os dados do usuario")))
        }

    }


}