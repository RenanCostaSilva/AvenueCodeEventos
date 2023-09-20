package br.com.renancsdev.avenuecodeeventos.model

data class Evento(

    val people: List<Any?>,
    var date : Long,
    var description : String,
    var image : String,
    var longitude : Double,
    var latitude : Double,
    var price : Double,
    var title : String,
    var id : Int,

)
