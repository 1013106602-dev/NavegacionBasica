package com.ejemplo.navegacion

data class Lugar(
    val id: Int,
    val nombre: String,
    val descripcion: String
)

val lugares = listOf(
    Lugar(0, "Monserrate", "Cerro que domina el oriente de la ciudad. Se sube en teleférico, funicular o a pie."),
    Lugar(1, "La Candelaria", "El centro histórico, con calles empedradas, museos y casas coloniales."),
    Lugar(2, "Usaquén", "Barrio con plaza colonial y mercado de pulgas los domingos."),
    Lugar(3, "Jardín Botánico", "Colección de plantas de todo el país en pleno centro de la ciudad."),
    Lugar(4, "Parque Simón Bolívar", "Uno de los parques urbanos más grandes de la ciudad, con lago y ciclorrutas.")
)
