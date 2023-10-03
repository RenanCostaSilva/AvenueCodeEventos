package br.com.renancsdev.avenuecodeeventos.viewmodel.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.renancsdev.avenuecodeeventos.api.call.CallEvento
import br.com.renancsdev.avenuecodeeventos.api.sealed.Resultado
import br.com.renancsdev.avenuecodeeventos.model.Evento
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList

class MainViewModel(private val api: CallEvento): ViewModel() {

    private val _listEventos = MutableLiveData<LiveData<Resultado<ArrayList<Evento>?>>>()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            _listEventos.postValue(api.buscarEventos())
        }
    }

    fun buscaListaEventos(): LiveData<Resultado<ArrayList<Evento>?>> =
        api.buscarEventos()
}