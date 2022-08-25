package com.example.maps

import java.io.Serializable

data class Marker(
    val id : Int,
    val address : String,
    val imgAddress : String,
    val title : String,
    val posLat : Double,
    val posLng : Double,
    val icoMarker : Int,
): Serializable