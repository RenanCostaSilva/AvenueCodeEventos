package br.com.renancsdev.avenuecodeeventos.api.call

import androidx.lifecycle.liveData
import br.com.renancsdev.avenuecodeeventos.api.interfaces.API

class CallEventoCheckIN(private val api: API) {

    fun fazerCheckIn( nome: String , email: String , id: Int) = liveData {

        val resposta = api.fazerCheckInLiveData(nome , email , id)
        if(resposta.isSuccessful){
            if(resposta != null){
                if(resposta.code() == 200 || resposta.code() == 201){
                    emit(Resultado.Sucesso(dado = resposta.body()))
                }
                else{
                    emit(Resultado.Erro(exception = Exception("Resposta não esperada: ${resposta.code()}")))
                }
            }else{
                emit(Resultado.Erro(exception = Exception("Nenhuma informação encontrada")))
            }
        } else {
            emit(Resultado.Erro(exception = Exception("Falha ao buscar os dados do usuario")))
        }

    }

}