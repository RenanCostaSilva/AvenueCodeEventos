package br.com.renancsdev.avenuecodeeventos.ui.holder

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.renancsdev.avenuecodeeventos.R
import br.com.renancsdev.avenuecodeeventos.databinding.ItemRecyclerEventoBinding
import br.com.renancsdev.avenuecodeeventos.model.Evento
import br.com.renancsdev.avenuecodeeventos.ui.activity.DetalheEvento
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.squareup.picasso.Picasso
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.Date

class ViewHolderEvento(private var binding: ItemRecyclerEventoBinding): RecyclerView.ViewHolder(binding.root) {

    var context: Context = binding.root.context

    fun bind(evento: Evento) {

        exibirImagemComGlide( urlParaString(evento) )
        exibirDados(evento)
        redirecionarDetalheComID(evento.id)

    }

    private fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("dd/MM/yyyy")
        return format.format(date)
    }

    private fun exibirImagemComGlide(urlRequisicao: String){
        Glide.with(context)
            .load(urlRequisicao)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(10) ))
            .error(R.drawable.img404)
            .override(500,500)
            .into(binding.imgItemEventoTitulo)
    }

    private fun redirecionarDetalheComID(id: Int){
        binding.cardEvento.setOnClickListener {
            binding.root.context.startActivity(Intent(context , DetalheEvento::class.java).putExtra("id" , id))
        }
    }

    private fun exibirDados(evento: Evento){
        binding.tvItemEventoTitulo.text = "${evento.title}"
        binding.tvItemEventoDescricao.text = evento.description
    }

    private fun urlParaString(evento: Evento): String{
        return if(evento.image.contains("lproweb")){
            "https://lproweb.procempa.com.br/pmpa/prefpoa/seda_news/usu_img/Papel%20de%20Parede.png"
        }else{
            evento.image
        }
    }

}