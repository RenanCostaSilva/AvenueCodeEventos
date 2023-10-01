package br.com.renancsdev.avenuecodeeventos.viewmodel.detalhe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.renancsdev.avenuecodeeventos.api.call.CallEvento
import br.com.renancsdev.avenuecodeeventos.api.call.CallEventoID
import br.com.renancsdev.avenuecodeeventos.api.call.Resultado
import br.com.renancsdev.avenuecodeeventos.model.Evento
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.ArrayList

class DetalheViewModel(private val api: CallEventoID): ViewModel() {

    private val _eventoID = MutableLiveData<LiveData<Resultado<Evento?>>>()
    private val _checkIn = MutableLiveData<LiveData<Resultado<Evento?>>>()

    val id = 0
    val nome = ""
    val email = ""

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _eventoID.postValue(api.buscarEventosPorID(id))
            _checkIn.postValue(api.fazerCheckIn(nome , email , id))
        }
    }

    fun buscarEventosPorID(id: Int): LiveData<Resultado<Evento?>> =
        api.buscarEventosPorID(id)

    fun fazerCheckIN(nome: String , email: String , id: Int): LiveData<Resultado<Evento?>> =
        api.fazerCheckIn(nome , email ,id)
}