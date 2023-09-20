package br.com.renancsdev.avenuecodeeventos.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.renancsdev.avenuecodeeventos.databinding.ItemRecyclerEventoBinding
import br.com.renancsdev.avenuecodeeventos.model.Evento
import br.com.renancsdev.avenuecodeeventos.ui.holder.ViewHolderEvento

class RecyclerViewEvento(var list: List<Evento>): RecyclerView.Adapter<ViewHolderEvento>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderEvento {
        return ViewHolderEvento(ItemRecyclerEventoBinding.inflate(LayoutInflater.from(parent.context)))
    }
    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: ViewHolderEvento, position: Int) {
       holder.bind(list[position])
    }

}